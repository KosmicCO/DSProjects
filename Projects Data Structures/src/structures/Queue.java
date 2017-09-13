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
public class Queue<E> implements Cloneable, Iterable {

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
            addNode.setNext(new Node(null, item));
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
    
    @Override
    public Queue<E> clone(){
        return null;
    }
    
    public int getSize(){
        return size;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator(this);
    }
    
    private class QueueIterator<E> implements Iterator{
        
        private Queue queue;
        
        public QueueIterator(Queue queue){
            this.queue = queue;
        }

        @Override
        public boolean hasNext() {
            return queue.size > 0;
        }

        @Override
        public Object next() {
            return queue.dequeue();
        }
        
        
    }
}
