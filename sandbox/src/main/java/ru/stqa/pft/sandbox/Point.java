package ru.stqa.pft.sandbox;

public class Point {

    public double a;
    public double b;


    public Point(double a,double b){
        this.a = a;
        this.b = b;

    }

    public static double distance (Point p1, Point p2) {
        return Math.sqrt (Math.pow(( p2.a - p1.a ), 2) + (Math.pow(( p2.b - p1.b ), 2 )));

    }

    public static void main(String[] args) {

        Point p1 = new Point (1,1);
        Point p2 = new Point (2,2);
        System.out.println ("Расстояние между точками равно  " + distance(p1, p2) );
    }

}
