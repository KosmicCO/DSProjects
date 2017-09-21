/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest;

import structures.Stack;

/**
 *
 * @author cbarnum18
 */
public class StackTest {
    
    public static void main(String[] args) {
        Stack<String> s = new Stack();
        s.push("Patron");
        s.push("Hello");
        System.out.println((String) s.pop());
        s.push("Dear");
        s.push("There");
        for(String p : s){
            System.out.println((String) p);
        }
        
    }
}
