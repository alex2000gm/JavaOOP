/**
 * Created by alex2000 on 22.10.16.
 */
public class Main {
    public static void main(String[] args) {
        double[] array = new double[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i * 3;
        }
        Vector firstVector = new Vector(array);

        Vector copy = new Vector(firstVector);

        System.out.println(firstVector.toString());
        System.out.println(copy.toString());

    }
}
