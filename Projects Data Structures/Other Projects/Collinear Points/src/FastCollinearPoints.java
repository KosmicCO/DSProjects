
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
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
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        Point[] pc = Arrays.copyOf(points, points.length);

        ArrayList<LineSegment> segs = new ArrayList<LineSegment>();
        
        Point a;
        Comparator<Point> cp;
        Point pushed = null;
        int ind, mode;
        boolean running;
        for (int i = 0; i < points.length - 3; i++) {

            a = points[i];

            cp = a.slopeOrder();
            Arrays.sort(pc, a.slopeOrder());
         
            ind = 0;
            mode = 0;
            running = true;
            while (running) {
                switch (mode) {
                    case 0:
                        if (ind > 0 && cp.compare(pc[ind], pc[ind - 1]) == 0) {
                            --ind;
                        }
                        mode = 1;
                        break;
                    case 1:
                        if (ind >= points.length - 2) {
                            mode = 3;
                            break;
                        }

                        ind += 2;

                        if (cp.compare(pc[ind - 2], pc[ind]) == 0) {
                            pushed = pc[ind];
                            mode = 2;
                        } else {
                            mode = 0;
                        }
                        break;
                    case 2:
                        while (true) {
                            if(ind >= points.length - 2){
                                mode = 3;
                                break;
                            }
                            ind += 2;
                            if (cp.compare(pc[ind - 2], pc[ind]) != 0) {
                                if(cp.compare(pc[ind - 2], pc[ind - 1]) == 0){
                                    segs.add(new LineSegment(pushed, pc[ind - 1]));
                                    mode = 1;
                                }else{
                                    segs.add(new LineSegment(pushed, pc[ind - 2]));
                                    mode = 0;
                                }
                                break;
                            }
                        }
                        break;
                    case 3:
                        running = false;
                }
            }
        }
    }

    public int numberOfSegments() {

    }

    public LineSegment[] segments() {

    }
}
