/**
 * Created by alex2000 on 08.10.16.
 */
// TODO: 09.10.16 добавить exceptions

public class GeometricalFigure {
    private double lengthOfSideA;
    private double lengthOfSideB;
    private double radius;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
 /* private double width;
    private double height;
    private double area;
    private double perimeter; */

    public GeometricalFigure(double coordinateX1, double coordinateY1, double coordinateX2, double coordinateY2, double coordinateX3, double coordinateY3) {
        this.x1 = coordinateX1;
        this.y1 = coordinateY1;
        this.x2 = coordinateX2;
        this.y2 = coordinateY2;
        this.x3 = coordinateX3;
        this.y3 = coordinateY3;
    }

    public GeometricalFigure(double rectangleSideA, double rectangleSideB) {
        this.lengthOfSideA = rectangleSideA;
        this.lengthOfSideB = rectangleSideB;
    }

    public GeometricalFigure(double rectangleSideA) {
        this.lengthOfSideA = rectangleSideA;
        this.lengthOfSideB = rectangleSideA;
    }

    public GeometricalFigure(double circleRadius, String r) {
        this.radius = circleRadius;
    }

    public GeometricalFigure() {
    }

    public double getLengthOfSideA() {
        return lengthOfSideA;
    }

    public double getLengthOfSideB() {
        return lengthOfSideB;
    }

    public double getRadius() {
        return radius;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    public void setLengthOfSideB(double lengthOfSideB) {
        this.lengthOfSideB = lengthOfSideB;
    }
}












