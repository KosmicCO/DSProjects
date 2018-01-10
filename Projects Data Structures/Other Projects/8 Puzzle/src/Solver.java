
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

    private int moves;
    private boolean solvable;
    private final List<Board> steps;

    public Solver(Board initial) {
        steps = new ArrayList();
        MinPQ<Board> regQueue = new MinPQ();
        MinPQ<Board> othQueue = new MinPQ();

        Board next = initial;
        Board nTwn = initial.twin();

        while (!(next.isGoal() || nTwn.isGoal())) {
            for (Board neighbor : next.neighbors()) {
                regQueue.insert(neighbor);
            }

            for (Board neighbor : nTwn.neighbors()) {
                othQueue.insert(neighbor);
            }

            next = regQueue.delMin();
            nTwn = othQueue.delMin();
            moves++;
        }

        System.out.println(next);
        System.out.println(nTwn);
        System.out.println(moves);
    }

    public boolean isSolvable() {
        return false;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return null;
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
//        if (!solver.isSolvable()) {
//            StdOut.println("No solution possible");
//        } else {
//            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution()) {
//                StdOut.println(board);
//            }
//        }
    }
}
