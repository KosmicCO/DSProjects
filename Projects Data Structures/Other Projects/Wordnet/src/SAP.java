
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
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
    private final boolean[] vVerts;
    private final boolean[] wVerts;
    private Digraph graph;
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph g) {
        graph = copyDigraph(g);
        vChecked = new boolean[g.V()];
        wChecked = new boolean[g.V()];
        vVerts = new boolean[g.V()];
        wVerts = new boolean[g.V()];
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
    public synchronized int length(Iterable<Integer> v, Iterable<Integer> w) {
        wipe(vChecked);
        wipe(wChecked);
        wipe(vVerts);
        wipe(wVerts);
        Queue<Integer> vQueue = new Queue();
        Queue<Integer> wQueue = new Queue();
        for(int i : v){
            vVerts[i] = true;
            vChecked[i] = true;
            vQueue.enqueue(i);
        }
        for(int i : w){
            if(vVerts[i]){
                return 0;
            }
            wVerts[i] = true;
            wChecked[i] = true;
            wQueue.enqueue(i);
        }
        vQueue.enqueue(INC_DIST);
        wQueue.enqueue(INC_DIST);
        int cur;
        int vDist = 0;
        int wDist = 0;
        boolean vPrev = false;
        boolean wPrev = false;
        while(!(vQueue.isEmpty() && wQueue.isEmpty())){
            if(!vQueue.isEmpty()){
                cur = vQueue.dequeue();
                if(cur == INC_DIST){
                    ++vDist;
                    if(!vPrev){
                        vQueue.enqueue(INC_DIST);
                    }
                }else{
                    vPrev = false;
                    for(int i : graph.adj(cur)){
                        if(vChecked[i]) continue;
                        if(wVerts[i]) return vDist;
                        vChecked[i] = true;
                        vQueue.enqueue(i);
                    }
                }
            }
            
            if(!wQueue.isEmpty()){
                cur = wQueue.dequeue();
                if(cur == INC_DIST){
                    ++wDist;
                    if(!wPrev){
                        wQueue.enqueue(INC_DIST);
                    }
                }else{
                    wPrev = false;
                    for(int i : graph.adj(cur)){
                        if(wChecked[i]) continue;
                        if(vVerts[i]) return wDist;
                        wChecked[i] = true;
                        wQueue.enqueue(i);
                    }
                }
            }
        }
        
        return -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public synchronized int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        wipe(vChecked);
        wipe(wChecked);
        Queue<Integer> bfsQueue = new Queue();
        for(int i : v){
            bfsQueue.enqueue(i);
            vChecked[i] = true;
        }
        for(int i : w){
            if(vChecked[i])return i;
            bfsQueue.enqueue(-(i + 1));
            wChecked[i] = true;
        }
        int cur;
        while(!bfsQueue.isEmpty()){
            cur = bfsQueue.dequeue();
            if(cur < 0){
                cur = -(cur + 1);
                for(int i : graph.adj(cur)){
                    if(wChecked[i]) continue;
                    if(vChecked[i]) return i;
                    bfsQueue.enqueue(-(i + 1));
                    wChecked[i] = true;
                }
            }else{
                for(int i : graph.adj(cur)){
                    if(vChecked[i]) continue;
                    if(wChecked[i]) return i;
                    bfsQueue.enqueue(i);
                    vChecked[i] = true;
                }
            }
        }
        return -1;
    }
    
    private static void wipe(boolean[] array){
        Arrays.fill(array, 0, 0, false);
    }
    
    private static Digraph copyDigraph(Digraph g){
        Digraph copy = new Digraph(g.V());
        int len = g.V();
        for (int i = 0; i < len; ++i) {
            for(int j : g.adj(i)) copy.addEdge(i, j);
        }
        return copy;
    }

    // do unit testing of this class
    public static void main(String[] args) {

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
