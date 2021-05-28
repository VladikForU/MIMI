package com.lab2;
import java.util.Scanner;
public class Lab1 {
    public static void main(String[] args) {
        Point3d a,b,c;
        Scanner scanner=new Scanner(System.in);
        a=new Point3d(scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());
        b=new Point3d(scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());
        c=new Point3d(scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());
        if(a.equals(b) || b.equals(c) || c.equals(a)){System.out.print("некоторые точки совпадают");}
        else{System.out.print(Point3d.computeArea(a,b,c));}
    }
}
