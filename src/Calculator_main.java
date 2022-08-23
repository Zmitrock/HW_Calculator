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
//                    calc("9-8*(-6+8)-7+(3/3)");
                    System.out.println(calc("1.0+2,0-3+4-5+6-7+8"));
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

    public static double calc(String str) throws IOException {
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
                    } else if (chars[j]==46 || chars[j]==44){
                        number = number + ".";
                        count++;
                    }
                    else{
                        break;
                    }
                }
                listForCalculation.add(number);
                i = i + count;
            } else listForCalculation.add(chars[i] + "");
        }
        System.out.println(listForCalculation);
        double result;
//        LinkedList<Integer> index = new LinkedList<>();
        //перезагрузился и начал с новыми силами
        int index = 0;
        double firstToken = Double.parseDouble(listForCalculation.get(index++));

        while (index < listForCalculation.size()) {
            String operator = listForCalculation.get(index);
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                index++;
            }
            double secondToken = Double.parseDouble(listForCalculation.get(index++));
            if (operator.equals("+")) {
                firstToken += secondToken;
            } else {
                firstToken -= secondToken;
            }


        }
        result=firstToken;

return result;

//        listForCalculation.stream().filter(x -> x == "(").peek(x -> index.add(listForCalculation.indexOf(x)));
//        System.out.println(index);
//        if (listForCalculation.contains("(")){
//
//        }

//        for (int i = 0; i < listForCalculation.size(); i++) {
//            switch (listForCalculation.get(i)) {
//                case "(":
//
//                case ")":
//
//                case "*":
//                    double temp = Double.parseDouble(listForCalculation.get(i)) * Double.parseDouble(listForCalculation.get(i) + 1);
//
//                case "/":
//
//                case "+":
//
//                case "-":
//
//
//            }
//        }

    }

}

