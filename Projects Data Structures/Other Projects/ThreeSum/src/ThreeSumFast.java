
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Arrays;

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

    public static void insertion(int[] a){
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]){
                    int k = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = k;
                }
            }
        }
    }
    
    public static int count(int[] a) {
        //Arrays.sort(a);
        insertion(a);
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (BinarySearch.indexOf(a, -a[i] - a[j]) > j) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] list;
        double[] timeList = new double[6];
        double[] diffList = new double[5];
        double[] ddifList = new double[4];
        int cnt = 0;
        for (int i = 1; i <= 32; i = i << 1) {
            list = (new In("intFiles/" + i + "Kints.txt")).readAllInts();
            Stopwatch sw = new Stopwatch();
            count(list);
            timeList[cnt] = sw.elapsedTime();
            StdOut.printf("%2dKints : %6.4f sec%n", i, timeList[cnt]);
            if (i > 1) {
                diffList[cnt - 1] = timeList[cnt] - timeList[cnt - 1];
                StdOut.printf("%8s: %6.4f sec%n", "", diffList[cnt - 1]);
                StdOut.printf("%8s: %6.4f sec%n", "", timeList[cnt] / diffList[cnt - 1]);
            }
            if (i > 2) {
                
            }
            cnt++;
        }
        
        //predict 65 seconds
//        list = (new In("intFiles/1Mints.txt")).readAllInts();
//        Stopwatch sw = new Stopwatch();
//        count(list);
//        double t = sw.elapsedTime();
//        StdOut.printf(" 1Mints : %-6.4f sec%n", t);
    }
}
