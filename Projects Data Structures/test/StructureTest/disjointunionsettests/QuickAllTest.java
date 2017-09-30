/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest.disjointunionsettests;

import structures.disjointunionsets.QuickAll;

/**
 *
 * @author Kosmic
 */
public class QuickAllTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuickAll qa = new QuickAll(10);
        qa.union(9, 0);
        System.out.println(qa);
        qa.union(3, 4);
        System.out.println(qa);
        qa.union(5, 8);
        System.out.println(qa);
        qa.union(7, 2);
        System.out.println(qa);
        qa.union(2, 1);
        System.out.println(qa);
        qa.union(5, 7);
        System.out.println(qa);
        qa.union(0, 3);
        System.out.println(qa);
        qa.union(4, 2);
        System.out.println(qa);
    }
    
}
