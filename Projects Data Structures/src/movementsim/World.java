/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movementsim;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import utils.Vec;

/**
 *
 * @author cbarnum18
 */
public class World {

    public final static double increment = 0.01;
    public final static double duration = Double.POSITIVE_INFINITY;

    private Mov[] movList;
    private int numMovs;

    public World(int numMovs) {
        this.numMovs = numMovs;
        movList = new Mov[numMovs];
        for (int i = 0; i < numMovs; i++) {
            movList[i] = new Mov(3);
        }

    }

    public void updateDraw() {
        StdDraw.clear();
        StdDraw.setPenColor(127, 127, 0);
        StdDraw.circle(0.0, 0.0, 1.0);
        StdDraw.setPenColor();
        for (Mov m : movList) {
            m.update();
            m.draw();
        }
        double unit = (movList[0].pos.getCom(2) + 16) / 4;
        if(unit > 0){
        StdDraw.circle(0.0, 0.0, (movList[0].pos.getCom(2) + 4) / 4);
        StdDraw.line(0.0, 0.0, movList[0].pos.getCom(0) * unit, movList[0].pos.getCom(1) * unit);
        }
        StdDraw.show();
    }

    private class Mov {

        public double CENTER_ACCEL = -1 * increment;
        public double MULTI = 0.1;
        public double RANDOM_ACCEL = 0 * increment;
        public double FRICTION = 1;//0.999;

        private Vec pos;
        private Vec vel;
        private final int dim;

        public Mov(int dim) {
            this.dim = dim;
            pos = Vec.rand(dim);
            vel = Vec.rand(dim);
        }

        public void update() {
            Vec nVel = vel.add(Vec.rand(dim).scale(RANDOM_ACCEL));
            double psn = pos.scale(MULTI).norm();
            vel = nVel.add(pos.scale(CENTER_ACCEL * psn)).scale(FRICTION);
            pos = pos.add(vel.scale(increment));
        }

        public void draw() {
            if (pos.dim == 3) {
                if (pos.getCom(2) > -4) {
                    double dist = (pos.getCom(2) + 4) / 4;
                    StdDraw.filledSquare(pos.getCom(0) * dist, pos.getCom(1) * dist, dist / 128);
                }
            }else{
                StdDraw.filledSquare(pos.getCom(0), pos.getCom(1), 0.02);
            }
        }
    }

    public static void main(String[] args) {
        World w = new World(10);
        double x, y;
        x = y = 4;
        StdDraw.setXscale(-x, x);
        StdDraw.setYscale(-y, y);
        StdDraw.enableDoubleBuffering();
        double time = 0.0;
        while (time < duration) {
            w.updateDraw();
            time += increment;
        }
    }
}
