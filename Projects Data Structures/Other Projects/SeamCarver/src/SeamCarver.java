
import edu.princeton.cs.algs4.Picture;

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
        
    }            

    public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
        
    }
    
    public int[] findVerticalSeam() { // sequence of indices for vertical seam
        
    }

    public void removeHorizontalSeam(int[] seam) { // remove horizontal seam from current picture
        
    }

    public void removeVerticalSeam(int[] seam) { // remove vertical seam from current picture
        
    }
}
