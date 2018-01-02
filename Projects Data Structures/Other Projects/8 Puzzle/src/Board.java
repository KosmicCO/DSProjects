
import java.util.Arrays;

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
    
    private int[][] brd;
    
    public Board(int[][] blocks){
        brd = new int[blocks.length][blocks.length];
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                brd[i][j] = blocks[i][j];
            }
        }
    }
    
    public int dimension(){
        return brd.length;
    }
    
    public int hamming(){
        
    }
    
    public int manhattan(){
        
    }
    
    public boolean isGoal(){
        for (int i = 0; i < brd.length; i++) {
            for (int j = 0; j < brd.length; j++) {
                if(brd[i][j] != i * brd.length + j || (i == brd.length && j == brd.length)){
                    
                }
            }
        }
    }
    
    public Board twin(){
        
    }
    
    public boolean equals(Object y){
        
    }
    
    public Iterable<Board> neighbors(){
        
    }
    
    @Override
    public String toString(){
        
    }
}
