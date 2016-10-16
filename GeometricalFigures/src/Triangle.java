
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
