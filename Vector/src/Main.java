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
        double multiplication = Vector.getMultiplied(firstVector, vectorFromArray2);
        System.out.println(multiplication);
        Vector vectorDifference = Vector.differenceOfVectors(vectorFromArray2, firstVector);
        System.out.println(vectorDifference.toString());
        Vector vectorSum = Vector.sumOfVectors(firstVector, vectorFromArray2);
        System.out.println(vectorSum.toString());
        System.out.println(firstVector.getLength());

        Vector check = new Vector(30, array);
        System.out.println(check.toString());

        Vector vectorForEquals = new Vector(30, 56, 22, 34, 56);
        Vector vectorForEquals2 = new Vector(30, 56, 22, 34, 56);
        Vector vectorForEquals3 = new Vector(11, 66, 11, 34, 91);
        if (vectorForEquals.equals(vectorForEquals2)) {
            System.out.println(vectorForEquals.toString() + " equals " + vectorForEquals2.toString());
        }
        if (!vectorForEquals.equals(vectorForEquals3)) {
            System.out.println(vectorForEquals.toString() + " not equals " + vectorForEquals3.toString());
        }
        if (!vectorForEquals2.equals(vectorForEquals3)) {
            System.out.println(vectorForEquals2.toString() + " not equals " + vectorForEquals2.toString());
        }
    }
}
