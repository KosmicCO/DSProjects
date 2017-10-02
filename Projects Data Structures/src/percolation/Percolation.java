/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percolation;

import structures.disjointunionsets.QuickAll;

/**
 *
 * @author cbarnum18
 */
public class Percolation  {
    
    private int length;
    private boolean[][] sites;
    
    public Percolation(int length) {
        this.length = length;
        sites = new boolean[length][length];
    }
    
    public void open(int row, int col){
        
    }
    
    public boolean isOpen(int row, int col){
        return true;
    }
    
    public boolean isFull(int row, int col){
        return true;
    }
    
    public int numberOfOpenSites(){
        return 0;
    }
    
    public boolean percolates(){
        return true;
    }
}
