import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

// Interfaces
interface Drawable {
    void draw();
}

interface Resizable {
    void resize(double factor);
}

// Abstract Classes
abstract class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getArea();
}

abstract class GeometricFigure extends Shape {
    public GeometricFigure(String name) {
        super(name);
    }
}

// Classes
class Circle extends GeometricFigure implements Drawable, Resizable {
    // Private variable
    private double radius;

    // Public constructor to access private variable
    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }

    @Override
    public void resize(double factor) {
        this.radius *= factor;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Protected overloaded method example
    protected void setRadius(double radius) {
        this.radius = radius;
    }

    // Protected overloaded method with a different parameter type
    protected void setRadius(int radius) {
        this.radius = radius;
    }
}

class Rectangle extends GeometricFigure implements Drawable, Resizable {
    // Private variables
    private double width;
    private double height;

    // Public constructor to access private variables
    public Rectangle(double width, double height) {
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle with width " + width + " and height " + height);
    }

    @Override
    public void resize(double factor) {
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    // Protected overloaded method example
    protected void setDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Another overloaded method with different parameters
    protected void setDimensions(double size) {
        this.width = size;
        this.height = size;
    }
}

class Triangle extends GeometricFigure implements Drawable {
    // Private variables
    private double base;
    private double height;

    // Public constructor to access private variables
    public Triangle(double base, double height) {
        super("Triangle");
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a triangle with base " + base + " and height " + height);
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    // Protected overloaded method example
    protected void setBase(double base) {
        this.base = base;
    }

    // Another overloaded method with different parameter type
    protected void setBase(int base) {
        this.base = base;
    }

    protected void setHeight(double height) {
        this.height = height;
    }

    // Another overloaded method with different parameter type
    protected void setHeight(int height) {
        this.height = height;
    }
}

class Square extends Rectangle {
    // Public constructor to access private variables
    public Square(double side) {
        super(side, side);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a square with side " + getWidth());
    }

    @Override
    public void resize(double factor) {
        super.resize(factor);
    }

    // Protected overloaded method example (specific to Square)
    protected void setSide(double side) {
        setDimensions(side, side);
    }

    // Another overloaded method
    protected void setSide(int side) {
        setDimensions(side, side);
    }
}

// Main Class
public class Main {
    public static void main(String[] args) throws IOException {
        Random random = new Random(); // Create a Random object

        // Generate random values for the shapes
        double circleRadius = 1 + random.nextDouble() * 10; // Random radius between 1 and 10
        double rectangleWidth = 1 + random.nextDouble() * 10; // Random width between 1 and 10
        double rectangleHeight = 1 + random.nextDouble() * 10; // Random height between 1 and 10
        double triangleBase = 1 + random.nextDouble() * 10; // Random base between 1 and 10
        double triangleHeight = 1 + random.nextDouble() * 10; // Random height between 1 and 10
        double squareSide = 1 + random.nextDouble() * 10; // Random side length between 1 and 10

        // Create the shapes with random dimensions
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(circleRadius));
        shapes.add(new Rectangle(rectangleWidth, rectangleHeight));
        shapes.add(new Triangle(triangleBase, triangleHeight));
        shapes.add(new Square(squareSide));

        // Process each shape
        for (Shape shape : shapes) {
            System.out.println("Shape: " + shape.getName() + ", Area: " + shape.getArea());
            if (shape instanceof Drawable) {
                ((Drawable) shape).draw();
            }
            if (shape instanceof Resizable) {
                ((Resizable) shape).resize(1.5);
                System.out.println("Shape: " + shape.getName() + " resized, new area: " + shape.getArea());
            }
            System.out.println();
        }
    }
}
