/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbodies;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Vector;
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
    public static double uniDiameter;
    public static ArrayList<Body> bodies;
    
    public static void main(String[] args) {
        String[] gggggg = { "1000.0", "1.0", "planets-zero-gravity.txt"};
        initFromFile(gggggg);
        draw();
        double curtime = 0.0;
        double waittime = 100;
        double milli = System.currentTimeMillis();
        while(curtime < duration){
            update();
            draw();
            curtime += Sim.increment;
            while(System.currentTimeMillis() - milli < waittime){}
            milli = System.currentTimeMillis();
        }
        System.out.println("ryui");
    }
    
    public static void update(){
        bodies.forEach(b -> b.update());
        bodies.forEach(b -> b.toNextVectors());
    }
    
    public static void draw(){
        StdDraw.picture(0, 0, BACK, 2, 2);
        bodies.forEach(b -> b.draw());
    }
    
    public static void initFromFile(String[] args){
        //try{
            duration = Double.parseDouble(args[0]);
            increment = Double.parseDouble(args[1]);
            In fileInput = new In("nbody/nbody/" + args[2]);
            int planNum = fileInput.readInt();
            uniRadius = fileInput.readDouble();
            uniDiameter = uniRadius * 2;
            bodies = new ArrayList();
            
            for (int i = 0; i < planNum; i++) {
                Vector pos = new Vector(fileInput.readDouble(), fileInput.readDouble());
                Vector vel = new Vector(fileInput.readDouble(), fileInput.readDouble());
                bodies.add(new Body(pos, vel, fileInput.readDouble(), "nbody/nbody/" + fileInput.readString()));
            }
//        }catch(NumberFormatException nfe){
//            System.out.println("Needs double input");
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
}
