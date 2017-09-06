/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbodies;

import edu.princeton.cs.algs4.Vector;

/**
 *
 * @author cbarnum18
 */
public class Body {
    
    private Vector coords;
    private Vector velocity;
    private double mass;
    private String gifFile;
    private Object gif; //This is broken 
    
    public Body(String raw){
        String[] split = raw.split(" ");
        double xc = Double.parseDouble(split[0].replaceAll("\\s", ""));
        double yc = Double.parseDouble(split[1].replaceAll("\\s", ""));
        double xv = Double.parseDouble(split[2].replaceAll("\\s", ""));
        double yv = Double.parseDouble(split[3].replaceAll("\\s", ""));
        double ms = Double.parseDouble(split[4].replaceAll("\\s", ""));
        String gf = split[5].replaceAll("\\s", "");
        coords = new Vector(xc, yc);
        velocity = new Vector(xv, yv);
        mass = ms;
        gifFile = gf;
    }
    
    private Body(Vector c, Vector v, double ms, String gf){
        coords = c;
        velocity = v;
        mass = ms;
        gifFile = gf;
    }
    
    public void update(){
        
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
