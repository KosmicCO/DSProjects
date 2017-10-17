/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageist;

import edu.princeton.cs.algs4.StdDraw;
import utils.Vec;

/**
 *
 * @author cbarnum18
 */
public class AgedWorld {

    private Aged[] queue;
    private int maxAged;
    private int oldest = 0;
    
    public AgedWorld(int maxAged){
        this.maxAged = maxAged;
        queue = new Aged[maxAged];
    }
    
    private Aged accessOrder(int index){
        int nn = index - oldest;
        if(nn < 0){
            return queue[nn + maxAged];
        }
        return queue[nn];
    }
    
    private double getOlderNorm(int index){
        int nn = index - oldest;
        if(nn < 0){
            nn += maxAged;
        }
        Aged targ = queue[index];
        double maxNorm = -1;
        for (int i = nn - 1; i >= 0; --i) {
        }
    }
    
    public void update(){
        
    }
    
    private class Aged{
        
        public static final double CENTER_ACCEL = -1;
        public static final double RANDOM_ACCEL = 0.1;
        
        private Vec pos;
        private Vec vel;
        private int dim;
        
        public Aged(int dim){
            this.dim = dim;
            pos = Vec.rand(dim);
            vel = Vec.rand(dim);
        }
        
        public void update(){
            Vec nVel = vel.add(Vec.rand(dim).scale(RANDOM_ACCEL));
            vel = nVel.add(vel.scale(CENTER_ACCEL * nVel.norm()));
            pos = pos.add(vel.scale(Sim.increment));
        }
        
        public void draw(){
            StdDraw.filledCircle(pos.getCom(0), pos.getCom(1), 0.1);
        }
    }
}
