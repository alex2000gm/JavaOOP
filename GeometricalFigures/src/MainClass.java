import java.util.ArrayList;

/**
 * Created by alex2000 on 08.10.16.
 */
public class MainClass {
    public static void main(String[] args) {
        GeometricalFigure rectangle1 = new Rectangle(530, 103);
        System.out.println(rectangle1.getArea());
        System.out.println(rectangle1.getPerimeter());
        System.out.println();

        GeometricalFigure circle1 = new Circle(7, "r");
        System.out.println(circle1.getArea());
        System.out.println(circle1.getPerimeter());
        System.out.println();

        GeometricalFigure triangle1 = new Triangle(13, 144, 123, 222, 21, 44);
        System.out.println(triangle1.getArea());
        System.out.println(triangle1.getPerimeter());
        System.out.println();

        GeometricalFigure square1 = new Square(50);
        System.out.println(square1.getArea());
        System.out.println(square1.getPerimeter());
        System.out.println();

        GeometricalFigure rectangle2 = new Rectangle(78, 65);
        System.out.println(rectangle2.getArea());
        System.out.println(rectangle2.getPerimeter());
        System.out.println();

        GeometricalFigure circle2 = new Circle(73, "r");
        System.out.println(circle2.getArea());
        System.out.println(circle2.getPerimeter());
        System.out.println();

        GeometricalFigure triangle2 = new Triangle(-9, 8, -13, 5, 7, 18);
        System.out.println(triangle2.getArea());
        System.out.println(triangle2.getPerimeter());
        System.out.println();

        GeometricalFigure[] array = {rectangle1, circle1, triangle1, square1, rectangle2, circle2, triangle2};
        findMaxAreaFigure(array);
        System.out.println();
        findPerimeterByOrder(array, 8);
    }

    private static void findMaxAreaFigure(GeometricalFigure[] figuresArray) {
        double maxArea = 0;
        GeometricalFigure maxAreaFigure = figuresArray[0];
        for (GeometricalFigure i : figuresArray) {
            if (i.getArea() > maxArea) {
                maxArea = i.getArea();
                maxAreaFigure = i;
            }
        }
        System.out.printf("%nМаксимальная площадь среди данных фигур = %f%n", maxArea);
        System.out.println(maxAreaFigure.toString());
    }

    private static GeometricalFigure findMaxPerimeter(GeometricalFigure[] figuresArray) {
        double maxPerimeter = 0;
        GeometricalFigure maxPerimeterFigure = figuresArray[0];
        for (GeometricalFigure i : figuresArray) {
            if (i.getPerimeter() > maxPerimeter) {
                maxPerimeter = i.getPerimeter();
                maxPerimeterFigure = i;
            }
        }
        return maxPerimeterFigure;
    }

    private static GeometricalFigure[] removeMaxPerimeter(GeometricalFigure[] figuresArray) {
        int maxPerimeterMember = 0;
        double maxPerimeter = 0;
        for (int i = 0; i < figuresArray.length; i++) {
            if (figuresArray[i].getPerimeter() > maxPerimeter) {
                maxPerimeter = figuresArray[i].getPerimeter();
                maxPerimeterMember = i;
            }
        }
        GeometricalFigure[] maxMemberExcluded = new GeometricalFigure[figuresArray.length - 1];
        for (int i = 0; i < figuresArray.length - 1; i++) {
            if (i < maxPerimeterMember) {
                maxMemberExcluded[i] = figuresArray[i];
            } else maxMemberExcluded[i] = figuresArray[i + 1];
        }
        return maxMemberExcluded;
    }

    private static void findPerimeterByOrder(GeometricalFigure[] figuresArray, int sequenceNumber) {
        int counter = 0;
        GeometricalFigure[] arrayForSearch = figuresArray;
        if (figuresArray.length >= sequenceNumber) {
            while (counter < sequenceNumber - 1) {
                arrayForSearch = removeMaxPerimeter(arrayForSearch);
                counter++;
            }
            System.out.println(findMaxPerimeter(arrayForSearch));
        } else {
            System.out.printf("Количество фигур меньше, чем порядковый номер искомого периметра;%nПоиск периметра по убыванию не возможен.");
        }
    }
}
// функция получила массив и цифру (порядковый номер), функция передала массив функции поиска максимума и получила в ответ порядковый номер
// максимального члена, далее переделала массив без этого числа и выслала его опять в функцию поиска максимального члена