/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbodies;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

/**
 *
 * @author cbarnum18
 */
public class Sim {
    
    public static final String BACK = "nbody/nbody/starfield.jpg";
    
    public static double duration;
    public static double increment;
    public static double uniRadius;
    public static ArrayList<Body> bodies;
    
    public static void main(String[] args) {
        String[] gggggg = {"1.0", "1.0", "planets.txt"};
        initFromFile(gggggg);
        draw();
        
    }
    
    public static void draw(){
        StdDraw.picture(0, 0, BACK, 2, 2);
        bodies.forEach(b -> draw());
    }
    
    public static void initFromFile(String[] args){
        //try{
            duration = Double.parseDouble(args[0]);
            increment = Double.parseDouble(args[1]);
            In fileInput = new In("nbody/nbody/" + args[2]);
            String[] lines = fileInput.readAllLines();
            int planNum = Integer.parseInt(lines[0].replaceAll("\\s", ""));
            uniRadius = Double.parseDouble(lines[1].replaceAll("\\s", ""));
            bodies = new ArrayList();
            for (int i = 0; i < planNum; i++) {
                double[] info = new double[5];
                for (int j = 0; j < 5; j++) {
                    info[j] = fileInput.readDouble(); //thats now how it works
                }
                bodies.add(new Body(info, fileInput.readString()));
            }
//        }catch(NumberFormatException nfe){
//            System.out.println("Needs double input");
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
}
