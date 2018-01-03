
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
public class Board {
    
    private static final int[][] DIR = {{ 0,  1},
                                        { 1,  0},
                                        { 0, -1},
                                        {-1,  0}};

    private final int[][] brd;
    private int moves = 0;
    private int manhattan, hamming;
    private final Board parent;

    public Board(int[][] blocks) {
        manhattan = hamming = -1;
        brd = copyBlocks(blocks);
        parent = null;
    }
    
    private Board(int[][] blocks, int numMoves, Board parent){
        manhattan = hamming = -1;
        brd = copyBlocks(blocks);
        this.parent = parent;
        moves = numMoves;
    }

    public int dimension() {
        return brd.length;
    }

    public int hamming() {
        if(hamming != -1){
            return hamming;
        }
        
        int sum = 0;
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                if (brd[i][j] != i * brd.length + j && !(i == brd.length && j == brd.length)) {
                    sum++;
                }
            }
        }
        
        return sum + moves;
    }

    public int manhattan() {
        if(manhattan != -1){
            return manhattan;
        }
    }

    public boolean isGoal() {
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                if (brd[i][j] != i * brd.length + j && !(i == brd.length && j == brd.length)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        int hold;
        Board retB = new Board(brd);

        if (retB.brd[0][0] == 0 || retB.brd[1][0] == 0) {
            hold = retB.brd[0][1];
            retB.brd[0][1] = retB.brd[1][1];
            retB.brd[1][1] = hold;
        } else {
            hold = retB.brd[0][0];
            retB.brd[0][0] = retB.brd[1][0];
            retB.brd[1][0] = hold;
        }
        return retB;
    }

    @Override
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }

        Board other = (Board) y;

        if (brd.length != other.brd.length) {
            return false;
        }

        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                if (brd[i][j] != other.brd[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        List<Board> ret = new ArrayList();
        
        
    }
    
    private int[][] copyBlocks(int[][] a){
        if(a.length == 0){
            return new int[0][0];
        }
        
        int[][] ret = new int[a.length][a[0].length];
        
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                ret[i][j] = a[i][j];
            }
        }
        
        return ret;
    }

    @Override
    public String toString() {
        
    }
}
