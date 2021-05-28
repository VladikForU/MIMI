package com.lab4;

public class Complex{
    public double x,y;
    public Complex(double x, double y){this.x=x; this.y=y;}
    public Complex plus(Complex z){return new Complex(this.x+z.x,this.y+z.y);}
    public Complex mul(Complex z){return new Complex(this.x*z.x-this.y*z.y,this.x*z.y+z.x*this.y);}
    public double modulsqr(){return x*x+y*y;}
}
