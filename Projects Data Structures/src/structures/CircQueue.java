/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Iterator;
import java.util.function.Consumer;
import utils.Node;

/**
 *
 * @author cbarnum18
 * @param <E>
 */
public class CircQueue<E> implements Iterable<E> {

    private Node<E> endNode;
    private int size;

    public CircQueue() {
        endNode = null;
        size = 0;
    }

    public void enqueue(E item) {
        if (endNode == null) {
            endNode = new Node(null, item);
            endNode.setNext(endNode);
        } else {
            Node<E> next = new Node(endNode.getNext(), item);
            endNode.setNext(next);
            endNode = next;
        }
        size++;
    }

    public E dequeue() {
        if (size <= 0) {
            return null;
        } else if (size == 1) {
            E data = (E) endNode.getData();
            endNode = null;
            size--;
            return data;
        }
        E data = (E) endNode.getNext().getData();
        endNode.setNext(endNode.getNext().getNext());
        size--;
        return data;

    }
    
    public int getSize(){
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircQueueIterator(this);
    }

    @Override
    public void forEach(Consumer<? super E> cnsmr) {
        for(E e : this){
            cnsmr.accept(e);
        }
    }

    private class CircQueueIterator<E> implements Iterator<E> {

        private final CircQueue queue;
        
        public CircQueueIterator(CircQueue queue){
            this.queue = queue;
        }
        
        @Override
        public boolean hasNext() {
            return queue.size > 0;
        }

        @Override
        public E next() {
            return (E) queue.dequeue();
        }
    }
}
