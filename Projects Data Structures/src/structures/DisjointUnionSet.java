/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author cbarnum18
 */
public class DisjointUnionSet {
    
    private int[] id;
    private int[] rank;
    
    public DisjointUnionSet(int size){
        id = new int[size];
        rank = new int[size];
    }
}
