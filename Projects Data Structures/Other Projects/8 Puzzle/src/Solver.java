
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class Solver {

    private final int moves;
    private final boolean solvable;
    private final Iterable<Board> steps;

    public Solver(Board initial) {
        MinPQ<Node> regQueue = new MinPQ<Node>();
        MinPQ<Node> othQueue = new MinPQ<Node>();

        Node next = new Node(initial, null);
        Node nTwn = new Node(initial.twin(), null);

        while (!(next.brd.isGoal() || nTwn.brd.isGoal())) {

            for (Board neighbor : next.brd.neighbors()) {

                if (next.parent != null && next.parent.brd.equals(neighbor)) {
                    continue;
                }

                regQueue.insert(new Node(neighbor, next));
            }

            for (Board neighbor : nTwn.brd.neighbors()) {

                if (next.parent != null && nTwn.parent.brd.equals(neighbor)) {
                    continue;
                }

                othQueue.insert(new Node(neighbor, nTwn));
            }

            next = regQueue.delMin();
            nTwn = othQueue.delMin();
        }

        solvable = next.brd.isGoal();
        steps = next.steps();
        moves = next.length;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return steps;
    }

    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }

    private class Node implements Comparable<Node> {

        private final Board brd;
        private final Node parent;
        private int length;

        public Node(Board b, Node p) {
            brd = b;
            parent = p;
            length = 0;
        }

        @Override
        public int compareTo(Node o) {
            return brd.manhattan() - o.brd.manhattan();
        }

        public Iterable<Board> steps() {
            List<Board> step = new ArrayList<Board>();
            Node n = this;
            length = -1;

            while (n != null) {
                step.add(n.brd);
                n = n.parent;
                length++;
            }

            return step;
        }

        @Override
        public String toString() {
            return brd.toString();
        }

    }
}
