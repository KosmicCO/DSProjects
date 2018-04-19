
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
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
public class SAP {

    private static final int INC_DIST = -1; // must be a negative number

    private final Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph g) {
        graph = new Digraph(g);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return length(() -> new SingleIterator(v), () -> new SingleIterator(w));
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return ancestor(() -> new SingleIterator(v), () -> new SingleIterator(w));
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }

        int[] vD = dists(v);
        int[] wD = dists(w);

        boolean ret = false;
        int len = Integer.MAX_VALUE;

        for (int i = 0; i < vD.length; i++) {
            int pl;
            if (vD[i] != -1 && wD[i] != -1) {
                pl = vD[i] + wD[i];
                if (pl < len) {
                    ret = true;
                    len = pl;
                }
            }
        }

        return ret ? len : -1;
    }

    private int[] dists(Iterable<Integer> v) {
        int[] dists = new int[graph.V()];
        wipeN(dists);
        Queue<Integer> q = new Queue<>();

        for (int i : v) {
            if (i < 0 || i >= graph.V()) {
                throw new IllegalArgumentException();
            }
            dists[i] = 0;
            q.enqueue(i);
        }

        int cur;
        while (!q.isEmpty()) {
            cur = q.dequeue();
            for (int i : graph.adj(cur)) {
                if (dists[i] == -1) {
                    dists[i] = dists[cur] + 1;
                    q.enqueue(i);
                }
            }
        }
        return dists;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }

        int[] vD = dists(v);
        int[] wD = dists(w);

        int vert = -1;
        int len = Integer.MAX_VALUE;

        for (int i = 0; i < vD.length; i++) {
            int pl;
            if (vD[i] != -1 && wD[i] != -1) {
                pl = vD[i] + wD[i];
                if (pl < len) {
                    vert = i;
                    len = pl;
                }
            }
        }

        return vert;
    }

    private static void wipeN(int[] array) {
        Arrays.fill(array, 0, array.length, -1);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("wordnet/digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int v = 3;
        int w = 8;
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }

    private class SingleIterator implements Iterator<Integer> {

        boolean checked;
        int val;

        public SingleIterator(int v) {
            val = v;
            checked = false;
        }

        @Override
        public boolean hasNext() {
            if (!checked) {
                checked = true;
                return true;
            }
            return false;
        }

        @Override
        public Integer next() {
            checked = true;
            return val;
        }
    }
}
