/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class Fermat {
    
    public static void main(String[] args) {
        long c = 5496093;
        System.out.println(c(c));
        long a = 17221;
        long b = c(c) - c(a);
        double root = Math.pow(b, 0.33333333333333333333333333333333333333);
        while(b <= 0 || Math.abs(root - ((int) root)) > 1e-10){
            if(b < 0){
                c++;
                a = 1;
            }else{
                a++;
            }
            b = c(c) - c(a);
            root = Math.pow(b, 0.33333333333333333333333333333333333333);
        }
        System.out.println(a + ", " + b + ", " + c);
    }
    
    public static long c(long i){
        return i * i * i;
    }
}
