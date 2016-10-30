/**
 * Created by alex2000 on 22.10.16.
 */
public class Main {
    public static void main(String[] args) {
        double[] array = new double[10];
        for (int i = 0; i < 10; i++) {
            array[i] = (i + 1) * 3;
        }
        Vector firstVector = new Vector(array);

        Vector copy = new Vector(firstVector);

        System.out.println(firstVector.toString());
        System.out.println(copy.toString());

        double[] array2 = new double[30];
        for (int i = 0; i < 30; i++) {
            array2[i] = (i + 1) * 3;
        }
        Vector vectorFromArray2 = new Vector(array2);
        System.out.println(vectorFromArray2.toString());
        Vector sum = firstVector.sumOfVectors(vectorFromArray2);
        System.out.println(sum.toString());
        Vector diff = firstVector.differenceOfVectors(vectorFromArray2);
        Vector diff2 = vectorFromArray2.differenceOfVectors(firstVector);
        System.out.println(diff.toString());
        System.out.println(diff2.toString());

        diff2.setNumByIndex(4, 55);
        System.out.println(diff2.toString());
        System.out.println(diff2.getNumByIndex(4));
        System.out.println();
        double multiplication = Vector.scalarMultiplication(firstVector, vectorFromArray2);
        System.out.println(multiplication);
        Vector vectorDifference = Vector.differenceOfVectors(vectorFromArray2, firstVector);
        System.out.println(vectorDifference.toString());
        Vector vectorSum = Vector.sumOfVectors(firstVector, vectorFromArray2);
        System.out.println(vectorSum.toString());
        System.out.println(firstVector.getLength());
    }
}
