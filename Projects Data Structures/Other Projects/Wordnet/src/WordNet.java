
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class WordNet {

    private final String[] nouns;
    private final Map<String, Integer> nounToID;
    private final SAP sapFinder;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

        String[][] hypForm;
        nounToID = new HashMap<>();

        In synIn = new In(synsets);
        In hypIn = new In(hypernyms);

        String[] synLines = synIn.readAllLines();
        String[] hypLines = hypIn.readAllLines();

        nouns = new String[synLines.length];
        Digraph graph = new Digraph(synLines.length);

        String[] w;
        int ind;
        for (int i = 0; i < synLines.length; ++i) {
            w = synLines[i].split(",");
            ind = Integer.parseInt(w[0]);
            nouns[ind] = w[1];
        }
        for (String hypLine : hypLines) {
            w = hypLine.split(",");
            ind = Integer.parseInt(w[0]);
            for (int i = 1; i < w.length; i++) {
                graph.addEdge(ind, Integer.parseInt(w[i]));
            }
        }

        Topological tp = new Topological(graph);
        if (!tp.hasOrder()) {
            throw new IllegalArgumentException();
        }

        boolean oneRoot = false;
        for (int i = 0; i < graph.V(); ++i) {
            if (graph.outdegree(i) == 0) {
                if (oneRoot) {
                    throw new IllegalArgumentException();
                } else {
                    oneRoot = true;
                }
            }
        }
        if (!oneRoot) {
            throw new IllegalArgumentException();
        }
        sapFinder = new SAP(graph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        List<String> ret = new ArrayList();
        for (String simNouns : nouns) {
            ret.addAll(Arrays.asList(simNouns.split(" ")));
        }
        return ret;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if(word == null) throw new IllegalArgumentException();
        return nounToID.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException();
        }
        Integer nA = nounToID.get(nounA);
        Integer nB = nounToID.get(nounB);
        if(nA == null || nB == null){
            throw new IllegalArgumentException();
        }
        return sapFinder.length(nA, nB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException();
        }
        Integer nA = nounToID.get(nounA);
        Integer nB = nounToID.get(nounB);
        if(nA == null || nB == null){
            throw new IllegalArgumentException();
        }
        int ret = sapFinder.ancestor(nA, nB);
        return ret == -1 ? null : nouns[ret];
    }

    // do unit testing of this class
    public static void main(String[] args) {
        
    }
}
