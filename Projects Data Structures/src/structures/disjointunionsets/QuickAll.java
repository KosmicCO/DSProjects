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
public class QuickAll extends DisjointUnionSet {

    protected int[] rank;
    private int recentAccess = 0;
    
    public QuickAll(int size) {
        super(size);
        rank = new int[size];
    }

    @Override
    protected int find(int i) {
        int k = i;
        while(k != id[k]){
            id[k] = id[id[k]];
            k = id[k];
            recentAccess += 4;
        }
        recentAccess++;
        return k;
    }

    @Override
    public boolean union(int i, int j) {
        recentAccess = 0;
        int k = find(i);
        int l = find(j);
        if(k == l) return false;
        if(rank[k] > rank[l]){
            id[k] = l;
            rank[l] += rank[k];
        }else{
            id[l] = k;
            rank[k] += rank[l];
        }
        recentAccess += 5;
        return true;
    }
    
    @Override
    public String toString(){
        String s = "[";
        for (int i = 0; i < size; i++) {
            if(i != 0) s += ", ";
            s += id[i];
        }
        return s + "] " + recentAccess;
    }
}
