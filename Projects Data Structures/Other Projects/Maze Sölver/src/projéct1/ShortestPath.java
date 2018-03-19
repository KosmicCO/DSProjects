/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projéct1;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;

/**
 *
 * @author cbarnum18
 */
public class ShortestPath {
    
    private static int numMoves = 0; // Allows us to keep track of how many recursive calls to findPath are required to solve the maze.
    private String[][] maze;
    private int numRows;
    private int numCols;
    private Graph graph;
    
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
    }
    
    public void solve(){
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph);
    }
    
    private void makeGraph(int col, int row){
        int st = -1, gl;
        graph = new Graph(col * row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch(maze[i][j].charAt(0)){
                    case 'G':
                        gl = j + i * numCols;
                        if(st == -1) st = -2;
                    case 'S':
                        if(st == -1) st = j + i * numCols;
                        if(st == -2) st++;
                    case '.':
                        if((i + 1) < numRows && ".SG".contains(maze[i + 1][j])){
                            graph.addEdge(j + i * numCols, j + (i + 1) * numCols);
                        }
                        if((j + 1) < numCols && ".SG".contains(maze[i][j + 1])){
                            graph.addEdge(j + i * numCols, j + 1 + i * numCols);
                        }
                }
            }
        }
    }
}
