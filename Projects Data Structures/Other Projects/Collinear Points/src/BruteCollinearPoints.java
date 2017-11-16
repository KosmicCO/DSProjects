
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
public class BruteCollinearPoints implements CollinearPoints{

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if(points[i] == null || points[i] == points[i + 1]){
                throw new IllegalArgumentException();
            }
        }
        
        List<LineSegment> segList = new ArrayList<LineSegment>();
        Point min, max;
        Comparator<Point> ref;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        ref = points[i].slopeOrder();
                        if (ref.compare(points[j], points[k]) == 0
                                && ref.compare(points[k], points[l]) == 0) {
                            min = max = points[i];

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

                            if (points[l].compareTo(max) > 0) {
                                max = points[l];
                            } else if (points[l].compareTo(min) < 0) {
                                min = points[l];
                            }
                            segList.add(new LineSegment(min, max));
                        }
                    }
                }
            }
        }

        
        segments = (LineSegment[]) segList.toArray(new LineSegment[segList.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}
