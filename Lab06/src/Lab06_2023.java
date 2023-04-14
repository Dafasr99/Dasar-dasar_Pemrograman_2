import java.util.Scanner;
import java.util.InputMismatchException;

public class Lab06_2023 {
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D();
        Circle2D c2 = new Circle2D(2, 2, 2.5);
        Circle2D c3 = new Circle2D(5.0, 6.0, 3.1);
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
        System.out.printf("Area of %s is %.2f%n", "c2", c2.getArea());
        System.out.printf("Perimeter of %s is %.2f%n", "c2", c2.getPerimeter());
        System.out.println("c2 contains point (3, 3)? " + c2.contains(3, 3));
        System.out.println("c3 contains circle Circle2D(4, 5, 8.5)? " + c3.contains(new Circle2D(4, 5, 8.5)));
        System.out.println("c1 overlaps circle Circle2D(3, 5, 0.3)? " + c1.overlaps(new Circle2D(3, 5, 0.3)));
        System.out.println("c2 overlaps c3? " + c2.overlaps(c3));
        System.out.println("c1 contains c1? " + c1.contains(c1));
        System.out.println("Circle Circle2D(1, 0, 7) contains c1? " + new Circle2D(1, 0, 7).contains(c1));
        System.out.printf("The hashcode of c1 is %h%n", c1.hashCode());
        System.out.printf("The hashcode of new Circle2D() is %h%n", new Circle2D().hashCode());
        Circle2D c4 = new Circle2D("Lingkaran dengan radius 250 hidden here.");
        System.out.println("c4: " + c4);
        System.out.println("Total number of circles created: " + Circle2D.getNumberOfCircle());
    }
} 

/**
Circle2D class represents a circle with center (x,y) and radius.
The class provides methods to calculate the area and perimeter of the circle, 
and to check if a point or another circle is inside or overlaps the circle.
*/

class Circle2D {
    // Data fields
    private double x;
    private double y;
    private double radius;
    private static int numberOfCircle = 0;

    // General constructor for circle with center 
    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        numberOfCircle++;
    }

    // Default constructor for circle with center (0, 0, 0, 0) and radius 1.0
    public Circle2D() {
        this(0.0, 0.0, 1.0);
    }

    // constructor for circle with center (0.0, 0.0) and radius extracted from the input string
    public Circle2D(String str) {
        // try to extract a double from the input string
        try {
            Scanner s = new Scanner(str).useDelimiter("\\D+");
            double r = s.nextDouble();
            // check if radius is negative
            if (r >= 0) {
                radius = r;
            } else {
                System.err.println("Error: Invalid input data for Circle2D constructor (negative radius)");
                radius = 0.0;
            }
            x = y = 0.0;
            numberOfCircle++;
            s.close();
        // catch exception if input string does not contain a double
        } catch (InputMismatchException e) {
            System.err.println("Error: Invalid input data for Circle2D constructor (not a double)");
            radius = 0.0;
            x = y = 0.0;
        }
    }
    
    // String representation of a circle
    @Override
    public String toString() {
        return String.format("Circle: center (%.2f,%.2f), radius %.2f, hashcode %x", x, y, radius, hashCode());
    }
        
    // Getter methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public static int getNumberOfCircle() {
        return numberOfCircle;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    // Setter methods
    public boolean contains(double x, double y) {
        double d = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        return d < radius;
    }

    // Check if this circle contains the circle passed as argument
    public boolean contains(Circle2D circle) {
        double d = Math.sqrt(Math.pow(circle.getX() - x, 2) + Math.pow(circle.getY() - y, 2)); 
        return d + circle.getRadius() <= radius;
    }

    // Check if this circle overlaps the circle passed as argument
    public boolean overlaps(Circle2D circle) {
        double d = Math.sqrt(Math.pow(circle.getX() - x, 2) + Math.pow(circle.getY() - y, 2)); 
        return d < radius + circle.getRadius();
    }

}
