
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdRandom;
import java.math.BigInteger;

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
    public static Integer[] integers;
    public static int[] ints;
    public static int runs;
    
    //public static int count();
    
    public static void main(String[] args) {
        if(args.length == 3){
            filename = args[0];
            integers = new Integer[Integer.parseInt(args[1])];
            ints = new int[integers.length];
            runs = Integer.parseInt(args[2]);
        }else{
            filename = "1Kints.txt";
            integers = new Integer[1000];
            ints = new int[integers.length];
            runs = 10;
        }
    }
    
    public static void mainInt(String[] args) {
        if(args.length == 3){
            filename = args[0];
            integers = new Integer[Integer.parseInt(args[1])];
            ints = new int[integers.length];
            runs = Integer.parseInt(args[2]);
        }else{
            filename = "1Kints.txt";
            integers = new Integer[1000];
            ints = new int[integers.length];
            runs = 10;
        }
        
        In fn = new In(filename);
        
        for (int i = 0; i < integers.length; i++) {
            integers[i] = fn.readInt();
        }
        
        BigInteger sum = new BigInteger("0");
        Quick.sort(integers);
        for (int i = 0; i < integers.length; i++) {
            ints[i] = integers[i];
        }
        long tStart, tEnd;
        int randIndex;
        for (int i = 0; i < runs; i++) {
            randIndex = StdRandom.uniform(integers.length);
            tStart = System.nanoTime();
            BinarySearch.indexOf(ints, randIndex);
            tEnd = System.nanoTime();
            sum.add(new BigInteger("" + (tEnd - tStart)));
        }
        System.out.println(sum.divide(new BigInteger("" + runs)).doubleValue() / 1000000000);
    }
}
