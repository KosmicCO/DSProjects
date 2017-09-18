/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbodies;

import edu.princeton.cs.algs4.StdDraw;
import utils.Vec;

/**
 *
 * @author cbarnum18
 */
public class Body {
    
    private static final double THREE_HALFS = 3.0 / 2.0;
    private static final double G = 6.67e-11;
    
    private Vec coords;
    private Vec velocity;
    private double mass;
    private String gifFile;
    
    private Vec nextVel;
    private Vec nextCoo;
    
    public Body(Vec c, Vec v, double ms, String gf){
        coords = c;
        velocity = v;
        mass = ms;
        gifFile = gf;
        nextVel = v;
        nextCoo = c;
    }
    
    public void update(){
//        Vec netForce = new Vec(0, 0);
//        for(Body b : Sim.bodies){
//            if(b != this){
//                double dx = b.getCoords().cartesian(0) - coords.cartesian(0);
//                double dy = b.getCoords().cartesian(1) - coords.cartesian(1);
//                double conF = (6.67e-11 * b.getMass() * Sim.increment) / Math.pow(dx * dx + dy * dy, THREE_HALFS);
//                netForce = netForce.plus(new Vec(conF * dx, conF * dy));
//            }
//        }
//        nextVel = velocity.plus(netForce);
//        nextCoo = coords.plus(nextVel.scale(Sim.increment));

//        Vec netForce = new Vec(0.0, 0.0);
//        System.out.println(Sim.bodies);
//        for(Body b : Sim.bodies){
//            if(b != this){
//                Vec dist = b.coords.subtract(coords);
//                double dx = dist.getCom(0);
//                double dy = dist.getCom(1);
//                double r1 = Math.pow(dx * dx + dy * dy, 0.5);
//                double force = (G * b.mass) / (r1 * r1);
//                netForce = new Vec((force * dx) / r1 + netForce.getCom(0), (force * dy)/ r1 + netForce.getCom(1));
//            }
//        }
//        nextVel = velocity.add(netForce.scale(Sim.increment));
//        nextCoo = coords.add(nextVel.scale(Sim.increment));

        Vec accel = new Vec(0.0, 0.0);
        for(Body b : Sim.bodies){
            if(b == this){
                continue;
            }
            Vec dist = b.coords.subtract(coords);
            double force = G * b.mass / Math.pow(dist.norm(), THREE_HALFS);
            accel = accel.add(dist.scale(force));
        }
        
        nextVel = velocity.add(accel.scale(Sim.increment));
        nextCoo = coords.add(nextVel.scale(Sim.increment));
    }
    
    public void toNextVectors(){
        velocity = nextVel;
        coords = nextCoo;
    }
    
    public void draw(){
        StdDraw.picture((coords.getCom(0) + Sim.uniRadius) / Sim.uniDiameter, (coords.getCom(1) + Sim.uniRadius) / Sim.uniDiameter, gifFile);
    }

    public Vec getCoords() {
        return coords;
    }

    public void setCoords(Vec coords) {
        this.coords = coords;
    }

    public Vec getVelocity() {
        return velocity;
    }

    public void setVelocity(Vec velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getGifFile() {
        return gifFile;
    }

    public void setGifFile(String gifFile) {
        this.gifFile = gifFile;
    }
    
    
}
