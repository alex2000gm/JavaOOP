import java.util.Arrays;

/**
 * Created by alex2000 on 24.11.16.
 */
public class CashPointMain {
    public static void main(String[] args) {


        int[] firstInsert = CashPoint.insertMoney(new int[]{100, 500, 10, 1000, 5000, 10, 500, 100, 500});
        System.out.println(Arrays.toString(firstInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));

        int[] secondInsert = CashPoint.insertMoney(new int[]{100, 500, 10, 1000, 5000, 10, 500, 100, 500});
        System.out.println(Arrays.toString(secondInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));

        int[] thirdInsert = CashPoint.insertMoney(new int[]{100, 500, 10, 1000, 5000, 10, 500, 100, 500, 10000});
        System.out.println(Arrays.toString(thirdInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));

        int[] toManyNotes = new int[50];
        for (int i = 0; i < 46; i++) {
            toManyNotes[i] = 10;
        }
        for (int i = 46; i < toManyNotes.length; i++) {
            toManyNotes[i] = 10000;
        }
        int[] fourthInsert = CashPoint.insertMoney(toManyNotes);
        System.out.println(Arrays.toString(fourthInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));
    }
}
