import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {

        String regex = "-?\\d+(\\.\\d+)?";
        int result;
        int a;
        int b;
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(" ");
        if (array.length != 3) {
            throw new Exception("Неверно написан пример");
        }
        if ((array[0].matches(regex) && array[2].matches(regex)) || (!array[0].matches(regex) && !array[2].matches(regex))) {
            if (!array[0].matches(regex)) {
                a = romanToArabic(array[0]);
                b = romanToArabic(array[2]);
                if(a < 0 || a > 10 || b < 0 || b > 10) {
                    throw new Exception("Ошибка! Одно из чисел вне диапазона от 0 до 10");
                }
                result = doMath(a, b, array[1]);
                if (result < 0) {
                    throw new Exception("Ошибка! В римской системе нет отрицательных чисел");
                }
                System.out.println(arabicToRoman(result));
            } else {
                a = Integer.parseInt(array[0]);
                b = Integer.parseInt(array[2]);
                if(a < 0 || a > 10 || b < 0 || b > 10) {
                    throw new Exception("Ошибка! Одна из чисел вне диапазона от 0 до 10");
                }
                result = doMath(a, b, array[1]);
                System.out.println(result);
            }
        } else {
            throw new Exception("Ошибка! Не совпадают системы счисления");
        }


    }

    public static int doMath(int n1, int n2, String operator) throws Exception {
        switch (operator) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                return n1 / n2;
            default:
                throw new Exception("Ошибка! неизвестный оператор");
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
            throw new IllegalArgumentException(input + " нельзя перевести в арабскую систему счисления");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " не в диапазоне от 0 до 100");
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