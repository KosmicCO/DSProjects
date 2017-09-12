/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bag_stack_queue;

import java.util.Iterator;

/**
 *
 * @author cbarnum18
 */
public class Stack<E> implements Cloneable, Iterable {
    
    private Node prev;
    private int length;
    
    @Override
    public Iterator iterator(){
        return null;
    }
    
    private class Node<E> {

        private final Node next;
        private final E data;

        public Node(Node next, E data) {
            this.next = next;
            this.data = data;
        }
        
        public E getData(){
            return data;
        }
        
        public Node getNext(){
            return next;
        }
    }
}
