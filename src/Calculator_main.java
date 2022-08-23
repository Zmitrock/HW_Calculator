import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Calculator_main {
    public static BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
    public static LinkedList<String> listForCalculation = new LinkedList<String>();
    public static int index = 0;
    public static String s;

    public static void main(String[] args) throws IOException {
        boolean flag = true;
        while (flag) {
            switch (showMenu()) {
                case 1:
                    stringTransform(inputString());
                    System.out.println(s+" = "+calc());
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

    public static LinkedList<String> stringTransform(String str) {
         s = str.replaceAll("\\s", "");//удаляю все пробельные символы
        int temp =0;
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)<40 ||s.charAt(i)>57 ){
                char c = s.charAt(i);
                temp++;
               s= s.replace(c+"","");
            }
        }
        if(temp>0){
            System.err.println("Введенная строка, содержала некорректные символы. Для расчета они были удалены");
        }
//regex - боль
//        if (s.matches("[^\\d\\.\\,\\*/\\+\\-\\(\\)]")) {
//            System.out.println("есть некорректные символы в строке");
//
//        }

/*
здесь должна быть проверка на некорректные символы  - можно вводить только цифры и знаки арифметических операций
 */
        System.out.println("выражение " + s);

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
                    } else if (chars[j] == 46 || chars[j] == 44) {
                        number = number + ".";
                        count++;
                    } else {
                        break;
                    }
                }
                listForCalculation.add(number);
                i = i + count;
            } else listForCalculation.add(chars[i] + "");
        }
        System.out.println("Список для расчета: " + listForCalculation);
        return listForCalculation;
    }

    public static double calc() throws IOException {
        double firstToken = multiply();
        while (index < listForCalculation.size()) {
            String operator = listForCalculation.get(index);
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                index++;
            }
            double secondToken = multiply();
            if (operator.equals("+")) {
                firstToken += secondToken;
            } else {
                firstToken -= secondToken;
            }
        }
        return firstToken;
    }

    public static double multiply() throws IOException {
        double firstToken = factor();

        while (index < listForCalculation.size()) {
            String operator = listForCalculation.get(index);
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                index++;
            }
            double secondToken = factor();
            if (operator.equals("*")) {
                firstToken *= secondToken;
            } else {
                firstToken /= secondToken;
            }
        }
        return firstToken;
    }

    public static double factor() throws IOException {
        String next = listForCalculation.get(index);
        double result;
        if (next.equals("(")) {
            index++;
            result = calc();
            String closingScope;
            if (index < listForCalculation.size()) {
                closingScope = listForCalculation.get(index);
            } else {
                throw new IllegalArgumentException("Нет закрывающей скобки");
            }
            if (closingScope.equals(")")) {
                index++;
                return result;
            }
            throw new IllegalArgumentException("')' ожидалась, но найдено " + closingScope);
        }
        index++;
        return Double.parseDouble(next);
    }

}

