
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
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

    private final boolean[] vChecked;
    private final boolean[] wChecked;
    private final int[] vDist;
    private final int[] wDist;
    private final Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph g) {
        graph = new Digraph(g);
        vChecked = new boolean[g.V()];
        wChecked = new boolean[g.V()];
        vDist = new int[g.V()];
        wDist = new int[g.V()];
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

        int anc = ancestor(v, w);

        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(graph, w);

        if (anc == -1) {
            return -1;
        }

        int vs = bfdpV.distTo(anc);
        int ws = bfdpW.distTo(anc);
        return vs + ws;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException();
        }

        wipe(vChecked);
        wipe(wChecked);
        wipe(vDist);
        wipe(wDist);

        Queue<Integer> bfsQueue = new Queue<>();
        for (int i : v) {
            bfsQueue.enqueue(i);
            vChecked[i] = true;
            vDist[i] = 0;
        }

        for (int i : w) {
            if (vChecked[i]) {
                return i;
            }
            bfsQueue.enqueue(-(i + 1));
            wChecked[i] = true;
            wDist[i] = 0;
        }

        int sa = -1;
        int dist = Integer.MAX_VALUE;
        int cur;

        while (!bfsQueue.isEmpty()) {
            cur = bfsQueue.dequeue();
            if (cur < 0) {
                cur = -(cur + 1);
                for (int i : graph.adj(cur)) {
                    if (wChecked[i]) {
                        continue;
                    }
                    wChecked[i] = true;
                    wDist[i] = wDist[cur] + 1;
                    if (vChecked[i]) {
                        if (dist > vDist[i] + wDist[i]) {
                            dist = vDist[i] + wDist[i];
                            sa = i;
                        }
                    }
                    bfsQueue.enqueue(-(i + 1));
                }
            } else {
                for (int i : graph.adj(cur)) {
                    if (vChecked[i]) {
                        continue;
                    }
                    vChecked[i] = true;
                    vDist[i] = vDist[cur] + 1;
                    if (wChecked[i]) {
                        if (dist > vDist[i] + wDist[i]) {
                            dist = vDist[i] + wDist[i];
                            sa = i;
                        }
                    }
                    bfsQueue.enqueue(i);
                }
            }

        }
        return sa;

    }

    private static void wipe(boolean[] array) {
        Arrays.fill(array, 0, 0, false);
    }

    private static void wipe(int[] array) {
        Arrays.fill(array, 0, 0, 0);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("wordnet/digraph3.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            System.out.println("f");
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
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
