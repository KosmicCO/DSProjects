/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movementsim;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import utils.Vec;

/**
 *
 * @author cbarnum18
 */
public class World {

    public final static double increment = 0.01;
    public final static double duration = 1000;
    
    private Mov[] movList;
    private int numMovs;
    
    public World(int numMovs){
        this.numMovs = numMovs;
        movList = new Mov[numMovs];
        for (int i = 0; i < numMovs; i++) {
            movList[i] = new Mov(2);
        }
        
    }
    
    public void updateDraw(){
        StdDraw.clear();
        StdDraw.setPenColor(127, 127, 0);
        StdDraw.circle(0.0, 0.0, 1.0);
        StdDraw.setPenColor();
        for(Mov m : movList){
            m.update();
            m.draw();
        }
        StdDraw.show();
    }
    
    private class Mov {
        
        public double CENTER_ACCEL = -1 * increment;
        public double MULTI = 0.1;
        public double RANDOM_ACCEL = 4 * increment;
        
        private Vec pos;
        private Vec vel;
        private int dim;
        
        public Mov(int dim){
            this.dim = dim;
            pos = Vec.rand(dim);
            vel = Vec.rand(dim);
        }
        
        public void update(){
            Vec nVel = vel.add(Vec.rand(dim).scale(RANDOM_ACCEL));
            double psn = pos.scale(MULTI).norm();
            vel = nVel.add(pos.scale(CENTER_ACCEL * psn));
            pos = pos.add(vel.scale(increment));
        }
        
        public void draw(){
            StdDraw.filledSquare(pos.getCom(0), pos.getCom(1), 0.02);
        }
    }
    
    public static void main(String[] args) {
        World w = new World(100);
        double x, y;
        x = y = 4;
        StdDraw.setXscale(-x, x);
        StdDraw.setYscale(-y, y);
        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        while(time < duration){
            w.updateDraw();
            time += increment;
        }
    }
}
