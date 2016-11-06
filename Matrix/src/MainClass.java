/**
 * Created by alex2000 on 02.11.16.
 */
public class MainClass {
    public static void main(String[] args) {
     /*   double[] array = new double[10];
        for (int i = 0; i < 10; i++) {
            array[i] = (i + 1) * 3;
        }
        Vector firstVector = new Vector(array);

        Vector copy = new Vector(firstVector);

        System.out.println(firstVector.toString());
        //   System.out.println(copy.toString());

        double[] array2 = new double[30];
        for (int i = 0; i < 30; i++) {
            array2[i] = (i + 1) * 3;
        }
        Vector vectorFromArray2 = new Vector(array2);
        //   System.out.println(vectorFromArray2.toString());

        Vector[] vectorMatrix = new Vector[3];
        vectorMatrix[0] = firstVector;
        vectorMatrix[1] = copy;
        vectorMatrix[2] = vectorFromArray2;
        Matrix matrix0 = new Matrix(5, 4);
        //  System.out.println(matrix0.toString());
        Matrix matrix = new Matrix(vectorMatrix);
        System.out.println(matrix.toString());
        Matrix multipliedMatrix = matrix.getMultiplied(5);
        //    System.out.println(multipliedMatrix.toString());
        Matrix multipliedOnVector = matrix.getMultiplied(firstVector);
        System.out.println(multipliedOnVector.toString());

        Matrix sumOfMatrixes = matrix.getSumOfMatrixes(matrix);
        System.out.println(sumOfMatrixes.toString());

        Matrix diffOfMatrixes = matrix.getDifferenceOfMatrixes(matrix);
        System.out.println(diffOfMatrixes.toString());

        Vector column = matrix.getColumnVector(0);
        System.out.println(column.toString());   */

        Vector row1 = new Vector( 1, 31, 241, 78, 55, 34);
        Vector row2 = new Vector( 56, 33, 22, 54, 23, 43);
        Vector row3 = new Vector( 24, 53, 87, 32, 99, 47);
        Matrix matrix1 = new Matrix(row1, row2, row3);
        System.out.println(matrix1.toString());

        Vector row21 = new Vector( 13, 43, 23);
        Vector row22 = new Vector( 19, 4, 5);
        Vector row23 = new Vector( 13, 63, 22);
        Vector row24 = new Vector( 19, 2, 7);
        Vector row25 = new Vector( 11, 33, 33);
        Vector row26 = new Vector( 1, 99, 433);
        Matrix matrix2 = new Matrix(row21, row22, row23, row24, row25, row26);
        System.out.println(matrix2.toString());

        Matrix matrix3 = Matrix.getMultiplied(matrix1, matrix2);
        System.out.println(matrix3.toString());
    }
}
