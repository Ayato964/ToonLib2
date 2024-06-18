package org.ayato.component;

public class Rotate {
    private double radians;
    public Rotate(int rotate){
        radians = Math.toRadians(rotate);
    }
    public Rotate(){
        this(0);
    }
    public Vector2D transRotate(Vector2D vector){
        int newX = (int) (vector.x() * Math.cos(radians) - vector.y() * Math.sin(radians));
        int newY = (int) (vector.x() * Math.sin(radians) + vector.y() * Math.cos(radians));
        return new Vector2D(newX, newY);
    }

    public double getRadian() {
        return radians;
    }
}
