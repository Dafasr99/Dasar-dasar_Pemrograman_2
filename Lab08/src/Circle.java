// ****************************************************************
// Circle.java
//
// Define a Circle class with methods to create and draw
// a circle of random size, color, and location.
// ****************************************************************

import java.awt.*;
import java.util.Random;

public class Circle {
    private int centerX, centerY;
    private int radius;
    private Color color;
    private static Random generator = new Random();

    //---------------------------------------------------------
    // Creates a circle with center at point given, random radius and color
    // -- radius 25..74
    // -- color RGB value 0..16777215 (24-bit)
    //---------------------------------------------------------

    public Circle(Point point) {
        radius = Math.abs(generator.nextInt()) % 50 + 25;
        color = new Color(Math.abs(generator.nextInt()) % 16777216);
        centerX = point.x;
        centerY = point.y;
    }

    //---------------------------------------------------------
    // Moves the circle to the specified point
    //---------------------------------------------------------
    public void move(Point point) {
        centerX = point.x;
        centerY = point.y;
    }

    //---------------------------------------------------------
    // Determines whether the specified point is inside the circle
    //---------------------------------------------------------
    public boolean isInside(Point point) {
        int dx = point.x - centerX;
        int dy = point.y - centerY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < radius;
    }

    //---------------------------------------------------------
    // Draws the circle on the graphics object given
    //---------------------------------------------------------
    public void draw(Graphics page) {
        page.setColor(color);
        page.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }

    //---------------------------------------------------------
    // Returns the x-coordinate of the circle's center
    //---------------------------------------------------------
    public int getCenterX() {
        return centerX;
    }

    //---------------------------------------------------------
    // Returns the y-coordinate of the circle's center
    //---------------------------------------------------------
    public int getCenterY() {
        return centerY;
    }
}
