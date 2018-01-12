
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;


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
        
        if(initial == null){
            throw new IllegalArgumentException();
        }
        
        MinPQ<Node> queue = new MinPQ<Node>();

        Node next = new Node(initial, null);
        queue.insert(new Node(initial.twin(), null));

        while (!next.brd.isGoal()) {

            for (Board neighbor : next.brd.neighbors()) {
                if (!(next.parent != null && next.parent.brd.equals(neighbor))) {
                    queue.insert(new Node(neighbor, next));
                }
            }

            next = queue.delMin();
        }

        steps = next.steps(initial);
        solvable = steps != null;
        moves = solvable ? next.length : -1;
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
            if (p == null) {
                length = 0;
            } else {
                length = p.length + 1;
            }
        }

        @Override
        public int compareTo(Node other) {
            return brd.manhattan() + length - other.brd.manhattan() - other.length;
        }

        public Iterable<Board> steps(Board initial) {
            LinkedStack<Board> step = new LinkedStack<Board>();
            Node n = this;

            while (n != null) {
                step.push(n.brd);
                n = n.parent;
            }

            if (step.peek().equals(initial)) {
                return step;
            }

            return null;
        }

        @Override
        public String toString() {
            return brd.toString();
        }

    }
}
