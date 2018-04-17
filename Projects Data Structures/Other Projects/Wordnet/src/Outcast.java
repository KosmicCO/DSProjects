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
        int maxD = 0;
        for (String n : nouns) {
            int d = 0;
            for (String m : nouns) {
                int nd = wn.distance(n, m);
                if (d < nd) {
                    d = nd;
                }
            }
            if (maxD < d) {
                oc = n;
                maxD = d;
            }
        }

        return oc;
    }
}
