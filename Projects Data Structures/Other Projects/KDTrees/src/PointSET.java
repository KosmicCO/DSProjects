
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class PointSET {

    private TreeSet<Point2D> points;

    public PointSET() {                               // construct an empty set of points 
        points = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {                      // is the set empty? 
        return points.isEmpty();
    }

    public int size() {                        // number of points in the set 
        return points.size();
    }

    public void insert(Point2D p) {           // add the point to the set (if it is not already in the set)
        points.add(p);
    }

    public boolean contains(Point2D p) {           // does the set contain point p? 
        return points.contains(p);
    }

    public void draw() {                     // draw all points to standard draw
        points.forEach(p -> draw());
    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle (or on the boundary) 
        List<Point2D> retPoints = new ArrayList<Point2D>();
        points.forEach(p -> {
            if (rect.xmin() <= p.x() && p.x() <= rect.xmax()) {
                if (rect.ymin() <= p.y() && p.y() <= rect.ymax()) {
                    retPoints.add(p);
                }
            }
        });
        return retPoints;
    }

    public Point2D nearest(Point2D point) {           // a nearest neighbor in the set to point p; null if the set is empty 
        if (points.isEmpty()) {
            return null;
        }
        Point2D near = points.first();
        double dist = Double.POSITIVE_INFINITY;
        for (Point2D p : points) {
            double candNear = p.distanceSquaredTo(point);
            if (candNear < dist) {
                near = p;
                dist = candNear;
            }
        }
        return near;
    }

    public static void main(String[] args) {                  // unit testing of the methods (optional) 
    }

}
