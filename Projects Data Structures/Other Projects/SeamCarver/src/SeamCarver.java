
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

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

    public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
        MinPQ q = new MinPQ();
    }

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
}
