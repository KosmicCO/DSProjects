
import java.util.ArrayList;
import java.util.Arrays;
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

    private static final int[][] DIR = {{0, 1},
    {1, 0},
    {0, -1},
    {-1, 0}};

    private final int[][] brd;
    private int moves;
    private int manhattan, hamming;
    private int zeroX, zeroY;
    private final Board parent;

    public Board(int[][] blocks) {
        brd = new int[blocks.length][blocks[0].length];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                brd[i][j] = blocks[i][j];
                if (brd[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }
        manhattan = fullManhattan();
        hamming = -1;
        hamming = fullHamming();
        parent = null;
        moves = 0;
    }

    private Board(Board parent) {
        brd = new int[parent.brd.length][parent.brd.length];

        for (int i = 0; i < parent.brd.length; i++) {
            for (int j = 0; j < parent.brd.length; j++) {
                brd[i][j] = parent.brd[i][j];
                if (brd[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }
        manhattan = hamming = -1;
        this.parent = parent;
        moves = parent.moves;
    }

    public int dimension() {
        return brd.length;
    }

    private int fullHamming() {
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

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    private int fullManhattan() {
        int sum = 0;
        int num;
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                num = brd[i][j];
                if (num == 0) {
                    sum += (i - (num % brd.length)) & 0x7FFF_FFFF + (j - (num / brd.length)) & 0x7FFF_FFFF;
                }
            }
        }
        return sum + moves;
    }

    private int moveManhattan(int x, int y, int num, int d) {
        int ind;
        if ((d & 0x0000_0001) == 1) {
            ind = num % brd.length;
            if (x > ind) {
                return -DIR[d][0];
            } else if (x < ind) {
                return DIR[d][0];
            }
        } else {
            ind = num / brd.length;
            if (y > ind) {
                return -DIR[d][0];
            } else if (y < ind) {
                return DIR[d][0];
            }
        }
        return 1;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.brd);
        return hash;
    }

    public Iterable<Board> neighbors() {
        List<Board> ret = new ArrayList();
        int x, y;
        Board work;

        for (int i = 0; i < 4; i++) {
            x = zeroX + DIR[i][0];
            y = zeroY + DIR[i][1];
            if (0 <= x && x < brd.length && 0 <= y && y <= brd.length) {
                work = new Board(this);
                work.brd[zeroX][zeroY] = work.brd[x][y];
                work.brd[x][y] = 0;
                work.manhattan
            }
        }
    }

    @Override
    public String toString() {

    }
}
