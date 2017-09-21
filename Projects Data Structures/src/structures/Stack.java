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
public class Stack<E> implements Cloneable, Iterable<E> {

    private Node<E> startNode;
    private int size;

    public Stack() {
        startNode = null;
        size = 0;
    }

    public void push(E item) {
        startNode = new Node(startNode, item);
        ++size;
    }

    public E pop() {
        if (size > 0) {
            Object ret = startNode.getData();
            startNode = startNode.getNext();
            --size;
            return (E) ret;
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator(this);
    }

    private class StackIterator<E> implements Iterator<E> {

        private final Stack<E> stack;

        public StackIterator(Stack<E> start) {
            stack = start;
        }

        @Override
        public boolean hasNext() {
            return stack.size > 0;
        }

        @Override
        public E next() {
            return stack.pop();
        }
    }
}
