/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import utils.Node;

/**
 *
 * @author cbarnum18
 * @param <E>
 */
public class LinkedList<E> implements Cloneable, Iterable<E>, List<E>{

    private Node<E> startNode;
    private Node<E> endNode;
    private int size;
    
    public LinkedList(){
        startNode = endNode = null;
        size = 0;
    }
    
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray(Object[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(E e) {
        if(endNode == null){
            startNode = endNode = new Node(null, e);
        }else{
            endNode.setNext(new Node(null, e));
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection clctn) {
        clctn.forEach((e) -> {
            this.add((E) e);
        });
        return true;
    }

    @Override
    public boolean addAll(int i, Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        startNode = endNode = null;
        size = 0;
    }

    @Override
    public E get(int i) {
        Node<E> curNode = startNode;
        for (int j = 0; j < i; j++) {
            Node<E> next = curNode.getNext();
            if(next != null){
                curNode = next;
            }else{
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return curNode.getData();
    }

    @Override
    public E set(int i, E e) {
        Node<E> curNode = startNode;
        for (int j = 0; j < i; j++) {
            Node<E> next = curNode.getNext();
            if(next != null){
                curNode = next;
            }else{
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        curNode.setData((E) e);
        return e;
    }

    @Override
    public void add(int i, E e) {
        Node<E> curNode = startNode;
        for (int j = 0; j < i - 1; j++) {
            Node<E> next = curNode.getNext();
            if(next != null){
                curNode = next;
            }else{
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        if(i == 0){
            startNode = new Node(startNode, e);
        }else{
            Node<E> rest = curNode.getNext();
            curNode.setNext(new Node(rest, e));
        }
        
        if(endNode.getNext() != null){
            endNode = endNode.getNext();
        }
    }

    @Override
    public E remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class LinkedListIterator<E> implements Iterator<E>, ListIterator<E>{

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException("Unsupportable."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
