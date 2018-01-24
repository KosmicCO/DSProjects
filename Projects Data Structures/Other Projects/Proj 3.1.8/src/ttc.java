
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class ttc {

    private static boolean printAll = true;
    private static boolean printTop = true;
    private static int minLen = 10;

    public static void put(String word, ST<String, Integer> words) {
        if (words.contains(word)) {
            words.put(word, words.get(word) + 1);
        } else {
            words.put(word, 1);
        }
    }

    public static void main(String[] args) {

        In text = new In("text.txt");
        ST<String, Integer> words = new ST<String, Integer>();
        List<String> h = new ArrayList<String>();
        String n;
        int dashes;
        while (!text.isEmpty()) {
            h.add(text.readString());

            while (!h.isEmpty()) {
                n = h.get(0);
                dashes = n.indexOf("--");
                if (dashes != -1) {
                    h.remove(0);
                    h.add(0, n.substring(dashes + 2));
                    h.add(0, n.substring(0, dashes));
                }
                put(h.remove(0).replaceAll("[^a-zA-z\\-]", "").toLowerCase(), words);
            }
        }

        Word[] wordList = new Word[words.size()];
        Iterator<String> keys = words.keys().iterator();
        String next;
        for (int i = 0; i < words.size(); i++) {
            next = keys.next();
            wordList[i] = new Word(next, words.get(next));
        }

        Arrays.sort(wordList);

        if (printAll) {

            StdOut.println("\nFull Word List (Minimum Length [" + minLen + "], then Occurence, then Alphabetical)\n");

            for (Word word : wordList) {
                if (word.getWord().length() >= 10) {
                    StdOut.println(word);
                }
            }
        }

        if (printTop) {

            StdOut.println("\nWord List (First Occurence of Increasing length)\n");

            Arrays.sort(wordList, (a, b) -> a.getWord().length() - b.getWord().length());
            int len = 0;
            int prev = 0;
            String extra = "";
            for (Word word : wordList) {
                if (word.getWord().length() > len) {
                    len = word.getWord().length();
                    if (word.getOccur() > prev) {
                        extra = " --";
                    } else {
                        extra = "";
                    }
                    prev = word.getOccur();
                    StdOut.println(word + extra);
                }
            }
        }
    }

    private static class Word implements Comparable<Word> {

        private final String word;
        private final int occur;

        public Word(String word, int occur) {
            this.word = word;
            this.occur = occur;
        }

        @Override
        public int compareTo(Word t) {
            int diff = t.occur - occur;
            if (diff == 0) {
                return word.compareTo(t.word);
            }
            return diff;
        }

        public String getWord() {
            return word;
        }

        public int getOccur() {
            return occur;
        }

        @Override
        public String toString() {
            return word + " : " + occur;
        }
    }
}

/*

Full Word List (Minimum Length [10], then Occurence, then Alphabetical)

monseigneur : 104
confidence : 33
expression : 33
appearance : 29
everything : 27

    ...

worthlessness : 1
wretchedest : 1
wretchedly : 1
wrongfully : 1
youthfulness : 1

Word List (First Occurence of Increasing length)

a : 2946 --
of : 4013 --
the : 8052 --
that : 1905
there : 577
little : 267
defarge : 282 --
business : 128
gentleman : 70
confidence : 33
monseigneur : 104 --
conversation : 20
circumstances : 22 --
farmer-general : 7
accomplishments : 2
attorney-general : 12 --
attorney-generals : 2
five-and-twentieth : 1
business-absorption : 1
garrison-and-dockyard : 1

 */
