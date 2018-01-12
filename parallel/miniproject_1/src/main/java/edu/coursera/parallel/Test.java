package edu.coursera.parallel;

import java.util.concurrent.RecursiveAction;

public class Test {
    public static void main(String[] args) {
        double[] a = new double[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        SumArrayTask t = new SumArrayTask(0, a.length, a);
    }
    static class SumArrayTask extends RecursiveAction {

        private final int lo;
        private final int hi;
        private final double[] a;
        private double sum;

        public SumArrayTask(int lo, int hi, double[] a) {
            this.lo = lo;
            this.hi = hi;
            this.a = a;
        }

        @Override
        protected void compute() {
            if (hi - lo <= 2) {

            }
        }
    }
}
