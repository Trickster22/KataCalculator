import java.util.*;

public class Main {



    public static void main(String[] args) {

        String regex = "-?\\d+(\\.\\d+)?";
        int result;
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(" ");
        if (array.length > 3) {
            System.out.println("Ошибка! Некорректно введен пример");
            return;
        }
        if ((array[0].matches(regex) && array[2].matches(regex)) || (!array[0].matches(regex) && !array[2].matches(regex))) {
            if (!array[0].matches(regex)) {
               result =  doMath(romanToArabic(array[0]), romanToArabic(array[2]), array[1]);
               if (result == -101) return;
               if (result < 0) {
                   System.out.println("Ошибка! В римской системе нет отрицательных чисел");
                   return;
               }
                System.out.println(arabicToRoman(result));
            } else {
                result = doMath(Integer.parseInt(array[0]), Integer.parseInt(array[2]), array[1]);
                if (result == -101) return;
                System.out.println(result);
            }
        } else {
            System.out.println("Ошибка! Не совпадают системы чисел");
            return;
        }


    }

    public static int doMath(int n1, int n2, String operator) {
       switch (operator){
           case "+":
               return n1 + n2;
           case "-":
               return n1 - n2;
           case "*":
               return n1 * n2;
           case "/":
               return n1 / n2;
           default:
               System.out.println("Ошибка! неизвестный оператор");
               return -101;
        }
    }

    public static int romanToArabic(String input) {
        String romanNumber = input.toUpperCase();
        int result = 0;

        List<RomanNumbers> romanNumbers = RomanNumbers.getReverseSortedValues();

        int i = 0;

        while ((romanNumber.length() > 0) && (i < romanNumbers.size())) {
            RomanNumbers symbol = romanNumbers.get(i);
            if (romanNumber.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumber = romanNumber.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumber.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumbers> romanNumbers = RomanNumbers.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumbers.size())) {
            RomanNumbers currentSymbol = romanNumbers.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

}