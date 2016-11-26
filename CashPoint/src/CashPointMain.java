import java.util.Arrays;

/**
 * Created by alex2000 on 24.11.16.
 */
public class CashPointMain {
    public static void main(String[] args) {

/*
        int[] firstInsert = CashPoint.insertMoney(new int[]{100, 500, 10, 1000, 5000, 10, 500, 100, 500});
        System.out.println(Arrays.toString(firstInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));

        int[] secondInsert = CashPoint.insertMoney(new int[]{100, 500, 10, 1000, 5000, 10, 500, 100, 500});
        System.out.println(Arrays.toString(secondInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));

        int[] thirdInsert = CashPoint.insertMoney(new int[]{100, 500, 10, 1000, 5000, 5000, 5000, 1000, 10, 500, 100, 500, 10000});
        System.out.println(Arrays.toString(thirdInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));

        int[] toManyNotes = new int[50];
        for (int i = 0; i < 46; i++) {
            toManyNotes[i] = 10;
        }
        for (int i = 46; i < toManyNotes.length; i++) {
            toManyNotes[i] = 10000;
        }
*/
        int[] fillCashPointToMax = new int[180];
        int noteValue = 10;
        for (int i = 0; i < fillCashPointToMax.length; i++) {
            if (i < 30) {
                noteValue = 10;
            } else if (i >= 30 && i < 60) {
                noteValue = 50;
            } else if (i >= 60 && i < 90) {
                noteValue = 100;
            } else if (i >= 90 && i < 120) {
                noteValue = 500;
            } else if (i >= 120 && i < 150) {
                noteValue = 1000;
            } else if (i >= 150 && i < 180) {
                noteValue = 5000;
            }
            fillCashPointToMax[i] = noteValue;
        }

        int[] fullInsert = CashPoint.insertMoney(fillCashPointToMax);

    /*    int[] fourthInsert = CashPoint.insertMoney(toManyNotes);
        System.out.println(Arrays.toString(fourthInsert));
        System.out.println(Arrays.toString(CashPoint.getQuantityOfNotes()));
*/

        int[] deployZero = CashPoint.getMoney(30000);
        int[] deployMoney1 = CashPoint.getMoney(9000);
        System.out.println(Arrays.toString(deployMoney1));
    }
}
