
import edu.princeton.cs.algs4.Point2D;

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
        if(root == null){
            root = new Node(p);
        }
    }

    public boolean contains(Point2D p) {           // does the set contain point p? 

    }

    public void draw() {                     // draw all points to standard draw

    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle (or on the boundary) 

    }

    public Point2D nearest(Point2D point) {           // a nearest neighbor in the set to point p; null if the set is empty 

    }
    
    private class Node {
        
        private Point2D point;
        private Node less, more;
        
        public Node(Point2D p){
            point = p;
            less = null;
            more = null;
        }
        
        private void insertRecur(Point2D p, boolean vert){
            
        }
    }
}
