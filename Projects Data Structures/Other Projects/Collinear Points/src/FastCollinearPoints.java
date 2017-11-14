
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class FastCollinearPoints {
    
    private Point[] points;
    private LineSegment[] segments;
    
    public FastCollinearPoints(Point[] points){
        if(points == null){
            throw new IllegalArgumentException();
        }
        this.points = points;
        
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        
        Arrays.sort(points);
        
        for (int i = 0; i < points.length - 1; i++) {
            if(points[i].compareTo(points[i + 1]) == 0){
                throw new IllegalArgumentException();
            }
        }
        
        Point[] pc = Arrays.copyOf(points, points.length);
        
        Point a;
        for (int i = 0; i < points.length - 3; i++) {
            
            a = points[i];
            Arrays.sort(pc, a.slopeOrder());
            
            for (int j = i + 1; j < points.length; j) {
                
            }
        }
    }
    
    public int numberOfSegments(){
        
    }
    
    public LineSegment[] segments(){
        
    }
}
