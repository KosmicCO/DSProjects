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
public class QuickFind extends DisjointUnionSet {
    private int recentAccess = 0;

    public QuickFind(int size) {
        super(size);
    }

    @Override
    protected int find(int i) {
        recentAccess++;
        return id[i];
    }

    @Override
    public boolean union(int i, int j) {
        recentAccess = 2;
        int change = id[j];
        int from = id[i];
        if(change == from) return false;
        for (int k = 0; k < size; ++k) {
            if(id[k] == from){
                id[k] = change;
                recentAccess++;
            }
            recentAccess++;
        }
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
