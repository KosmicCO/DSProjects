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
        String oc = null;
        int minD = Integer.MAX_VALUE;
        boolean skip = false;
        for (int i = 0; i < nouns.length; i++) {
            int nmd = Integer.MAX_VALUE;
            for (int j = i + 1; j < nouns.length; j++) {
                skip = false;
                int dist = wn.distance(nouns[i], nouns[j]);
                if (minD > dist) {
                    skip = true;
                    continue;
                }
                if (nmd > dist) {
                    nmd = dist;
                }
            }
            if (!skip) {
                if (minD > nmd) {
                    minD = nmd;
                    oc = nouns[i];
                }
            }
        }

        return oc;
    }
}
