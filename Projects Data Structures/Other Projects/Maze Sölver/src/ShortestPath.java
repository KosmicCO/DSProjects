/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author cbarnum18
 */
public class ShortestPath {

    private String[][] maze;
    private String[][] outMaze;
    private int numRows;
    private int numCols;
    private Graph graph;
    private int start = -1, goal = 0;

    private final static int[][] DIRS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public ShortestPath(String mazeStr) {
        int i = 0;
        int endIndex = mazeStr.indexOf(" ");
        this.numRows = Integer.parseInt(mazeStr.substring(i, endIndex));
        i = endIndex + 1;
        endIndex = mazeStr.indexOf(" ", i);
        this.numCols = Integer.parseInt(mazeStr.substring(i, endIndex));
        i = endIndex + 1; // i now points to the first char in first row of maze

        // Let's make sure our string maze has the proper number of characters in it...
        if (numRows * numCols != mazeStr.substring(i).length() - numRows + 1) {
            throw new IllegalArgumentException("mazeStr is not well-formed.");
        }

        // Now we build the maze from the maze string.
        this.maze = new String[numRows][numCols];  // DON'T FORGET to actually create the array!
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                maze[row][col] = mazeStr.substring(i, i + 1);
                i++;
            }
            i++; // this increment, after each row, skips the newline character '\n'
        }

        outMaze = new String[numRows][numCols];

        for (int j = 0; j < numRows; j++) {
            for (int k = 0; k < numCols; k++) {
                switch (maze[j][k].charAt(0)) {
                    case '.':
                        outMaze[j][k] = "\u001b[0m.";
                        break;
                    case 'G':
                    case 'S':
                        outMaze[j][k] = "\u001b[42m\u001b[30m+";
                        break;
                    case '#':
                        outMaze[j][k] = "\u001b[0m\u001b[44m#";
                }
            }
        }
    }
    
    public static String makeMazeStringFromFile(String fName) {
        String str = "";
        // build the mazeStr
        //String mazeStr = "" + rows + " " + cols + " ";
        // read the text file containing maze characters and append to mazeStr
        File file = new File(fName);
        Scanner reader = new Scanner(System.in);
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Maze file not found.");
        }
        String line1 = reader.nextLine();
        str = str + line1 + "\n";
        int numCols = line1.length();
        int numRows = 1;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            str = str + line + "\n";
            if (line.length() > 0) {
                numRows++;
            }
        }

        String mazeStr = "" + numRows + " " + numCols + " " + str.substring(0, str.length());
        if (mazeStr.substring(mazeStr.length() - 1).equals("\n")) // did they press ENTER after the last line?
        {
            mazeStr = mazeStr.substring(0, mazeStr.length() - 1);  // drops the last "\n" character
        }
        return mazeStr;
    }

    public void solve() {
        makeGraph();
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, start);
        for (int p : bfp.pathTo(goal)) {
            outMaze[toY(p)][toX(p)] = "\u001b[41;1m\u001b[37m+";
        }
    }

    private int toX(int id) {
        return id % numCols;
    }

    private int toY(int id) {
        return id / numRows;
    }

    private void makeGraph() {
        graph = new Graph(numCols * numRows);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                switch (maze[i][j].charAt(0)) {
                    case 'G':
                        goal = j + i * numCols;
                        if (start == -1) {
                            start = -2;
                        }
                    case 'S':
                        if (start == -1) {
                            start = j + i * numCols;
                        }
                        if (start == -2) {
                            start++;
                        }
                    case '.':
                        if ((i + 1) < numRows && ".SG".contains(maze[i + 1][j])) {
                            graph.addEdge(j + i * numCols, j + (i + 1) * numCols);
                        }
                        if ((j + 1) < numCols && ".SG".contains(maze[i][j + 1])) {
                            graph.addEdge(j + i * numCols, j + 1 + i * numCols);
                        }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                sb.append(outMaze[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ShortestPath ms = new ShortestPath("6 6 ##S###\n#....#\n#....#\n#....#\n#..#.#\n#G####");
        ms.solve();
        System.out.println(ms);

        System.out.println("\nSolving maze in maze1.txt");
        ms = new ShortestPath(makeMazeStringFromFile("test/maze1.txt"));
        ms.solve();
        System.out.println(ms);

        System.out.println("\nSolving maze in maze2.txt");
        ms = new ShortestPath(makeMazeStringFromFile("test/maze2.txt"));
        ms.solve();
        System.out.println(ms);

        System.out.println("\nSolving maze in maze3.txt");
        ms = new ShortestPath(makeMazeStringFromFile("test/maze3.txt"));
        ms.solve();
        System.out.println(ms);
    }
}
