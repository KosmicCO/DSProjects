
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;
import java.util.HashMap;
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

    private String[][] nouns;
    private Map<String, Integer> nounToID;
    private Digraph graph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

        String[][] hypForm;
        nounToID = new HashMap();
        
        In synIn = new In(synsets);
        In hypIn = new In(hypernyms);

        String[] synLines = synIn.readAllLines();
        String[] hypLines = hypIn.readAllLines();

        nouns = new String[synLines.length][];
        graph = new Digraph(synLines.length);

        String[] w;
        int ind;
        for (int i = 0; i < synLines.length; ++i) {
            w = synLines[i].split(",");
            ind = Integer.parseInt(w[0]);
            nouns[ind] = w[1].split(" ");
            for (int j = 0; j < nouns[ind].length; ++j) {
                nounToID.put(nouns[ind][j], ind);
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
        if(!tp.hasOrder()){
            throw new IllegalArgumentException();
        }
        
        boolean oneRoot = false;
        for (int i = 0; i < graph.V(); ++i) {
            if(graph.outdegree(i) == 0){
                if(oneRoot){
                    throw new IllegalArgumentException();
                }else{
                    oneRoot = true;
                }
            }
        }
        if(!oneRoot){
            throw new IllegalArgumentException();
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {

    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {

    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {

    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {

    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
