
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class KdTree {

    private Node root;
    private int size;

    public KdTree() {                               // construct an empty set of points 
        root = null;
        size = 0;
    }

    public boolean isEmpty() {                      // is the set empty? 
        return root == null;
    }

    public int size() {                        // number of points in the set 
        return size;
    }

    public void insert(Point2D p) {           // add the point to the set (if it is not already in the set)
        if (root == null) {
            root = new Node(p);
        } else {
            root.insertRecur(p, false);
        }
    }

    public boolean contains(Point2D p) {           // does the set contain point p? 
        if(root == null){
            return false;
        }
        return root.containsRecur(p, false);
    }

    public void draw() {                     // draw all points to standard draw

    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle (or on the boundary) 
        
    }

    public Point2D nearest(Point2D point) {           // a nearest neighbor in the set to point p; null if the set is empty 

    }

    private class Node {

        private final Point2D point;
        private Node less, more;

        public Node(Point2D p) {
            point = p;
            less = null;
            more = null;
        }
        
        private Iterable<Point2D> range(RectHV rect, List<Point2D> ps, boolean vert){
            byte direct = 0x0;
            if(vert){
                if(rect.xmin() <= point.x()){
                    direct |= 0x2;
                }
                if(point.x() <= rect.xmax()){
                    direct |= 0x1;
                }
            }
        }

        private boolean containsRecur(Point2D p, boolean vert) {
            if (point.equals(p)) {
                return true;
            }
            double comp = vert ? p.y() - point.y() : p.x() - point.x();
            if (comp > 0) {
                if (more == null) {
                    return false;
                } else {
                    return more.containsRecur(p, !vert);
                }
            }
            if (less == null) {
                return false;
            }
            return less.containsRecur(p, !vert);
        }

        private void insertRecur(Point2D p, boolean vert) {
            double comp = vert ? p.y() - point.y() : p.x() - point.x();
            if (comp > 0) {
                if (more == null) {
                    more = new Node(p);
                } else {
                    more.insertRecur(p, !vert);
                }
            } else {
                if (less == null) {
                    less = new Node(p);
                } else {
                    less.insertRecur(p, !vert);
                }
            }
        }
    }
}
