/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import utils.Node;

/**
 *
 * @author cbarnum18
 * @param <E>
 */
public class LinkedList<E> implements List<E> {

    private Node<E> startNode;
    private Node<E> endNode;
    private int size;

    public LinkedList() {
        startNode = endNode = null;
        size = 0;
    }

    //For Grading Purposes (renamed)
    public void delete(int k) {
        remove(k);
    }

    public boolean find(String key) {
        return contains(key);
    }
    
    public void removeAfter(Node<E> node){
        if(node.getNext() != null){
            node.setNext(node.getNext().getNext());
        }
    }
    
    public void insertAfter(Node<E> pre, Node<E> ins){
        if(pre != null && ins != null){
            ins.setNext(pre.getNext());
            pre.setNext(ins);
        }
    }
    
    public void insertFirst(E item){
        add(0, item);
    }
    
    public void insertLast(E item){
        add(size, item);
    }
    
    public E removeFirst(){
        return remove(0);
    }
    
    public E removeLast(){
        return remove(size - 1);
    }
    //end of grading renamed methods

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
    
    private Node<E> getNodeAtIndex(int i){
        Node<E> curNode = startNode;
        for (int j = 0; j < i; j++) {
            Node<E> next = curNode.getNext();
            if (next != null) {
                curNode = next;
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return curNode;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> curNode = startNode;
        while (curNode != endNode) {
            if (curNode.getData() == o) {
                return true;
            }
            curNode = curNode.getNext();
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int count = 0;
        for (E item : this) {
            array[count] = item;
            count++;
        }
        return array;
    }

    @Override
    public Object[] toArray(Object[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(E e) {
        if (endNode == null) {
            startNode = endNode = new Node(null, e);
        } else {
            endNode.setNext(new Node(null, e));
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> prev = startNode;
        if (prev != null) {
            while (prev != endNode && prev.getNext().getData() != o) {
                prev = prev.getNext();
            }
        }
        if (prev != endNode) {
            prev.setNext(prev.getNext().getNext());
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection clctn) {
        return clctn.stream().noneMatch((o) -> (!contains(o)));
    }

    @Override
    public boolean addAll(Collection clctn) {
        clctn.forEach((e) -> {
            this.add((E) e);
        });
        size += clctn.size();
        return true;
    }

    @Override
    public boolean addAll(int i, Collection clctn) {
        if(i < 0 || i > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        size += clctn.size();
        LinkedList<E> nodeGen = new LinkedList();
        clctn.forEach((o) -> nodeGen.add((E) o));
        if(i == 0){
            nodeGen.endNode.setNext(startNode);
            startNode = nodeGen.startNode;
            return true;
        }
        if(i == size){
            endNode = nodeGen.endNode;
        }
        Node<E> insert = getNodeAtIndex(i - 1);
        Node<E> next = insert.getNext();
        insert.setNext(nodeGen.startNode);
        nodeGen.endNode.setNext(next);
        return true;
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
            if (next != null) {
                curNode = next;
            } else {
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
            if (next != null) {
                curNode = next;
            } else {
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
            if (next != null) {
                curNode = next;
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        if (i == 0) {
            startNode = new Node(startNode, e);
        } else {
            Node<E> rest = curNode.getNext();
            curNode.setNext(new Node(rest, e));
        }

        if (endNode.getNext() != null) {
            endNode = endNode.getNext();
        }
        size++;
    }

    @Override
    public E remove(int i) {
        Node<E> curNode = startNode;
        for (int j = 0; j < i - 1; j++) {
            Node<E> next = curNode.getNext();
            if (next != null) {
                curNode = next;
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        Node<E> del = curNode.getNext();
        if (del != null) {
            curNode.setNext(del.getNext());
            size--;
            return del.getData();
        }
        throw new ArrayIndexOutOfBoundsException();
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
        return new LinkedListIterator(this);
    }

    @Override
    public ListIterator listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class LinkedListIterator<E> implements ListIterator<E> {

        private Node<E> curNode;
        private final LinkedList<E> list;
        private int index;

        public LinkedListIterator(LinkedList<E> list) {
            this.list = list;
            curNode = list.startNode;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < list.size;
        }

        @Override
        public E next() {
            E ret = curNode.getData();
            curNode = curNode.getNext();
            index++;
            return ret;
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
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unsupportable."); //To change body of generated methods, choose Tools | Templates.

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
