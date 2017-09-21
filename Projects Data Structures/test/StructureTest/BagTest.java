/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest;

import structures.Bag;

/**
 *
 * @author cbarnum18
 */
public class BagTest {
    
    public static void main(String[] args) {
        Bag<String> b = new Bag();
        b.add("Hello");
        b.add("There");
        b.add("Dear");
        b.add("Patron");
        for(String c : b){
            System.out.println(c);
        }
    }
}
