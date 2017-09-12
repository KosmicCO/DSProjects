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
 * @param <E>
 */
public class Bag<E> implements Cloneable, Iterable {

    private Node<E> startNode;
    private int length;
    
    public Bag(){
        startNode = null;
        length = 0;
    }
    
    public void add(E item){
        startNode = new Node(startNode, item);
        length++;
    }
    
    @Override
    public Iterator iterator() {

        return new BagIterator(startNode);
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
    
    private class BagIterator<E> implements Iterator {

        private Node next;
        
        public BagIterator(Node start){
            next = start;
        }
        
        @Override
        public boolean hasNext() {
            return next.getNext() != null;
        }

        @Override
        public E next() {
            Object ret = next.getData();
            next = next.getNext();
            return (E) ret;
        }
        
    }
}
