
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class Percolation {

    private static final int[][] ADJACENT = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private final WeightedQuickUnionUF orig;
    private final WeightedQuickUnionUF copy;
    private final int length;
    private final boolean[][] sites;
    private final int numSites;
    private int openLeft;

    private final int VTOP;
    private final int VBOT;

    public Percolation(int length) {
        if (0 >= length) {
            throw new IllegalArgumentException();
        }
        this.length = length;
        numSites = openLeft = length * length;
        orig = new WeightedQuickUnionUF(numSites + 2);
        copy = new WeightedQuickUnionUF(numSites + 1);
        sites = new boolean[length][length];

        VTOP = length * length;
        VBOT = length * length + 1;
    }

    public void open(int row, int col) {
        if (0 >= row || 0 >= col || row > length || col > length) {
            throw new IllegalArgumentException();
        }
        row--;
        col--;
        if (!sites[row][col]) {
            openLeft--;
            sites[row][col] = true;
            int node = flatten(row, col);
            for (int[] next : ADJACENT) {
                int nRow = row + next[0];
                int nCol = col + next[1];
                if (nCol < length && nCol >= 0) {
                    if (nRow < 0) {
                        orig.union(node, VTOP);
                        copy.union(node, VTOP);
                    } else if (nRow >= length) {
                        orig.union(node, VBOT);
                    } else {
                        if (sites[nRow][nCol]) {
                            int ftnd = flatten(nRow, nCol);
                            orig.union(node, ftnd);
                            copy.union(node, ftnd);
                        }
                    }
                }
            }
        }
    }

    private int flatten(int row, int col) {
        return row + length * col;
    }

    public boolean isOpen(int row, int col) {
        if (0 >= row || 0 >= col || row > length || col > length) {
            throw new IllegalArgumentException();
        }
        row--;
        col--;
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if (0 >= row || 0 >= col || row > length || col > length) {
            throw new IllegalArgumentException();
        }
        row--;
        col--;
        return copy.connected(flatten(row, col), VTOP);
    }

    public int numberOfOpenSites() {
        return numSites - openLeft;
    }

    public boolean percolates() {
        return orig.connected(VTOP, VBOT);
    }
}
