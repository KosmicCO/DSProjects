
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
public class FastCollinearPoints { //implements CollinearPoints {

    private final Point[] points;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] poi) {
        if (poi == null) {
            throw new IllegalArgumentException();
        }
        this.points = poi;

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

        ArrayList<Point> mins = new ArrayList<Point>();
        ArrayList<Point> maxs = new ArrayList<Point>();

        Point a;
        Comparator<Point> cp;
        int pushInd, count;
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

                    if (count >= 2) {
                        boolean newS = true;
                        for (int k = 0; k < mins.size(); k++) {
                            if (mins.get(k).compareTo(min) == 0) {
                                if (maxs.get(k).compareTo(max) == 0) {
                                    break;
                                }
                            }
                        }
                        if (newS) {
                            mins.add(min);
                            maxs.add(max);
                        }
                    }
                    min = max = a;
                    count = 0;
                    pushInd = j;
                }
            }
        }

        segments = new LineSegment[mins.size()];
        for (int i = 0; i < mins.size(); i++) {
            segments[i] = new LineSegment(mins.get(i), maxs.get(i));
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }
}
