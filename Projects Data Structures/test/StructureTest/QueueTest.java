/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest;

import structures.Queue;

/**
 *
 * @author cbarnum18
 */
public class QueueTest {
    
    public static void main(String[] args) {
        Queue<String> q = new Queue();
        q.enqueue("Hello");
        q.enqueue("There");
        System.out.println(q.dequeue());
        q.enqueue("Dear");
        q.enqueue("Patron");
        
        for(String s : q){
            System.out.println((String) s);
        }
    }
}
