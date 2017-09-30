/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest.disjointunionsettests;

import structures.disjointunionsets.QuickUnion;

/**
 *
 * @author Kosmic
 */
public class QuickUnionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(10);
        qu.union(9, 0);
        System.out.println(qu);
        qu.union(3, 4);
        System.out.println(qu);
        qu.union(5, 8);
        System.out.println(qu);
        qu.union(7, 2);
        System.out.println(qu);
        qu.union(2, 1);
        System.out.println(qu);
        qu.union(5, 7);
        System.out.println(qu);
        qu.union(0, 3);
        System.out.println(qu);
        qu.union(4, 2);
        System.out.println(qu);
    }
    
}
