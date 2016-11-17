class Rectangle extends GeometricalFigure implements Shape {
    public Rectangle(double lengthOfSideA, double lengthOfSideB) {
        super(lengthOfSideA, lengthOfSideB);
    }

    public Rectangle(double lengthOfSideA) {
        super(lengthOfSideA);
        this.setLengthOfSideB(lengthOfSideA); // TODO: подумать над размещением этой строки и над строкой вообще
    }

    public Rectangle() {
    }

    @Override
    public double getWidth() {
        return Math.max(this.getLengthOfSideA(), this.getLengthOfSideB());
    }

    @Override
    public double getHeight() {
        return Math.min(this.getLengthOfSideA(), this.getLengthOfSideB());
    }

    @Override
    public double getArea() {
        return this.getLengthOfSideA() * this.getLengthOfSideB();
    }

    @Override
    public double getPerimeter() {
        return (this.getLengthOfSideA() + this.getLengthOfSideB()) * 2;
    }

    @Override
    public String toString() {
        StringBuilder rectangle = new StringBuilder("");
        rectangle.append("Высота прямоугольника = ");
        rectangle.append(getHeight());
        rectangle.append(" Ширина = ");
        rectangle.append(getWidth());
        rectangle.append(" Периметр = ");
        rectangle.append(getPerimeter());
        rectangle.append(" Площадь = ");
        rectangle.append(getArea());
        return rectangle.toString();
    }
}