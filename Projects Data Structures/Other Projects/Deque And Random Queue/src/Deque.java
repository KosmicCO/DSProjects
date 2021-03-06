
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

    private Node<Item> last;
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
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (last == null) {
            last = new Node<Item>(null, null, item);
            last.prev = last;
            last.next = last;
        } else {
            Node<Item> nn = new Node<Item>(last, last.next, item);
            last.next = nn;
            nn.next.prev = nn;
        }
        size++;
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (last == null) {
            last = new Node<Item>(null, null, item);
            last.prev = last;
            last.next = last;
        } else {
            Node<Item> nn = new Node<Item>(last, last.next, item);
            last.next.prev = nn;
            last.next = nn;
            last = nn;
        }
        size++;
    }           // add the item to the end

    public Item removeFirst() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item rn;
        if (size == 1) {
            rn = last.data;
            last = null;
        } else {
            rn = last.next.data;
            last.next = last.next.next;
            last.next.prev = last;
        }
        size--;
        return rn;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item rn;
        if (size == 1) {
            rn = last.data;
            last = null;
        } else {
            rn = last.data;
            last = last.prev;
            last.next = last.next.next;
            last.next.prev = last;
        }
        size--;
        return rn;
    }                 // remove and return the item from the end

    @Override
    public Iterator<Item> iterator() {
        if (last == null) {
            return new DequeIterator<Item>(null, 0);
        }
        return new DequeIterator<Item>(last.next, size);
    }    // return an iterator over items in order from front to end

    public static void main(String[] args) {
//        Deque<Integer> dq = new Deque();
//        for (int i = 0; i < 100; i++) {
//            dq.addFirst(i);
//            System.out.println("++ " + i);
//            if (StdRandom.bernoulli(0.2)) {
//                System.out.println("-- " + dq.removeLast());
//            }
//        }

    }   // unit testing (optional)

    private class Node<Item> {

        private Node<Item> next;
        private Node<Item> prev;
        private final Item data;

        public Node(Node<Item> prev, Node<Item> next, Item data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> nextData;
        private int size;

        public DequeIterator(Node<Item> next, int size) {
            nextData = next;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            size--;
            Item rn = nextData.data;
            nextData = nextData.next;
            return rn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
