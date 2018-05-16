
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
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        pic = new Picture(picture);
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
        if (x == 0 || x == pic.width() - 1 || y == 0 || y == pic.height() - 1) {
            return 1000;
        }
        return Math.sqrt(dx(x, y) + dy(x, y));
    }

    private double dx(int x, int y) {
        int left = pic.getRGB(x - 1, y);
        int right = pic.getRGB(x + 1, y);
        return vecS((left & 0xFF) - (right & 0xFF),
                ((left >> 8) & 0xFF) - ((right >> 8) & 0xFF),
                ((left >> 16) & 0xFF) - ((right >> 16) & 0xFF));
    }

    private double dy(int x, int y) {
        int left = pic.getRGB(x, y - 1);
        int right = pic.getRGB(x, y + 1);
        return vecS((left & 0xFF) - (right & 0xFF),
                ((left >> 8) & 0xFF) - ((right >> 8) & 0xFF),
                ((left >> 16) & 0xFF) - ((right >> 16) & 0xFF));
    }

    private double vecS(double... vector) {
        double sum = 0;
        for (double d : vector) {
            sum += d * d;
        }
        return sum;
    }

    public int[] findVerticalSeam() {
        if (pic.height() == 1) {
            return new int[1];
        }
        if (pic.width() == 1) {
            return new int[pic.height()];
        }
        int[][] prev = new int[pic.width()][pic.height() - 1];
        MinPQ<Pos> queue = new MinPQ<>();
        for (int i = 0; i < prev.length; i++) {
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
            cur = queue.delMin();
        }

        int[] seam = new int[prev[0].length + 1];
        seam[seam.length - 1] = cur.x;
        int ind = cur.x;
        for (int i = seam.length - 2; i > 0; i--) {
            ind = prev[ind][i];
            seam[i] = ind;
        }

        seam[0] = seam[1];
        return seam;
    }

    public int[] findHorizontalSeam() { // sequence of indices for vertical seam
        if (pic.width() == 1) {
            return new int[1];
        }
        if (pic.height() == 1) {
            return new int[pic.width()];
        }
        int[][] prev = new int[pic.height()][pic.width() - 1];
        MinPQ<Pos> queue = new MinPQ<>();
        for (int i = 0; i < prev.length; i++) {
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
            cur = queue.delMin();
        }

        int[] seam = new int[prev[0].length + 1];
        seam[seam.length - 1] = cur.y;
        int ind = cur.y;
        for (int i = seam.length - 2; i > 0; i--) {
            ind = prev[ind][i];
            seam[i] = ind;
        }
        seam[0] = seam[1];
        return seam;
    }

    public void removeHorizontalSeam(int[] seam) { // remove horizontal seam from current picture
        validate(seam, pic.height(), pic.width());
        Picture np = new Picture(pic.width(), pic.height() - 1);
        for (int i = 0; i < pic.height() - 1; i++) {
            for (int j = 0; j < pic.width(); j++) {
                if (i < seam[j]) {
                    np.setRGB(j, i, pic.getRGB(j, i));
                } else {
                    np.setRGB(j, i, pic.getRGB(j, i + 1));
                }
            }
        }
        pic = np;
    }

    public void removeVerticalSeam(int[] seam) { // remove vertical seam from current picture
        validate(seam, pic.width(), pic.height());
        Picture np = new Picture(pic.width() - 1, pic.height());
        for (int i = 0; i < pic.width() - 1; i++) {
            for (int j = 0; j < pic.height(); j++) {
                if (i < seam[j]) {
                    np.setRGB(i, j, pic.getRGB(i, j));
                } else {
                    np.setRGB(i, j, pic.getRGB(i + 1, j));
                }
            }
        }
        pic = np;
    }

    private void validate(int[] seam, int max, int len) {
        if (seam == null || seam.length != len) {
            throw new IllegalArgumentException();
        }
        for (int s : seam) {
            if (s < 0 || s >= max) {
                throw new IllegalArgumentException();
            }
        }
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

    public static void main(String[] args) {

    }
}
