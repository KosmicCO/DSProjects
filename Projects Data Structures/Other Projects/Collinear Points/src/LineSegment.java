/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class LineSegment {
    
    private Point p, q;
    
    public LineSegment(Point p, Point q){
        this.p = p;
        this.q = q;
    }
    
    public void draw(){
        p.drawTo(q);
    }
    
    @Override
    public String toString(){
        return p + "to" + q;
    }
}
