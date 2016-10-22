
class Circle extends GeometricalFigure {
    public Circle(double radius, String r) {
        super(radius, r);
    }

    @Override
    public double getWidth() {
        return this.getRadius() * 2;
    }

    @Override
    public double getHeight() {
        return this.getRadius() * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(this.getRadius(), 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.getRadius();
    }

    @Override
    public String toString() {
        String stringCircle = "";
        return stringCircle.concat("Высота круга = " + getHeight() + " Ширина = " + getWidth() + " Периметр = " + getPerimeter() + " Площадь = " + getArea());
    }
}
