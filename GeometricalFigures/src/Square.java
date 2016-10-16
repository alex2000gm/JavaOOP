class Square extends Rectangle implements Shape {
    public Square(double lengthOfSideA) {
        super(lengthOfSideA);
    }

    @Override
    public String toString() {
        String stringSquare = "";
        return stringSquare.concat("Ширина квадрата = " + getWidth() + "  Высота = " + getHeight() + " Периметр = " + getPerimeter() + " Площадь = " + getArea());
    }
}