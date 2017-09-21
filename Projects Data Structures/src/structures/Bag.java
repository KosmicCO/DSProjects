/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Iterator;
import utils.Node;

/**
 *
 * @author cbarnum18
 * @param <E>
 */
public class Bag<E> implements Iterable<E> {

    private Node<E> startNode;
    private int size;
    
    public Bag(){
        startNode = null;
        size = 0;
    }
    
    public void add(E item){
        startNode = new Node(startNode, item);
        ++size;
    }
    
    @Override
    public Iterator iterator() {

        return new BagIterator(startNode);
    }

    public int getSize(){
        return size;
    }
    
    private class BagIterator<E> implements Iterator<E> {

        private Node<E> next;
        
        public BagIterator(Node<E> start){
            next = start;
        }
        
        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            Object ret = next.getData();
            next = next.getNext();
            return (E) ret;
        }
    }
}
