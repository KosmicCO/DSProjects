
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
public class Percolation extends WeightedQuickUnionUF {
    
    private static final int[][] ADJACENT = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    
    private final int length;
    private final boolean[][] sites;
    private int openLeft;
    
    private final int VTOP;
    private final int VBOT;
    
    public Percolation(int length) {
        super(length * length + 2);
        this.length = length;
        openLeft = length * length;
        sites = new boolean[length][length];
        
        VTOP = length * length;
        VBOT = length * length + 1;
    }
    
    public void open(int row, int col){
        row--;
        col--;
        if(!sites[row][col]){
            openLeft--;
            sites[row][col] = true;
            int node = flatten(row, col);
            for(int[] next : ADJACENT){
                int nRow = row + next[0];
                int nCol = col + next[1];
                if(nCol < length && nCol >= 0){
                    if(nRow < 0){
                        union(node, VTOP);
                    }else if(nRow >= length){
                        union(node, VBOT);
                    }else{
                        union(node, flatten(nRow, nCol));
                    }
                }
            }
        }
    }
    
    private int flatten(int row, int col){
        row--;
        col--;
        return row + length * col;
    }
    
    public boolean isOpen(int row, int col){
        row--;
        col--;
        return sites[row][col];
    }
    
    public boolean isFull(int row, int col){
        row--;
        col--;
        return connected(flatten(row, col), VTOP);
    }
    
    public int numberOfOpenSites(){
        return openLeft;
    }
    
    public boolean percolates(){
        return connected(VTOP, VBOT);
    }
}
