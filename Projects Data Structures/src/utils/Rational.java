/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Iterator;

/**
 *
 * @author cbarnum18
 */
public class Rational implements Iterable<Rational> {

    private final int numerator;
    private final int denominator;

    public Rational(int n, int d) {
        numerator = n;
        denominator = d;
    }

    @Override
    public Iterator<Rational> iterator() {
        return new RationalsIterator();
    }

    @Override
    public String toString() {
        return "" + numerator + "/" + denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
    
    

    private class RationalsIterator implements Iterator<Rational> {

        private int n = 2;
        private int d = 0;
        private boolean first = true;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rational next() {
            if (first) {
                first = false;
                return new Rational(0, 1);
            }
            n--;
            d++;
            Rational r = new Rational(n, d);
            if (n == 1) {
                n = d + 1;
                d = 1;
            }
            return r;
        }
    }
}
