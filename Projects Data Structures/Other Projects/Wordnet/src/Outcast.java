/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class Outcast {

    private final WordNet wn;

    public Outcast(WordNet wn) {
        this.wn = wn;
    }

    public String outcast(String[] nouns) {
        if (nouns == null) {
            throw new IllegalArgumentException();
        }
        for (String n : nouns) {
            if (!wn.isNoun(n)) {
                throw new IllegalArgumentException();
            }
        }
        int[] dists = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            for (int j = i + 1; j < nouns.length; j++) {
                dists[i] += wn.distance(nouns[i], nouns[j]);
                dists[j] += wn.distance(nouns[i], nouns[j]);
            }
        }
        String out = null;
        int greatest = 0;
        for (int i = 0; i < nouns.length; i++) {
            if (dists[i] > greatest) {
                greatest = dists[i];
                out = nouns[i];
            }
        }
        return out;
    }

    public static void main(String[] args) {
        WordNet wn = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
        Outcast out = new Outcast(wn);
        System.out.println(out.outcast("earth fire water heart".split(" ")));
    }
}
