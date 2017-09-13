/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbodies;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Vector;

/**
 *
 * @author cbarnum18
 */
public class Body {
    
    private static final double THREE_HALFS = 3 / 2;
    
    private Vector coords;
    private Vector velocity;
    private double mass;
    private String gifFile;
    
    private Vector nextVel;
    private Vector nextCoo;
    
    public Body(Vector c, Vector v, double ms, String gf){
        coords = c;
        velocity = v;
        mass = ms;
        gifFile = gf;
        nextVel = v;
        nextCoo = c;
    }
    
    public void update(){
        Vector netForce = new Vector(0, 0);
        for(Body b : Sim.bodies){
            if(b != this){
                double dx = b.getCoords().cartesian(0) - coords.cartesian(0);
                double dy = b.getCoords().cartesian(1) - coords.cartesian(1);
                double conF = (6.67e-11 * b.getMass() * Sim.increment) / Math.pow(dx * dx + dy * dy, THREE_HALFS);
                netForce = netForce.plus(new Vector(conF * dx, conF * dy));
            }
        }
        nextVel = velocity.plus(netForce);
        nextCoo = coords.plus(nextVel.scale(Sim.increment));
    }
    
    public void toNextVectors(){
        velocity = nextVel;
        coords = nextCoo;
    }
    
    public void draw(){
        StdDraw.picture((coords.cartesian(0) + Sim.uniRadius) / Sim.uniDiameter, (coords.cartesian(1) + Sim.uniRadius) / Sim.uniDiameter, gifFile);
    }

    public Vector getCoords() {
        return coords;
    }

    public void setCoords(Vector coords) {
        this.coords = coords;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
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
