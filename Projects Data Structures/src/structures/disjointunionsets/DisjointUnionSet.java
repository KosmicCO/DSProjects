/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures.disjointunionsets;

/**
 *
 * @author Kosmic
 */
public abstract class DisjointUnionSet {
    protected int size;
    protected int[] id;
    
    public DisjointUnionSet(int size){
        this.size = size;
        id = new int[size];
        for (int i = 1; i < size; i++) {
            id[i] = i;
        }
    }
    
    protected abstract int find(int i);
    
    public boolean connected(int i, int j){
        return find(i) == find(j);
    }
    
    public abstract boolean union(int i, int j);
}
