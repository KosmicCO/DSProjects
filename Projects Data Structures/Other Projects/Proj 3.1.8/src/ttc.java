
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
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
public class ttc {

    public static void main(String[] args) {

        In text = new In("text.txt");
        ST<String, Integer> words = new ST<String, Integer>();
        String h;
        while (!text.isEmpty()) {
            h = text.readString().replaceAll("[^a-zA-z]", "").toLowerCase();
            if (h.length() < 10) {
                continue;
            }
            if (words.contains(h)) {
                words.put(h, words.get(h) + 1);
            } else {
                words.put(h, 1);
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
        for (int i = wordList.length - 1; i >= 0; i--) {
            StdOut.println(wordList[i]);
        }
    }
    
    private static class Word implements Comparable<Word>{
        
        private final String word;
        private final int occur;
        
        public Word(String word, int occur){
            this.word = word;
            this.occur = occur;
        }

        @Override
        public int compareTo(Word t) {
            return occur - t.occur;
        }

        public String getWord() {
            return word;
        }

        public int getOccur() {
            return occur;
        }
        
        @Override
        public String toString(){
            return word + " : " + occur;
        }
    }
}
