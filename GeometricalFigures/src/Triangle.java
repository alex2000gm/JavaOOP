
class Triangle extends GeometricalFigure implements Shape {
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        super(x1, y1, x2, y2, x3, y3);
    }

    // TODO: сделать верификацию треугольника
    @Override
    public double getWidth() {
        double[] x = {this.getX1(), this.getX2(), this.getX3()};
        double maxX = this.getX1();
        double minX = this.getX1();
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
        double[] y = {this.getY1(), this.getY2(), this.getY3()};
        double maxY = this.getY1();
        double minY = this.getY1();
        for (double i : y) {
            if (i > maxY) {
                maxY = i;
            } else if (i < minY) {
                minY = i;
            }
        }
        return maxY - minY;
    }

    private double aBLength = lengthOfSide(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    private double bCLength = lengthOfSide(this.getX2(), this.getY2(), this.getX3(), this.getY3());
    private double aCLength = lengthOfSide(this.getX1(), this.getY1(), this.getX3(), this.getY3());

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
