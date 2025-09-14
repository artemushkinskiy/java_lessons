package firstHomeWork;

import java.util.Scanner;
/*
Программа предназначена для арифметических вычислений внутри терминала.
Поддерживаемые операции:
    - сложение (+);
    - вычитание (-);
    - деление (/);
    - умножение (*);
    - целочисленное деление (//);
    - возведение в степень (^);
    - остаток от деления (%).
 */
public class calculator {
    public static void main(String[] args) {
        String expression;
        String stopWord = "exit";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Поддерживаемые операции - сложение (+), вычитание (-), деление (/), умножение (*), целочисленное деление (//), возведение в степень (^), остаток от деления (%).");

        // Непрерывная работа с калькулятором
        while (true) {
            System.out.println("Введите ваше выражение:");

            expression = scanner.nextLine();

            // Валидация стоп слова
            if (expression.equals(stopWord)) {
                System.out.println("Выход...");
                break;
            }

            // Валидация введенного выражения
            if (validExpression(expression)) {
                // Разделяем введенное выражение через пробелы
                String[] words = expression.split(" ");

                // Объявление переменных
                double num1 = Double.parseDouble(words[0]);
                double num2 = Double.parseDouble(words[2]);

                // Проверка деления на ноль перед вычислениями
                if ((words[1].equals("/") || words[1].equals("//") || words[1].equals("%")) && num2 == 0) {
                    System.out.println("Ошибка - деление на 0.");
                    continue;
                }

                // Поддерживаемые выражения
                switch (words[1]) {
                    case "+" -> System.out.println("Результат: " + sum(num1, num2));
                    case "-" -> System.out.println("Результат: " + subtract(num1, num2));
                    case "*" -> System.out.println("Результат: " + multiply(num1, num2));
                    case "/" -> System.out.println("Результат: " + divide(num1, num2));
                    case "//" -> System.out.println("Результат: " + intDivide(num1, num2));
                    case "^" -> System.out.println("Результат: " + power(num1, num2));
                    case "%" -> System.out.println("Результат: " + modulus(num1, num2));
                }
            } else {
                System.out.println("Неверное выражение. Введите еще раз:");
            }
        }
    }

    // Метод для валидации выражения по регулярке
    // Примеры поддерживаемых выражений:
    // Примеры подходящих выражений:
    //  5 + 3, -10.5 * 2.3, .7 / .2
    //  8 // 3, 2 ^ 4, 9 % 2
    //  -15 + -5
    public static boolean validExpression(String expression) {
        return expression.matches("-?(\\d+\\.?\\d*|\\.\\d+)\\s([+\\-*/%^]|//)\\s-?(\\d+\\.?\\d*|\\.\\d+)");
    }

    public static double sum(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static double divide(double num1, double num2) {
        return num1 / num2;
    }

    public static int intDivide(double num1, double num2) {
        return (int)(num1 / num2);
    }

    public static double power(double num1, double num2) {
        return Math.pow(num1, num2);
    }

    public static double modulus(double num1, double num2) {
        return num1 % num2;
    }
}