/**
 * Created by alex2000 on 08.10.16.
 */

public class GeometricalFigure {
    double lengthOfSideA;
    double lengthOfSideB;
    double radius;
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;

    double width;
    double height;
    double area;
    double perimeter;

    public GeometricalFigure(double coordinateX1, double coordinateY1, double coordinateX2, double coordinateY2, double coordinateX3, double coordinateY3) {
        x1 = coordinateX1;
        y1 = coordinateY1;
        x2 = coordinateX2;
        y2 = coordinateY2;
        x3 = coordinateX3;
        y3 = coordinateY3;
    }

    public GeometricalFigure(double rectangleSideA, double rectangleSideB) {
        lengthOfSideA = rectangleSideA;
        lengthOfSideB = rectangleSideB;
    }

    public GeometricalFigure(double circleRadius) {
        radius = circleRadius;
    }

    public GeometricalFigure() {
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }
}

interface Shape {
    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();
}

class Triangle extends GeometricalFigure implements Shape {
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        super(x1, y1, x2, y2, x3, y3);
    }

    // TODO: сделать верификацию треугольника
    @Override
    public double getWidth() {
        double[] x = {this.x1, this.x2, this.x3};
        double maxX = this.x1;
        double minX = this.x1;
        for (double i : x) {
            if (i > maxX) {
                maxX = i;
            } else if (i < minX) {
                minX = i;
            }
        }
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double[] y = {this.y1, this.y2, this.y3};
        double maxY = this.y1;
        double minY = this.y1;
        for (double i : y) {
            if (i > maxY) {
                maxY = i;
            } else if (i < minY) {
                minY = i;
            }
        }
        return maxY - minY;
    }

    private double aBLength = lengthOfSide(this.x1, this.y1, this.x2, this.y2);
    private double bCLength = lengthOfSide(this.x2, this.y2, this.x3, this.y3);
    private double aCLength = lengthOfSide(this.x1, this.y1, this.x3, this.y3);

    @Override
    public double getPerimeter() {
        return aBLength + bCLength + aCLength;
    }

    private static double lengthOfSide(double x1, double y1, double x2, double y2) {
        final int DEGREE = 2;
        return Math.sqrt(Math.pow((x2 - x1), DEGREE) + Math.pow((y2 - y1), DEGREE));
    }

    @Override
    public double getArea() {
        final double HALF = 2;
        double halfPerimeter = getPerimeter() / HALF;
        return Math.sqrt(halfPerimeter * (halfPerimeter - aBLength) * (halfPerimeter - bCLength) * (halfPerimeter - aCLength));
    }

    @Override
    public String toString() {
        String stringTriangle = "";
        return stringTriangle.concat("Длины сторон треугольника = " + aBLength + ", " + bCLength + ", " + aCLength + " Ширина = " + getWidth() + "  Высота = " + getHeight() + " Периметр = " + getPerimeter() + " Площадь = " + getArea());
    }
}

class Rectangle extends GeometricalFigure implements Shape {
    public Rectangle(double lengthOfSideA, double lengthOfSideB) {
        super(lengthOfSideA, lengthOfSideB);
    }

    public Rectangle(double lengthOfSideA) {
        super(lengthOfSideA);
        lengthOfSideB = this.lengthOfSideA; // TODO: подумать над размещением этой строки и над строкой вообще
    }

    public Rectangle() {
    }

    @Override
    public double getWidth() {
        return Math.max(this.lengthOfSideA, this.lengthOfSideB);
    }

    @Override
    public double getHeight() {
        return Math.min(this.lengthOfSideA, this.lengthOfSideB);
    }

    @Override
    public double getArea() {
        return this.lengthOfSideA * this.lengthOfSideB;
    }

    @Override
    public double getPerimeter() {
        return (this.lengthOfSideA + this.lengthOfSideB) * 2;
    }

    @Override
    public String toString() {
        String stringRectangle = "";
        return stringRectangle.concat("Длины сторон прямоугольника = " + lengthOfSideA + " и " + lengthOfSideB + " Ширина = " + getWidth() + "  Высота = " + getHeight() + " Периметр = " + getPerimeter() + " Площадь = " + getArea());
    }
}

class Square extends Rectangle implements Shape {
    public Square(double lengthOfSideA) {
        super(lengthOfSideA);
    }

    @Override
    public double getWidth() {
        return this.lengthOfSideA;
    }

    @Override
    public double getHeight() {
        return this.lengthOfSideA;
    }

    @Override
    public double getArea() {
        return this.lengthOfSideA * this.lengthOfSideA;
    }

    @Override
    public double getPerimeter() {
        final int NUMBEROFSIDES = 4;
        return this.lengthOfSideA * NUMBEROFSIDES;
    }

    @Override
    public String toString() {
        String stringSquare = "";
        return stringSquare.concat("Длины сторон квадрата = " + lengthOfSideA + " и " + lengthOfSideA + " Ширина = " + getWidth() + "  Высота = " + getHeight() + " Периметр = " + getPerimeter() + " Площадь = " + getArea());
    }
}

class Circle extends GeometricalFigure {
    public Circle(double radius) {
        super(radius);
    }

    @Override
    public double getWidth() {
        return this.radius * 2;
    }

    @Override
    public double getHeight() {
        return this.radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public String toString() {
        String stringCircle = "";
        return stringCircle.concat("Высота круга = " + getHeight() + " Ширина = " + getWidth() + " Периметр = " + getPerimeter() + " Площадь = " + getArea());
    }
}


