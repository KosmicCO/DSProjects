
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

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
        while(!text.isEmpty()){
            h = text.readString();
            if(words.contains(h)){
                words.put(h, words.get(h) + 1);
            }else{
                words.put(h, 1);
            }
        }
    }
}
