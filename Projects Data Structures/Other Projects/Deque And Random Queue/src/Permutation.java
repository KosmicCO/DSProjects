
import edu.princeton.cs.algs4.StdIn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class Permutation {

    public static void main(String[] args) {
        int out = 100;
        if (args.length == 1) {
            out = Integer.parseInt(args[0]);
        }
        RandomizedQueue<String> rq = new RandomizedQueue();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        for (int i = 0; i < out; i++) {
            System.out.println(rq.dequeue());
        }
    }
}
