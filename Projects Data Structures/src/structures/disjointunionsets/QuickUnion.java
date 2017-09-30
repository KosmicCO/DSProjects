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
public class QuickUnion extends DisjointUnionSet {

    private int recentAccess = 0;
    
    public QuickUnion(int size) {
        super(size);
    }

    @Override
    protected int find(int i) {
        int k = i;
        while(k != id[k]){
            k = id[k];
            recentAccess += 2;
        }
        recentAccess++;
        return k;
    }

    @Override
    public boolean union(int i, int j) {
        recentAccess = 0;
        int k = find(i);
        if(k == j) return false;
        id[k] = j;
        recentAccess++;
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
