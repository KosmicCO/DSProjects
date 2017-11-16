
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
public class FastCollinearPoints implements CollinearPoints {

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
        int pushInd, state, count;
        Point max, min;
        for (int i = 0; i < points.length; i++) {

            a = points[i];
            cp = a.slopeOrder();
            Arrays.sort(pc, cp);

            pushInd = 1;
            count = 0;
            min = max = a;

            for (int j = 1; j < points.length; j++) {

                if (cp.compare(pc[pushInd], pc[j]) == 0) {
                    ++count;
                    if (min.compareTo(pc[j]) < 0) {
                        min = pc[j];
                    } else if (max.compareTo(pc[j]) > 0) {
                        max = pc[j];
                    }

                } else {
                    if (count >= 3) {
                        segs.add(new LineSegment(min, max));
                    }
                    min = max = a;
                    count = 0;
                    pushInd = j;
                    state = 1;
                }
            }
        }
        segments = segs.toArray(new LineSegment[segs.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}
