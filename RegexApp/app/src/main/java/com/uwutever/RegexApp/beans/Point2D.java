package com.uwutever.RegexApp.beans;

public class Point2D {

    protected double x;

    protected double y;
    
    Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Point2D() {
        this.x = 0;
        this.y = 0;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setLocation(Point2D point) {
        this.x = point.x;
        this.y = point.y;
    }
}