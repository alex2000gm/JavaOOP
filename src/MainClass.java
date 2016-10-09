/**
 * Created by alex2000 on 08.10.16.
 */
public class MainClass {
    public static void main(String[] args) {
        GeometricalFigure rectangle = new Rectangle(5, 3);
        System.out.println(rectangle.toString());

        GeometricalFigure circle = new Circle(7);
        System.out.println(circle.toString());

        GeometricalFigure triangle = new Triangle(13,144,123,222,21,44);
        System.out.println(triangle.toString());

        GeometricalFigure square = new Square(50);
        System.out.println(square.toString());
    }
}
