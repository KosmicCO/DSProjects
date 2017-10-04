/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cbarnum18
 */
public class PercolationStats {
    
    private int length;
    private int trials;
    private double[] result;
    
    public PercolationStats(int length, int trials){
        this.length = length;
        this.trials = trials;
        result = new double[trials];
    }
    
    public double mean(){
        return 0.0;
    }
    
    public double stddev(){
        return 0.0;
    }
    
    public double confidenceLo(){
        return 0.0;
    }
    
    public double confidenceHi(){
        return 0.0;
    }
}
