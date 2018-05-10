
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Picture;
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
        if (x == 0 || x == pic.width() - 1) {
            return 1000;
        }
        int left = pic.getRGB(x - 1, y);
        int right = pic.getRGB(x + 1, y);
        return vecS(left & 0xFF - right & 0xFF,
                (left & 0xFF00) >> 8 - (right & 0xFF00) >> 8,
                (left & 0xFF0000) >> 16 - (right & 0xFF0000) >> 16);
    }

    private double dy(int x, int y) {
        if (y == 0 || y == pic.width() - 1) {
            return 1000;
        }
        int left = pic.getRGB(x, y - 1);
        int right = pic.getRGB(x, y - 1);
        return vecS(left & 0xFF - right & 0xFF,
                (left & 0xFF00) >> 8 - (right & 0xFF00) >> 8,
                (left & 0xFF0000) >> 16 - (right & 0xFF0000) >> 16);
    }

    private double vecS(double... vector) {
        double sum = 0;
        for (double d : vector) {
            sum += d * d;
        }
        return sum;
    }

    public int[] findVerticalSeam() {
        int[][] prev = new int[pic.width()][pic.height() - 1];
        MinPQ<Pos> queue = new MinPQ<>();
        for (int i = 0; i < prev[0].length + 1; i++) {
            queue.insert(new Pos(i, 0, this));
        }

        for (int[] rows : prev) {
            Arrays.fill(rows, -1);
        }

        Pos cur = queue.delMin();
        while (cur.y < prev[0].length && !queue.isEmpty()) {
            for (int i = -1; i <= 1; i++) {
                if (cur.x + i >= 0 && cur.x + i < prev.length && prev[cur.x + i][cur.y] == -1) {
                    queue.insert(new Pos(cur.x + i, cur.y + 1, this, cur.energy));
                    prev[cur.x + i][cur.y] = cur.x; // Note: prev index starts after top row
                }
            }
        }

        int[] seam = new int[prev[0].length + 1];
        seam[seam.length - 1] = cur.x;
        int ind = cur.x;
        for (int i = seam.length - 2; i > 0; i--) {
            ind = prev[ind][i];
            seam[i] = ind;
        }

        return seam;
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
    public int[] findHorizontalSeam() { // sequence of indices for vertical seam
        int[][] prev = new int[pic.height()][pic.width() - 1];
        MinPQ<Pos> queue = new MinPQ<>();
        for (int i = 0; i < prev[0].length + 1; i++) {
            queue.insert(new Pos(0, i, this));
        }

        for (int[] rows : prev) {
            Arrays.fill(rows, -1);
        }

        Pos cur = queue.delMin();
        while (cur.x < prev[0].length && !queue.isEmpty()) {
            for (int i = -1; i <= 1; i++) {
                if (cur.y + i >= 0 && cur.y + i < prev.length && prev[cur.y + i][cur.x] == -1) {
                    queue.insert(new Pos(cur.x + 1, cur.y + i, this, cur.energy));
                    prev[cur.y + i][cur.x] = cur.y; // Note: prev index starts after top col
                }
            }
        }

        int[] seam = new int[prev[0].length + 1];
        seam[seam.length - 1] = cur.y;
        int ind = cur.y;
        for (int i = seam.length - 2; i > 0; i--) {
            ind = prev[ind][i];
            seam[i] = ind;
        }

        return seam;
    }

    public void removeHorizontalSeam(int[] seam) { // remove horizontal seam from current picture
        Picture np = new Picture(pic.width(), pic.height() - 1);
        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                if (j < seam[i]) {
                    np.set(i, j, pic.get(i, j));
                } else {
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
                if (j < seam[i]) {
                    np.set(j, i, pic.get(j, i));
                } else {
                    np.set(j, i, pic.get(j + 1, i));
                }
            }
        }
        pic = np;
    }

    private class Pos implements Comparable<Pos> {

        private final int x;
        private final int y;
        private final double energy;

        public Pos(int x, int y, SeamCarver sc) {
            this.x = x;
            this.y = y;
            energy = sc.energy(x, y);
        }

        public Pos(int x, int y, SeamCarver sc, double parentE) {
            this.x = x;
            this.y = y;
            energy = sc.energy(x, y) + parentE;
        }

        @Override
        public int compareTo(Pos t) {
            return Double.compare(energy, t.energy);
        }
    }
}
