
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Object[] dataList;
    private int size = 0;
    private int map = 0xFFFFFFFF;
    
    public RandomizedQueue() {
        dataList = new Object[0];
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return size <= 0;
    }                 // is the randomized queue empty?

    public int size() {
        return size;
    }                      // return the number of items on the randomized queue

    public void enqueue(Item item) {
        
    }     // add the item

    public Item dequeue() {
    }      // remove and return a random item

    public Item sample() {
    }         // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
    }        // return an independent iterator over items in random order

    public static void main(String[] args) {
    }  // unit testing (optional)
}
