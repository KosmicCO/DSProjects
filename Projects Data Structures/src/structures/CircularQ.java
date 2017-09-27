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
 * A linked-list implementation of a circular queue, where one instance var last
 * always points to the last node
 *
 * @author cruz
 * @param <E>
 */
public class CircularQ<E> implements Iterable<E> {

    private Node<E> last = null;
    private int size = 0;

    @Override
    public void forEach(Consumer<? super E> action) {
        for(E item : this){
            action.accept(item);
        }
    }
    
    /**
     * add item to the beginning of the queue
     *
     * @param item
     */
    private void addFirst(E item) {
        if (last == null) {
            last = new Node(null, item);
            last.setNext(last);
        } else {
            Node<E> next = new Node(last.getNext(), item);
            last.setNext(next);
        }
        size++;
    }

    /**
     * add item to the end of the queue
     *
     * @param item
     */
    private void addLast(E item) {
        if (last == null) {
            last = new Node(null, item);
            last.setNext(last);
        } else {
            Node<E> next = new Node(last.getNext(), item);
            last.setNext(next);
            last = next;
        }
        size++;
    }

    /**
     * remove first item from the queue
     *
     * @return Item removed
     */
    private E removeFirst() {
        if (size <= 0) {
            return null;
        } else if (size == 1) {
            E data = (E) last.getData();
            last = null;
            size--;
            return data;
        }
        E data = (E) last.getNext().getData();
        last.setNext(last.getNext().getNext());
        size--;
        return data;
    }

    /**
     * remove last item from end of the queue
     *
     * @return Item removed
     */
    private E removeLast() {
        if (size <= 0) {
            return null;
        } else if (size == 1) {
            E data = (E) last.getData();
            last = null;
            size--;
            return data;
        }
        Node<E> cur = last;
        for (int i = 0; i < size - 1; i++) {
            cur = cur.getNext();
        }
        E data = (E) last.getData();
        cur.setNext(last.getNext());
        last = cur;
        size--;
        return data;
    }

    public void enqueue(E item) {
        addLast(item);
    }

    public E dequeue() {
        return removeFirst();
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator(this);
    }

    private class ListIterator<E> implements Iterator<E> {

        private final CircularQ queue;
        
        public ListIterator(CircularQ queue){
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
    
    @Override
    public String toString(){
        String s = "{";
        Node<E> cur = last.getNext();
        for (int i = 0; i < size; i++) {
            if(i > 0){
                s += ", ";
            }
            s += cur.getData();
            cur = cur.getNext();
        }
        return s + "}";
    }
    
    public static void main(String[] args) {
        CircularQ<Integer> cq = new CircularQ();
        cq.addFirst(1);
        cq.addFirst(2);
        cq.addLast(3);
        cq.enqueue(4);
        // expect 2 1 3 4
        assert cq.toString().equals("{2, 1, 3, 4}");
        assert cq.removeFirst() == 2;
        assert cq.removeLast() == 4;
        assert cq.dequeue() == 1;
        assert cq.dequeue() == 3;
        assert cq.toString().equals("{}");// successful
    }
}
