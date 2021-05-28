package com.lab2;
public class Point3d {
    private double xCoord,yCoord,zCoord;
    public Point3d(double x,double y,double z){
        xCoord=x;yCoord=y;zCoord=z;
    }
    public Point3d(){this(0,0,0);}
    public double getxCoord(){return xCoord;}
    public double getyCoord(){return yCoord;}
    public double getzCoord(){return zCoord;}
    public void setxCoord(double xCoord){this.xCoord=xCoord;}
    public void setyCoord(double yCoord){this.yCoord=yCoord;}
    public void setzCoord(double zCoord){this.zCoord=zCoord;}

    public double distanceTo(Point3d point3d){return Math.sqrt((xCoord-point3d.xCoord)*(xCoord-point3d.xCoord)+(yCoord-point3d.yCoord)*(yCoord-point3d.yCoord)+(zCoord-point3d.zCoord)*(zCoord-point3d.zCoord));}
    public static double computeArea(Point3d p1,Point3d p2,Point3d p3){
        double a=p1.distanceTo(p2),b=p2.distanceTo(p3),c=p3.distanceTo(p1),p=(a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3d point3d = (Point3d) o;
        return Double.compare(point3d.xCoord, xCoord) == 0 && Double.compare(point3d.yCoord, yCoord) == 0 && Double.compare(point3d.zCoord, zCoord) == 0;
    }
}
