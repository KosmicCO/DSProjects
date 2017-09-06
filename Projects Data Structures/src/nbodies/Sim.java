/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbodies;

import edu.princeton.cs.algs4.In;
import java.util.ArrayList;

/**
 *
 * @author cbarnum18
 */
public class Sim {
    
    public static double duration;
    public static double increment;
    public static double uniRadius;
    public static ArrayList<Body> bodies;
    
    public static void main(String[] args) {
        bodies = initFromFile(args);
    }
    
    public static ArrayList<Body> initFromFile(String[] args){
        try{
            duration = Double.parseDouble(args[0]);
            increment = Double.parseDouble(args[1]);
            In fileInput = new In("nbody/nbody/" + args[2]);
            String[] lines = fileInput.readAllLines();
            int planNum = Integer.parseInt(lines[0].replaceAll("\\s", ""));
            uniRadius = Integer.parseInt(lines[1].replaceAll("\\s", ""));
            ArrayList<Body> bodies = new ArrayList();
            for (int i = 0; i < planNum; i++) {
                bodies.add(new Body(lines[i + 2]));
            }
            return bodies;
        }catch(NumberFormatException nfe){
            System.out.println("Needs double input");
        }
        return null;
    }
}
