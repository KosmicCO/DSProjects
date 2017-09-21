/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest;

import structures.LinkedList;

/**
 *
 * @author cbarnum18
 */
public class LinkedListTest {
    
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList();
        LinkedList<String> lm = new LinkedList();
        ll.add("Hello");
        lm.add("There");
        lm.add("Dear");
        ll.add("Patron");
        ll.addAll(1, lm);
        for(String s : ll){
            System.out.println(s);
        }
    }
}
