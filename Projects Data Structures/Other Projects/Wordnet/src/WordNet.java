
import edu.princeton.cs.algs4.In;

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

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        
        String[][] synForm;
        String[][] hypForm;
        {
        In synIn = new In(synsets);
        In hypIn = new In(hypernyms);
        
        String synAll = synIn.readAll();
        String hypAll = hypIn.readAll();
        
        String[] synLines = synAll.split(",");
        String[] hypLines = hypAll.split(",");
        
        synForm = new String[synLines.length][];
        hypForm = new String[hypLines.length][];
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
