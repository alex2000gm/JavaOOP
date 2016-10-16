/**
 * Created by alex2000 on 08.10.16.
 */
// TODO: 09.10.16 добавить exceptions

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

    private double width;
    private double height;
    private double area;
    private double perimeter;

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

    public GeometricalFigure(double rectangleSideA) {
        lengthOfSideA = rectangleSideA;
        lengthOfSideB = rectangleSideA;
    }

    public GeometricalFigure(double circleRadius, String r) {
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










