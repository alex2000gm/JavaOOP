import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class CashPoint {
    private final static ArrayList<Integer> VALUES_LEGEND;
    private static int[] quantityOfNotes;
    private static final int LIMIT_OF_NOTES_PER_VALUE = 30;

    static {
        VALUES_LEGEND = new ArrayList<>();
        VALUES_LEGEND.add(10);
        VALUES_LEGEND.add(50);
        VALUES_LEGEND.add(100);
        VALUES_LEGEND.add(500);
        VALUES_LEGEND.add(1000);
        VALUES_LEGEND.add(5000);
    }

    static {
        quantityOfNotes = new int[VALUES_LEGEND.size()];
        for (int i = 0; i < quantityOfNotes.length; i++) {
            quantityOfNotes[i] = 0;
        }
    }

    public static int[] insertMoney(int[] bunchOfMoney) {
        ArrayList<Integer> unknownNotes = new ArrayList<>();
        int[] notAcceptedNotes = new int[quantityOfNotes.length];
        int[] acceptedNotes = new int[quantityOfNotes.length];

        for (int i = 0; i < quantityOfNotes.length; i++) {
            notAcceptedNotes[i] = 0;
            acceptedNotes[i] = 0;
        }

        boolean notAccepted = false;
        for (int aBunchOfMoney : bunchOfMoney) {
            if (VALUES_LEGEND.contains(aBunchOfMoney)) {
                if (havePlaceForInsert(VALUES_LEGEND.indexOf(aBunchOfMoney), acceptedNotes)) {
                    acceptedNotes[VALUES_LEGEND.indexOf(aBunchOfMoney)]++;
                } else {
                    notAccepted = true;
                    notAcceptedNotes[VALUES_LEGEND.indexOf(aBunchOfMoney)]++;
                }
            } else {
                notAccepted = true;
                unknownNotes.add(aBunchOfMoney);
            }
        }

        if (notAccepted) {
            System.out.println();
            System.out.println("К сожалению, не все банкноты могут быть приняты банкоматом, следующие купюры будут возвращены:");
            for (int i = 0; i < notAcceptedNotes.length; i++) {
                if (notAcceptedNotes[i] > 0) {
                    System.out.printf("%n%d * %d RUR%n", notAcceptedNotes[i], VALUES_LEGEND.get(i));
                }
            }
            if (unknownNotes.size() > 0) {
                System.out.printf("не распознанных купюр - %d%n", unknownNotes.size());
            }
            String choice1 = "1";
            String choice2 = "2";
            String usersString = "";
            Scanner scanner = new Scanner(System.in);

            while (!choice1.equals(usersString) || !choice2.equals(usersString)) {
                System.out.println();
                System.out.println("Выберите один из вариантов и нажмите enter:");
                System.out.println();
                System.out.println("Принять купюры за исключением не принятых - 1");
                System.out.println("Вернуть все внесенные купюры - 2");
                System.out.println();
                usersString = scanner.nextLine();
                if (choice1.equals(usersString) || choice2.equals(usersString)) {
                    break;
                }
            }
            if (choice1.equals(usersString)) {

                for (int i = 0; i < quantityOfNotes.length; i++) {
                    quantityOfNotes[i] = quantityOfNotes[i] + acceptedNotes[i];
                }
                int quantityToReturn = unknownNotes.size();
                for (int notAcceptedNote : notAcceptedNotes) {
                    quantityToReturn = quantityToReturn + notAcceptedNote;
                }

                int[] moneyToReturn = new int[quantityToReturn];
                int moneyToReturnIndex = 0;
                if (notAcceptedNotes.length > 0) {
                    for (int i = 0; i < notAcceptedNotes.length; i++) {
                        for (int j = 0; j < notAcceptedNotes[i]; j++, moneyToReturnIndex++) {
                            moneyToReturn[moneyToReturnIndex] = VALUES_LEGEND.get(i);
                        }
                    }
                }
                if (unknownNotes.size() > 0) {
                    for (Integer unknownNote : unknownNotes) {
                        moneyToReturn[moneyToReturnIndex] = unknownNote;
                        moneyToReturnIndex++;
                    }
                }
                return moneyToReturn;
            } else {
                return bunchOfMoney;
            }
        } else {
            for (int i = 0; i < quantityOfNotes.length; i++) {
                quantityOfNotes[i] = quantityOfNotes[i] + acceptedNotes[i];
            }
        }
        return new int[0];
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

    public static int calcAllAvailableMoney() {
        int sumInCashPoint = 0;
        for (int i = 0; i < quantityOfNotes.length; i++) {
            sumInCashPoint = sumInCashPoint + getCurrentQuantity(i) * VALUES_LEGEND.get(i);
        }
        return sumInCashPoint;
    }

    private static boolean havePlaceForInsert(int index, int[] acceptedNotes) {
        return getLimitOfNotes() - acceptedNotes[index] - getCurrentQuantity(index) > 0;
    }

    public static int[] getMoney(int sum) {
        if (sum > calcAllAvailableMoney()) {
            return new int[0];
        }
        System.out.println("В банкомате есть купюры следующего достоинства:");
        for (int i = 0; i < quantityOfNotes.length; i++) {
            if (quantityOfNotes[i] > 0) {
                System.out.printf("%n%d RUR", VALUES_LEGEND.get(i));
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Купюрами какого достоинства произвести выдачу? (перечеслить через запятую и нажать enter)");
        String usersWish = scanner.nextLine();
        System.out.println();
        System.out.println();
        String[] usersWishArray = usersWish.split(",");
        ArrayList<Integer> usersChoice = new ArrayList<>();
        for (int i = 0; i < usersWishArray.length; i++) {
            int addToList = Integer.parseInt(usersWishArray[i]);
            usersChoice.add(addToList);
        }
        for (int i = 0; i < usersChoice.size(); i++) {
            if (!VALUES_LEGEND.contains(usersChoice.get(i))) {
                usersChoice.remove(i);
            } else if (getCurrentQuantity(VALUES_LEGEND.indexOf(usersChoice.get(i))) == 0) {
                usersChoice.remove(i);
            } else if (sum < usersChoice.get(i)) {
                usersChoice.remove(i);
            }
        }
        Collections.sort(usersChoice);
        ArrayList<Integer> reversedSort = new ArrayList<>();
        for (int i = usersChoice.size() - 1; i >= 0; i--) {
            reversedSort.add(usersChoice.get(i));
        }
        usersChoice.clear();
        usersChoice.addAll(reversedSort);
        ArrayList<Integer> calcOfSequence = new ArrayList<>();
/* 1. если в банкомате нет 10 или их число меньше 10, то нужно делать выдачу кратно 50
   2. если и 50 нет, то кратно 100 и т. д.
   3. разменом выдаем только последнюю 1000
   4. начинаем с самых больших купюр, в зависимости от суммы выдачи, выдаем их по максимуму
9000
1 - 5000 / 4 - 1000:
1 - 5000 / 3 - 1000 / 2 - 500:
1 - 5000 / 3 - 1000 / 1 - 500 / 5 - 100:
1 - 5000 / 3 - 1000 / 1 - 500 / 4 - 100 / 2 - 50:
1 - 5000 / 3 - 1000 / 1 - 500 / 4 - 100 / 1 - 50 / 4 - 10:
*/

// TODO: 26.11.16 переделать сортировку на сортировку по убыванию
        int sumForCalc = sum;
        for (int i = 0; i < usersChoice.size(); i++) {
            boolean dontMoveCounter = false;
            int counterOfInserts = sumForCalc / usersChoice.get(i);
            if (counterOfInserts > getCurrentQuantity(VALUES_LEGEND.indexOf(usersChoice.get(i)))) {
                counterOfInserts = getCurrentQuantity(VALUES_LEGEND.indexOf(usersChoice.get(i)));
                dontMoveCounter = true;
            }
            if (sumForCalc % usersChoice.get(i) == 0 && i < usersChoice.size() - 1 && !dontMoveCounter) {
                counterOfInserts--;
            }
            sumForCalc = sumForCalc - counterOfInserts * usersChoice.get(i);
            while (counterOfInserts > 0) {
                calcOfSequence.add(usersChoice.get(i));
                quantityOfNotes[VALUES_LEGEND.indexOf(usersChoice.get(i))]--;
                counterOfInserts--;
            }
        }

        int[] moneyForUser = new int[calcOfSequence.size()];
        for (int i = 0; i < calcOfSequence.size(); i++) {
            moneyForUser[i] = calcOfSequence.get(i);
        }
        return moneyForUser;
    }
}
