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
public class Queue<E> implements Iterable<E> {

    private Node<E> firstNode;
    private Node<E> addNode;
    private int size;

    public Queue() {
        firstNode = addNode = null;
        size = 0;
    }

    public void enqueue(E item) {
        if (firstNode == null) {
            firstNode = addNode = new Node(null, item);
        } else {
            Node<E> next = new Node(null, item);
            addNode.setNext(next);
            addNode = next;
        }
        ++size;
    }

    public E dequeue() {
        if (size > 0) {
            Object ret = firstNode.getData();
            firstNode = firstNode.getNext();
            --size;
            return (E) ret;
        }
        return null;
    }
    
    public int getSize(){
        return size;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator(this);
    }
    
    private class QueueIterator<E> implements Iterator<E> {
        
        private Queue<E> queue;
        
        public QueueIterator(Queue<E> queue){
            this.queue = queue;
        }

        @Override
        public boolean hasNext() {
            return queue.size > 0;
        }

        @Override
        public E next() {
            return queue.dequeue();
        }
    }
}
