import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public final class CashPoint {

    private static int[] quantityOfNotes;
    private static final int LIMIT_OF_NOTES_PER_VALUE = 10000;


/*  quantityOfNotes:
           quantityOfNotes[0] = quantity of notes with value 10
           quantityOfNotes[1] = quantity of notes with value 50
           quantityOfNotes[2] = quantity of notes with value 100
           quantityOfNotes[3] = quantity of notes with value 500
           quantityOfNotes[4] = quantity of notes with value 1000
           quantityOfNotes[5] = quantity of notes with value 5000
            */

    static {
        quantityOfNotes = new int[6];
        for (int i = 0; i < 6; i++) {
            quantityOfNotes[i] = 0;
        }
    }

    public static void insertMoney(int[] bunchOfMoney) {

        int i = 0; //current position in bunchOfMoney array
        int j = 0; //index in quantityOfNotes array

        switch (bunchOfMoney[i]) {
            case 10:
                j = 0;
                break;
            case 50:
                j = 1;
                break;
            case 100:
                j = 2;
                break;
            case 500:
                j = 3;
                break;
            case 1000:
                j = 4;
                break;
            case 5000:
                j = 5;
                break;
        }

        int emptySpace = getLimitOfNotes() - getCurrentQuantity(j);
        while (i < bunchOfMoney.length) {
            if (emptySpace == 0) {
                ArrayList<Integer> notAcceptedValues = new ArrayList<>();
                notAcceptedValues.add(i);
                int[] moneyForReturn = new int[bunchOfMoney.length - i];
                System.arraycopy(bunchOfMoney, bunchOfMoney[i], moneyForReturn, 0, (bunchOfMoney.length - i));
                System.out.printf("К сожалению, все купюры не могут быть приняты банкоматом, можем принять только %d ");
                break;
            }
            switch (bunchOfMoney[i]) {
                case 10:
                    CashPoint.quantityOfNotes[0] = CashPoint.quantityOfNotes[0] + 1;
                    break;
                case 50:
                    CashPoint.quantityOfNotes[1] = CashPoint.quantityOfNotes[1] + 1;
                    break;
                case 100:
                    CashPoint.quantityOfNotes[2] = CashPoint.quantityOfNotes[2] + 1;
                    break;
                case 500:
                    CashPoint.quantityOfNotes[3] = CashPoint.quantityOfNotes[3] + 1;
                    break;
                case 1000:
                    CashPoint.quantityOfNotes[4] = CashPoint.quantityOfNotes[4] + 1;
                    break;
                case 5000:
                    CashPoint.quantityOfNotes[5] = CashPoint.quantityOfNotes[5] + 1;
                    break;
            }
            i++;
        }
    }

    public static int getCurrentQuantity(int i) {
        return quantityOfNotes[i];
    }

    public static int[] getQuantityOfNotes() {
        return quantityOfNotes;
    }

    public static int getLimitOfNotes() {
        return LIMIT_OF_NOTES_PER_VALUE;
    }

    public static int getMoney() {

        StringBuilder moneyInCashPoint = new StringBuilder("В банкомате есть купюры следующего достоинства:");
        if (getQuantityOfNotes()[0] > 0) {
            moneyInCashPoint.append(" 10;");
        }
        if (getQuantityOfNotes()[1] > 0) {
            moneyInCashPoint.append(" 50;");
        }
        if (getQuantityOfNotes()[2] > 0) {
            moneyInCashPoint.append(" 100;");
        }
        if (getQuantityOfNotes()[3] > 0) {
            moneyInCashPoint.append(" 500;");
        }
        if (getQuantityOfNotes()[4] > 0) {
            moneyInCashPoint.append(" 1000;");
        }
        if (getQuantityOfNotes()[5] > 0) {
            moneyInCashPoint.append(" 5000;");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Купюрами какого достоинства произвести выдачу? (перечеслить через запятую)");
        String usersWish = scanner.nextLine();

        return 1;
    }
}
