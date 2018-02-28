
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

    private static final Color REED_RED = new Color(167, 14, 22);
    private static final RectHV CANVAS = new RectHV(0, 0, 1, 1);
    private static final double POINT_SIZE = 0.005;
//    private static final double PEN_RAD = 0.002;
    private static final boolean START_ITOR = true;

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
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = new Node(p);
        } else {
            if (!root.insertRecur(p, START_ITOR)) {
                return;
            }
        }
        size++;
    }

    public boolean contains(Point2D p) {           // does the set contain point p?
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return root == null ? false : root.containsRecur(p, START_ITOR);
    }

    public void draw() {
//        StdDraw.setPenRadius(PEN_RAD); // draw all points to standard draw
        if (root != null) {
            root.draw(CANVAS, START_ITOR);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle (or on the boundary) 
        if(rect == null){
            throw new IllegalArgumentException();
        }
        List<Point2D> ret = new ArrayList<Point2D>();
        if (root == null) {
            return ret;
        }
        root.rangeRecur(rect, ret, START_ITOR);
        return ret;
    }

    public Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty 
        if(p == null){
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return null;
        }
        return root.nearestRecur(p, START_ITOR).point;
    }

    public static void main(String[] args) {
        KdTree kt = new KdTree();
        kt.insert(new Point2D(0.5, 0.5));
        kt.insert(new Point2D(0.25, 0.75));
        System.out.println(kt.range(new RectHV(0.0, 0.0, 0.3, 1.0)));
    }

    private class Node {

        private final Point2D point;
        private Node less, more;

        public Node(Point2D p) {
            point = p;
            less = null;
            more = null;
        }

        private void draw(RectHV bounding, boolean vert) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(point.x(), point.y(), POINT_SIZE);
            if (vert) {
                if (less != null) {
                    less.draw(new RectHV(bounding.xmin(), bounding.ymin(), point.x(), bounding.ymax()), !vert);
//                    StdDraw.line(point.x(), point.y(), less.point.x(), less.point.y());
                }
                if (more != null) {
                    more.draw(new RectHV(point.x(), bounding.ymin(), bounding.xmax(), bounding.ymax()), !vert);
//                    StdDraw.line(point.x(), point.y(), more.point.x(), more.point.y());
                }
                StdDraw.setPenColor(REED_RED);
                StdDraw.line(point.x(), bounding.ymin(), point.x(), bounding.ymax());
            } else {
                if (less != null) {
                    less.draw(new RectHV(bounding.xmin(), bounding.ymin(), bounding.xmax(), point.y()), !vert);
//                    StdDraw.line(point.x(), point.y(), less.point.x(), less.point.y());
                }
                if (more != null) {
                    more.draw(new RectHV(bounding.xmin(), point.y(), bounding.xmax(), bounding.ymax()), !vert);
//                    StdDraw.line(point.x(), point.y(), more.point.x(), more.point.y());
                }
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(bounding.xmin(), point.y(), bounding.xmax(), point.y());
            }
        }

        private PointDist nearestRecur(Point2D p, boolean vert) {
            double comp = vert ? p.x() - point.x() : p.y() - point.y();
            PointDist pd = new PointDist(point, p.distanceSquaredTo(point));
            PointDist cand;
            if (comp >= 0) {
                if (more != null) {
                    cand = more.nearestRecur(p, !vert);
                    if (cand.distSqr < pd.distSqr) {
                        pd = cand;
                    }
                }

                if (comp * comp < pd.distSqr) {
                    if (less != null) {
                        cand = less.nearestRecur(p, !vert);
                        if (cand.distSqr < pd.distSqr) {
                            pd = cand;
                        }
                    }
                }
            } else {
                if (less != null) {
                    cand = less.nearestRecur(p, !vert);
                    if (cand.distSqr < pd.distSqr) {
                        pd = cand;
                    }
                }

                if (comp * comp < pd.distSqr) {
                    if (more != null) {
                        cand = more.nearestRecur(p, !vert);
                        if (cand.distSqr < pd.distSqr) {
                            pd = cand;
                        }
                    }
                }
            }

            return pd;
        }

        private void rangeRecur(RectHV rect, List<Point2D> ps, boolean vert) {
            byte direct = 0x0;
            if (rect.contains(point)) {
                ps.add(point);
            }
            if (vert) {
                if (rect.xmin() <= point.x()) {
                    direct |= 0x2;
                }
                if (point.x() <= rect.xmax()) {
                    direct |= 0x1;
                }
            } else {
                if (rect.ymin() <= point.y()) {
                    direct |= 0x2;
                }
                if (point.y() <= rect.ymax()) {
                    direct |= 0x1;
                }
            }
            if ((direct & 0x2) == 0x2 && less != null) {
                less.rangeRecur(rect, ps, !vert);
            }
            if ((direct & 0x1) == 0x1 && more != null) {
                more.rangeRecur(rect, ps, !vert);
            }
        }

        private boolean containsRecur(Point2D p, boolean vert) {
            if (point.equals(p)) {
                return true;
            }
            double comp = vert ? p.x() - point.x() : p.y() - point.y();
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
            if (point.equals(p)) {
                return false;
            }
            double comp = vert ? p.x() - point.x() : p.y() - point.y();
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

    private class PointDist {

        private final Point2D point;
        private final double distSqr;

        public PointDist(Point2D p, double d) {
            point = p;
            distSqr = d;
        }
    }
}
