
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

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

    private final int length;
    private final double size;
    private final int trials;
    private final double[] result;

    private final double[] data = new double[4];
    private byte calculated = 0;

    public PercolationStats(int length, int trials) {
        this.length = length;
        size = length * length;
        this.trials = trials;
        result = new double[trials];

        for (int i = 0; i < trials; i++) {
            runTrial(i);
        }
    }

    private void runTrial(int trialNum) {
        Percolation p = new Percolation(length);
        int opened = 0;
        int x, y;
        while (!p.percolates()) {
            x = StdRandom.uniform(length) + 1;
            y = StdRandom.uniform(length) + 1;
            if (!p.isOpen(y, x)) {
                p.open(y, x);
                opened++;
            }
        }
        result[trialNum] = opened / size;
    }

    public double mean() {
        if ((calculated & 0x8) == 0x0) {
            data[0] = StdStats.mean(result);
            calculated ^= 0x8;
        }
        return data[0];
    }

    public double stddev() {
        if ((calculated & 0x4) == 0x0) {
            data[1] = StdStats.stddev(result);
            calculated ^= 0x4;
        }
        return data[1];
    }

    public double confidenceLo() {
        if ((calculated & 0x2) == 0x0) {
            data[2] = mean() - 2 * stddev();
            calculated ^= 0x2;
        }
        return data[2];
    }

    public double confidenceHi() {
        if ((calculated & 0x1) == 0x0) {
            data[3] = mean() + 2 * stddev();
            calculated ^= 0x1;
        }
        return data[3];
    }

    public static void main(String[] args) {
        int length = 100;
        int trials = 100;
        if (args.length == 2) {
            length = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
        }
        PercolationStats ps = new PercolationStats(length, trials);
        System.out.println(ps.mean() + "\n" + ps.stddev() + "\n[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
