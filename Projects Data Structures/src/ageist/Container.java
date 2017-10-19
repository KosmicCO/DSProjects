/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageist;

import utils.Vec;

/**
 *
 * @author cbarnum18
 */
public class Container {
    private Vec[] nodes;
    private final double[][] data;
    private final int dim;
    
    public Container(int dim, int size, int trials){
        nodes = new Vec[size];
        this.dim = dim;
        data = new double [10][trials - 1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = Vec.rand(dim);
        }
    }
    
    private void runTrial(){
        
    }
}
