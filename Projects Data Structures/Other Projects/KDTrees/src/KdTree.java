
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.util.ArrayList;
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
    
    public static final Color REED_RED = new Color(167, 14, 22);
    private static final RectHV CANVAS = new RectHV(0, 0, 1, 1);
    private static final double POINT_SIZE = 0.005;
    private static final boolean START_ITOR = false;

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
            root.insertRecur(p, START_ITOR);
        }
    }

    public boolean contains(Point2D p) {           // does the set contain point p? 
        if(root == null){
            return false;
        }
        return root.containsRecur(p, START_ITOR);
    }

    public void draw() {                     // draw all points to standard draw
        if(root != null){
            root.draw(CANVAS, START_ITOR);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle (or on the boundary) 
        List<Point2D> ret = new ArrayList<Point2D>();
        if(root == null){
            return ret;
        }
        root.range(rect, ret, START_ITOR);
        return ret;
    }

    public Point2D nearest(Point2D point) {           // a nearest neighbor in the set to point p; null if the set is empty 
        return null;
    }

    private class Node {

        private final Point2D point;
        private Node less, more;

        public Node(Point2D p) {
            point = p;
            less = null;
            more = null;
        }
        
        private void draw(RectHV bounding, boolean vert){
            if(vert){
                StdDraw.setPenColor(REED_RED);
                StdDraw.line(point.x(), bounding.ymin(), point.x(), bounding.ymax());
                if(less != null){
                    less.draw(new RectHV(bounding.xmin(), bounding.ymin(), point.x(), bounding.ymax()), !vert);
                }
                if(more != null){
                    more.draw(new RectHV(point.x(), bounding.ymin(), bounding.xmax(), bounding.ymax()), !vert);
                }
            }else{
                StdDraw.line(bounding.xmin(), point.y(), bounding.xmax(), point.y());
                if(less != null){
                    less.draw(new RectHV(bounding.xmin(), bounding.ymin(), bounding.xmax(), point.y()), !vert);
                }
                if(more != null){
                    more.draw(new RectHV(bounding.xmin(), point.y(), bounding.xmax(), bounding.ymax()), !vert);
                }
            }
            StdDraw.filledCircle(point.x(), point.y(), POINT_SIZE);
        }
        
        private void range(RectHV rect, List<Point2D> ps, boolean vert){
            byte direct = 0x0;
            if(vert){
                if(rect.xmin() <= point.x()){
                    direct |= 0x2;
                }
                if(point.x() <= rect.xmax()){
                    direct |= 0x1;
                }
            }else{
                if(rect.ymin() <= point.x()){
                    direct |= 0x2;
                }
                if(point.x() <= rect.ymax()){
                    direct |= 0x1;
                }
            }
            if(rect.contains(point)){
                ps.add(point);
            }
            if((direct & 0x1) == 0x1 && less != null){
                less.range(rect, ps, !vert);
            }
            if((direct & 0x2) == 0x2 && more != null){
                more.range(rect, ps, !vert);
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

        private boolean insertRecur(Point2D p, boolean vert) {
            if(point.equals(p)){
                return false;
            }
            double comp = vert ? p.y() - point.y() : p.x() - point.x();
            if (comp > 0) {
                if (more == null) {
                    more = new Node(p);
                    return true;
                } else {
                    return more.insertRecur(p, !vert);
                }
            } else {
                if (less == null) {
                    less = new Node(p);
                    return true;
                } else {
                    return less.insertRecur(p, !vert);
                }
            }
        }
    }
}
