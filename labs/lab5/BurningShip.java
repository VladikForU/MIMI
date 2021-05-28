package com.lab5;

import com.lab4.Complex;
import com.lab4.FractalGenerator;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    private Rectangle2D.Double range;
    public Rectangle2D.Double getRange(){return range;}

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x=-2;
        range.y=-1.5;
        range.width=3;
        range.height=3;
        this.range=range;
    }

    @Override
    public int numIterations(double x, double y){
        int iter;
        double xm=x*range.width+range.x,ym=y*range.height+range.y;
        Complex z=new Complex(0,0);
        for(iter=0;iter<MAX_ITERATIONS && z.modulsqr()<4;iter++){
            z=new Complex(Math.abs(z.x),Math.abs(z.y));
            z=z.mul(z).plus(new Complex(xm,ym));
        }
        return iter==MAX_ITERATIONS ? -1 : iter;
    }
}
