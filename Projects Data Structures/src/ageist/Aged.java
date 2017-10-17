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
public class Aged {
    private Vec pos;
    private Vec vel;
    private int dim;
    
    public Aged(int dim){
        this.dim = dim;
        pos = Vec.rand(dim);
        
    }
}
