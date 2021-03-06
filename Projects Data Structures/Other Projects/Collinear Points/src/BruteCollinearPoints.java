
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
public class BruteCollinearPoints { // implements CollinearPoints{

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] poi) {
        if (poi == null) {
            throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < poi.length - 1; i++) {
            if (poi[i] == null || poi[i] == poi[i + 1]) {
                throw new IllegalArgumentException();
            }
        }
        
        Point[] points = Arrays.copyOf(poi, poi.length);
        Arrays.sort(points);
        

        List<LineSegment> segList = new ArrayList<LineSegment>();
        Point min, max;
        Comparator<Point> ref;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        ref = points[i].slopeOrder();
                        if (ref.compare(points[j], points[k]) == 0
                                && ref.compare(points[k], points[m]) == 0) {
                            min = points[i];
                            max = points[i];

                            if (points[j].compareTo(max) > 0) {
                                max = points[j];
                            } else if (points[j].compareTo(min) < 0) {
                                min = points[j];
                            }

                            if (points[k].compareTo(max) > 0) {
                                max = points[k];
                            } else if (points[k].compareTo(min) < 0) {
                                min = points[k];
                            }

                            if (points[m].compareTo(max) > 0) {
                                max = points[m];
                            } else if (points[m].compareTo(min) < 0) {
                                min = points[m];
                            }
                            segList.add(new LineSegment(min, max));
                        }
                    }
                }
            }
        }

        segments = segList.toArray(new LineSegment[segList.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }
}
