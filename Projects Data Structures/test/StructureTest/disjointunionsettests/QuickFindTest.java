/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest.disjointunionsettests;

import structures.disjointunionsets.QuickFind;

/**
 *
 * @author Kosmic
 */
public class QuickFindTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuickFind qf = new QuickFind(10);
        qf.union(9, 0);
        System.out.println(qf);
        qf.union(3, 4);
        System.out.println(qf);
        qf.union(5, 8);
        System.out.println(qf);
        qf.union(7, 2);
        System.out.println(qf);
        qf.union(2, 1);
        System.out.println(qf);
        qf.union(5, 7);
        System.out.println(qf);
        qf.union(0, 3);
        System.out.println(qf);
        qf.union(4, 2);
        System.out.println(qf);
    }
    
}
