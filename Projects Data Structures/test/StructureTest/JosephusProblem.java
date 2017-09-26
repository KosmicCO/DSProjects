/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StructureTest;

import structures.CircQueue;

/**
 *
 * @author cbarnum18
 */
public class JosephusProblem {
    public static void main(String[] arg) {
        String[] args = {"23843", "2"};
        int num = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        CircQueue<Integer> circle = new CircQueue();
        for (int i = 0; i < num; ++i) {
            circle.enqueue(i);
        }
        
        int count = 1;
        int nline = 1;
        while(circle.getSize() > 1){
            Integer i = circle.dequeue();
            if(!(count % n == 0)){
                circle.enqueue(i);
            }else{
                System.out.print(" " + i);
            }
            nline %= 25;
            if(nline == 0){
                System.out.println("");
            }
            nline++;
            count++;
        }
        
        System.out.println("-" + circle.dequeue());
    }
}
