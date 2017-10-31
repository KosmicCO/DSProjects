
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] dataList;
    private int size = 0;
    private int power = 0;

    public RandomizedQueue() {
        dataList = (Item[]) new Object[1];
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return size <= 0;
    }                 // is the randomized queue empty?

    public int size() {
        return size;
    }                      // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (size >= (0x1 << power)) {
            dataList = Arrays.copyOf(dataList, (0x1) << (++power));
        }
        dataList[size++] = item;
    }     // add the item

    private void swapEnd(int index) {
        dataList[index] = dataList[size - 1];
        dataList[size - 1] = null;
    }

    public Item dequeue() {
        int rand = StdRandom.uniform(size);
        Item ret = dataList[rand];
        swapEnd(rand);
        if (size < (0x1 << (power - 2))) {
            dataList = Arrays.copyOf(dataList, (0x1 << (--power)));
        }
        return ret;
    }      // remove and return a random item

    public Item sample() {
        return dataList[StdRandom.uniform(size)];
    }         // return a random item (but do not remove it)

    @Override
    public Iterator<Item> iterator() {
        return new RandQueueIterator<Item>(this);
    }        // return an independent iterator over items in random order

    public static void main(String[] args) {
//        RandomizedQueue<Integer> rq = new RandomizedQueue();
//        for (int i = 0; i < 100; i++) {
//            rq.enqueue(i);
//        }
//        for (int i : rq) {
//            System.out.println(i);
//        }
//        rq.enqueue(Integer.SIZE);
    }  // unit testing (optional)

    private class RandQueueIterator<Item> implements Iterator<Item> {

        private final int[] inds;
        private int size;
        private final RandomizedQueue<Item> rq;

        public RandQueueIterator(RandomizedQueue<Item> rq) {
            size = rq.size;
            this.rq = rq;
            inds = new int[size];
            Arrays.setAll(inds, i -> i);
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
            int rand = StdRandom.uniform(size);
            int nind = inds[rand];
            inds[rand] = inds[--size];
            return rq.dataList[nind];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
