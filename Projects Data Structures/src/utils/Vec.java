/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author cbarnum18
 */
public class Vec {

    public final int dim;
    private final Double[] vec;

    public Vec(Double... vec) {
        this.vec = vec;
        dim = vec.length;
    }
    
    public Vec scale(double s) {
        Double[] nVec = new Double[dim];
        for (int i = 0; i < dim; i++) {
            nVec[i] = vec[i] * s;
        }
        return new Vec(nVec);
    }
    
    public Vec subtract(Vec v){
        if (v.dim == dim) {
            Double[] nVec = new Double[dim];
            for (int i = 0; i < dim; i++) {
                nVec[i] = vec[i] - v.vec[i];
            }
            return new Vec(nVec);
        }
        throw new IllegalArgumentException();
    }

    public Vec add(Vec v) {
        if (v.dim == dim) {
            Double[] nVec = new Double[dim];
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
