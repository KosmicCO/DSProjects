
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
public class FastCollinearPoints { // implements CollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] poi) {
        if (poi == null) {
            throw new IllegalArgumentException();
        }

        for (Point poi1 : poi) {
            if (poi1 == null) {
                throw new IllegalArgumentException();
            }
        }

        Point[] points = Arrays.copyOf(poi, poi.length);

        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        Point[] pc = Arrays.copyOf(points, points.length);

        ArrayList<Point> mins = new ArrayList<Point>();
        ArrayList<Point> maxs = new ArrayList<Point>();

        Comparator<Point> cp;
        int pushInd, count;
        Point max, min;
        for (Point point : points) {
            cp = point.slopeOrder();
            Arrays.sort(pc, cp);
            pushInd = 1;
            count = 0;
            min = point;
            max = point;
            int j = 1;
            while (j <= points.length) {

                if (j < points.length && cp.compare(pc[pushInd], pc[j]) == 0) {
                    ++count;
                    if (min.compareTo(pc[j]) < 0) {
                        min = pc[j];
                    }
                    if (max.compareTo(pc[j]) > 0) {
                        max = pc[j];
                    }

                } else {

                    if (count >= 3) {
                        boolean newS = true;
                        for (int k = 0; k < mins.size(); k++) {
                            if (mins.get(k).compareTo(min) == 0) {
                                if (maxs.get(k).compareTo(max) == 0) {
                                    newS = false;
                                    break;
                                }
                            }
                        }
                        if (newS) {
                            mins.add(min);
                            maxs.add(max);
                        }
                    }
                    if (j >= points.length) {
                        break;
                    }
                    min = point;
                    max = point;
                    count = 0;
                    pushInd = j;
                    j--;
                }
                j++;
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
