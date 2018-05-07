
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;
import java.awt.Color;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cbarnum18
 */
public class SeamCarver {

    private Picture pic;

    public SeamCarver(Picture picture) { // create a seam carver object based on the given picture
        pic = picture;
    }

    public Picture picture() { // current picture
        return new Picture(pic);
    }

    public int width() { // width of current picture
        return pic.width();
    }

    public int height() { // height of current picture
        return pic.height();
    }

    public double energy(int x, int y) { // energy of pixel at column x and row y
        if (x < 0 || y < 0 || x >= pic.width() || y >= pic.height()) {
            throw new IllegalArgumentException();
        }
        return Math.sqrt(dx(x, y) + dy(x, y));
    }

    private double dx(int x, int y) {
        int bounds = (x == 0 || x == (pic.width() - 1)) ? 0x0 : (x == 0) ? 0x2 : 0x1;
        Color col;
        if (bounds == 0x0) {
            col = pic.get(x - 1, y);
            Color col2 = pic.get(x + 1, y);
            return vecS(col.getBlue() - col2.getBlue(), col.getGreen() - col2.getGreen(),
                    col.getRed() - col2.getRed(), col.getAlpha() - col2.getAlpha());
        }
        col = bounds == 0x1 ? pic.get(1, y) : pic.get(x - 1, y);
        return vecS(1000.0 - col.getBlue(), 1000.0 - col.getGreen(),
                1000.0 - col.getRed(), 1000.0 - col.getAlpha());
    }

    private double dy(int x, int y) {
        int bounds = (y == 0 || y == (pic.width() - 1)) ? 0x0 : (y == 0) ? 0x2 : 0x1;
        Color col;
        if (bounds == 0x0) {
            col = pic.get(x, y - 1);
            Color col2 = pic.get(x, y + 1);
            return vecS(col.getBlue() - col2.getBlue(), col.getGreen() - col2.getGreen(),
                    col.getRed() - col2.getRed(), col.getAlpha() - col2.getAlpha());
        }
        col = bounds == 0x1 ? pic.get(x, 1) : pic.get(x, y - 1);
        return vecS(1000.0 - col.getBlue(), 1000.0 - col.getGreen(),
                1000.0 - col.getRed(), 1000.0 - col.getAlpha());
    }

    private double vecS(double... vector) {
        double sum = 0;
        for (double d : vector) {
            sum += d * d;
        }
        return sum;
    }
    
    public int[] findHorizontalSeam() {
        int[] top = new int[pic.width()];
        double[] topEn = new double[pic.width()];
        IndexMinPQ<Double> sort = new IndexMinPQ<>(top.length);
        for (int i = 0; i < top.length; i++) {
            topEn[i] = energy(i, 0);
            sort.insert(i, topEn[i]);
        }
        for (int i = 0; i < top.length; i++) {
            top[i] = sort.delMin();
        }
    }

    /*
    public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
        int[] top = new int[pic.width()];
        double[] topEn = new double[pic.width()];
        IndexMinPQ<Double> sort = new IndexMinPQ<>(top.length);
        for (int i = 0; i < top.length; i++) {
            topEn[i] = energy(i, 0);
            sort.insert(i, topEn[i]);
        }
        for (int i = 0; i < top.length; i++) {
            top[i] = sort.delMin();
        }
        
        double[][] distTo = new double[pic.width()][pic.height()];
        int[][] nodeTo = new int[pic.width()][pic.height()];
        for (double[] suba : distTo) {
            Arrays.fill(suba, -1);
        }
        
        int ind = 1;
        
        Pos cur;
        double topNextEn = top[1];
        int endInd = -1;
        
        IndexMinPQ<Double> order = new IndexMinPQ<>(3);
        
        while(!q.isEmpty()){
            cur = q.dequeue();
            double energy = Double.POSITIVE_INFINITY;
            
            if(cur.y == distTo[0].length - 1){ // If made it to the bottom
                endInd = cur.x;
                break;
            }
            
            for (int i = -1; i <= 1; i++) { // In general
                if(!(cur.x + i < 0 || cur.x + i >= distTo.length)){
                    distTo[cur.x + 1][cur.y] = energy(cur.x + i, cur.y) + distTo[cur.x][cur.y];
                    order.insert(cur.x + i, distTo[cur.x + 1][cur.y]);
                }
            }
            
            while(!order.isEmpty()){
                q.enqueue(new Pos(order.delMin(), cur.y + 1));
            }
        }
        
        double energy;
        for (int i = distTo.length - 1; i >= 0; --i) {
            for (int j = -1; j <= 1; j++) {
                
            }
        }
    }
*/

    public int[] findVerticalSeam() { // sequence of indices for vertical seam

    }

    public void removeHorizontalSeam(int[] seam) { // remove horizontal seam from current picture
        Picture np = new Picture(pic.width(), pic.height() - 1);
        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                if(j < seam[i]){
                    np.set(i, j, pic.get(i, j));
                }else{
                    np.set(i, j, pic.get(i, j + 1));
                }
            }
        }
        pic = np;
    }

    public void removeVerticalSeam(int[] seam) { // remove vertical seam from current picture
        Picture np = new Picture(pic.width() - 1, pic.height());
        for (int i = 0; i < pic.height(); i++) {
            for (int j = 0; j < pic.width(); j++) {
                if(j < seam[i]){
                    np.set(j, i, pic.get(j, i));
                }else{
                    np.set(j, i, pic.get(j + 1, i));
                }
            }
        }
        pic = np;
    }
    
    private class Pos{
        
        private final int x;
        private final int y;
        
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
