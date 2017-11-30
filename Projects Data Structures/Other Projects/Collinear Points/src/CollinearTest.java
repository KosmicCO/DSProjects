
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class CollinearTest {

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("collinear-testing/collinear/test.txt");
        if (args.length == 1) {
            in = new In(args[0]);
        }
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            try{
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            }catch(Exception e){
                points[i] = null;
            }
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            if(p != null){
            p.draw();
            }
        }
        StdDraw.show();

//        Point[] ppp = new Point[1];
//        ppp[0] = null;
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        //BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        int s = 0;
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            s += 40;
            segment.draw();
        }
        StdDraw.show();
        StdOut.print("done");
    }
}
