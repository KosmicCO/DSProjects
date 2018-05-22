
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class Addchar {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        do{
            System.out.println("-");
            input = s.next();
            System.out.println((char) (input.charAt(0) + '\001'));
        } while(true);
    }
}
