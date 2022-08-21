import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator_main {
    public static BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        boolean flag = true;
        while (flag) {
            switch (showMenu()) {
                case 1:
//                    calc(inputString());
                    calc("9-8*(-6+8)-7+(3/3)");
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Некорректный ввод. Выберите действие");
            }
        }

    }

    private static int showMenu() throws IOException {
//        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        System.out.println("Хотите что-то посчитать? \n1 - да" +
                "\n0 - нет (выйти из программы)");
        return Integer.parseInt(reader.readLine());
    }

    private static String inputString() throws IOException {
        System.out.println("Введите выражение для расчета, используя цифры от 0 до 9 и знаки арифметических операций");
        String str = reader.readLine();
        return str;
    }

    public static void calc(String str) throws IOException {
        String s = str.replaceAll("\\s", "");//удаляю все пробельные символы
/*
здесь должна быть проверка на дурака - можно вводить только цифры и знаки арифметических операций
 */
        System.out.println("выражение " + s);
        LinkedList<String> listForCalculation = new LinkedList<String>();
        String number = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int count = 0;
            if (Character.isDigit(chars[i])) {
                number = chars[i] + "";
                for (int j = i + 1; j < chars.length; j++) {
                    if (Character.isDigit(chars[j])) {
                        number = number + chars[j];
                        count++;
                    } else {
                        break;
                    }
                }
                listForCalculation.add(number);
                i = i + count;
            } else listForCalculation.add(chars[i] + "");
        }
        System.out.println(listForCalculation);
    }


}
