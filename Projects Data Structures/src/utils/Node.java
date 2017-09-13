/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author cbarnum18
 */
public class Node<E> implements Cloneable {

    private Node next;
    private E data;

    public Node(Node next, E data) {
        this.next = next;
        this.data = data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    @Override
    public Node<E> clone() {
        return null;
    }
}
