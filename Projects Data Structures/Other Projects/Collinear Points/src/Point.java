
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

/*
 * http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class Point implements Comparable<Point>{

    private int x, y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Point t) {
        return (t.y == y) ? (t.x - x) : (t.y - y);
    }
    
    public double slopeTo(Point t){
        return ((double) (t.y - y)) / (t.x - x);
    }
    
    public Comparator<Point> slopeOrder(){
        return (t, s) -> Double.compare(slopeTo(t), slopeTo(s));
    }
    
    public void draw(){
        StdDraw.square(x, y, 0.05);
    }
    
    public void drawTo(Point t){
        StdDraw.line(x, y, t.x, t.y);
    }
    
    @Override
    public String toString(){
        return "<" + x + ", " + y + ">";
    }
}
