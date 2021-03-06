
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

        String[] synLines = (new In(synsets)).readAllLines();
        String[] hypLines = (new In(hypernyms)).readAllLines();

        nouns = new String[synLines.length];
        Digraph graph = new Digraph(synLines.length);

        String[] w;
        int ind;
        for (int i = 0; i < synLines.length; ++i) {
            w = synLines[i].split(",");
            ind = Integer.parseInt(w[0]);
            nouns[ind] = w[1];
            String[] fromW = w[1].split(" ");
            for (String word : fromW) {
                nounToID.put(word, ind);
            }
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
        List<String> ret = new ArrayList<>();
        for (String simNouns : nouns) {
            ret.addAll(Arrays.asList(simNouns.split(" ")));
        }
        return ret;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return nounToID.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null || !(isNoun(nounA) && isNoun(nounB))) {
            throw new IllegalArgumentException();
        }
        return sapFinder.length(nounToID.get(nounA), nounToID.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null || !(isNoun(nounA) && isNoun(nounB))) {
            throw new IllegalArgumentException();
        }
        int ret = sapFinder.ancestor(nounToID.get(nounA), nounToID.get(nounB));
        return ret == -1 ? null : nouns[ret];
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wn = new WordNet("wordnet/synsets50000-subgraph.txt", "wordnet/hypernyms50000-subgraph.txt");
        System.out.println("--");
        System.out.println(wn.distance("Madrid", "license"));
    }
}
