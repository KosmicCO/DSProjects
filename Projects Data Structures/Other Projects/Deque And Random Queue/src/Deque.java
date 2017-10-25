
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 *
 * @author cbarnum18
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> secend;
    private int size;

    public Deque() {
    }                           // construct an empty deque

    public boolean isEmpty() {
        return size <= 0;
    }                 // is the deque empty?

    public int size() {
        return size;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        switch (size) {
            case 0:
                secend = new Node(null, item);
                secend.next = secend;
                break;
            case 1:
                secend.next = new Node(secend, item);
                secend = secend.next;
                break;
            default:
                secend.next.next = new Node(secend.next.next, item);
                break;
        }
        size++;
    }          // add the item to the front

    public void addLast(Item item) {
        switch (size) {
            case 0:
                secend = new Node(null, item);
                secend.next = secend;
                break;
            case 1:
                secend.next.next = new Node(secend.next.next, item);
                break;
            default:
                secend.next.next = new Node(secend.next.next, item);
                secend = secend.next;
                break;
        }
        size++;
    }           // add the item to the end

    public Item removeFirst() {
        Item data;
        switch (size) {
            case 0:
                throw new NoSuchElementException();
            case 1:
                data = secend.data;
                secend = null;
                break;
            default:
                data = secend.next.next.data;
                secend.next.next = secend.next.next.next;
                break;
        }
        size--;
        return data;
    }                // remove and return the item from the front

    public Item removeLast() {
        Item data;
        switch(size){
            case 0:
                throw new NoSuchElementException();
            case 1:
                data = secend.data;
                secend = null;
                break;
            default:
                data = secend.next.data;
                secend.next = secend.next.next;
        }
        size--;
        return data;
    }                 // remove and return the item from the end

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator(this);
    }    // return an iterator over items in order from front to end

    public static void main(String[] args) {
    }   // unit testing (optional)

    private class Node<Item> {

        private Node<Item> next;
        private Item data;

        public Node(Node<Item> next, Item data) {
            this.next = next;
            this.data = data;
        }
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        private final Deque<Item> deque;
        
        public DequeIterator(Deque<Item> dq){
            deque = dq;
        }
        
        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            return deque.removeFirst();
        }
    }
}
