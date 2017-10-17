/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author cbarnum18
 */
public class Vec {

    public final int dim;
    private final double[] vec;

    public Vec(double... vec) {
        this.vec = vec;
        dim = vec.length;
    }
    
    public static Vec rand(int dim){
        double[] vec = new double[dim];
        for (int i = 0; i < dim; i++) {
            vec[i] = StdRandom.uniform();
        }
        return new Vec(vec);
    }
    
    public Vec scale(double s) {
        double[] nVec = new double[dim];
        for (int i = 0; i < dim; i++) {
            nVec[i] = vec[i] * s;
        }
        return new Vec(nVec);
    }
    
    public Vec subtract(Vec v){
        if (v.dim == dim) {
            double[] nVec = new double[dim];
            for (int i = 0; i < dim; i++) {
                nVec[i] = vec[i] - v.vec[i];
            }
            return new Vec(nVec);
        }
        throw new IllegalArgumentException();
    }

    public Vec add(Vec v) {
        if (v.dim == dim) {
            double[] nVec = new double[dim];
            for (int i = 0; i < dim; i++) {
                nVec[i] = vec[i] + v.vec[i];
            }
            return new Vec(nVec);
        }
        throw new IllegalArgumentException();
    }

    public double getCom(int i) {
        if (0 <= i && i < dim) {
            return vec[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }
    
    public double dot(Vec v){
        if(v.dim == dim){
            double sum = 0.0;
            for (int i = 0; i < dim; i++) {
                sum += vec[i] * v.vec[i];
            }
            return sum;
        }
        throw new IllegalArgumentException();
    }
    
    public double norm(){
        double sum = 0.0;
        for (int i = 0; i < dim; i++) {
            sum += vec[i] * vec[i];
        }
        return sum;
    }
}
