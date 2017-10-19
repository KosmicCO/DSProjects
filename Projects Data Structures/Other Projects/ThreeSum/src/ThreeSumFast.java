
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
public class ThreeSumFast {
    
    public static String filename;
    public static int[] ints;
    
    //public static int count();
    
    public static void main(String[] args) {
        if(args.length == 2){
            filename = args[0];
            ints = new int[Integer.parseInt(args[1])];
        }else{
            filename = "1Kints.txt";
            ints = new int[1000];
        }
        
        In fn = new In(filename);
        
        for (int i = 0; i < ints.length; i++) {
            ints[i] = fn.readInt();
        }
    }
}
