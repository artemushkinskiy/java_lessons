package secondHomeWork;

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

// Абстрактный класс для операций
abstract class Operation {
    protected double operand1;
    protected double operand2;

    public Operation(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public abstract double calculate();
    public abstract String getOperator();
}

// Конкретные классы операций
class Addition extends Operation {
    public Addition(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return operand1 + operand2;
    }

    @Override
    public String getOperator() {
        return "+";
    }
}

class Subtraction extends Operation {
    public Subtraction(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return operand1 - operand2;
    }

    @Override
    public String getOperator() {
        return "-";
    }
}

class Multiplication extends Operation {
    public Multiplication(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return operand1 * operand2;
    }

    @Override
    public String getOperator() {
        return "*";
    }
}

class Division extends Operation {
    public Division(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        if (operand2 == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return operand1 / operand2;
    }

    @Override
    public String getOperator() {
        return "/";
    }
}

class IntegerDivision extends Operation {
    public IntegerDivision(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        if (operand2 == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return (int)(operand1 / operand2);
    }

    @Override
    public String getOperator() {
        return "//";
    }
}

class Power extends Operation {
    public Power(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return Math.pow(operand1, operand2);
    }

    @Override
    public String getOperator() {
        return "^";
    }
}

class Modulus extends Operation {
    public Modulus(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        if (operand2 == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return operand1 % operand2;
    }

    @Override
    public String getOperator() {
        return "%";
    }
}

// Класс для валидации выражений
class ExpressionValidator {
    // Метод для валидации выражения по регулярке
    // Примеры поддерживаемых выражений:
    //  5 + 3, -10.5 * 2.3, .7 / .2
    //  8 // 3, 2 ^ 4, 9 % 2
    //  -15 + -5
    public boolean validateExpression(String expression) {
        return expression.matches("-?(\\d+\\.?\\d*|\\.\\d+)\\s([+\\-*/%^]|//)\\s-?(\\d+\\.?\\d*|\\.\\d+)");
    }

    public boolean checkStopWord(String expression, String stopWord) {
        return expression.equalsIgnoreCase(stopWord);
    }
}

// Класс для парсинга выражений
class ExpressionParser {
    public Operation parseExpression(String expression) {
        String[] parts = expression.split(" ");
        double operand1 = Double.parseDouble(parts[0]);
        double operand2 = Double.parseDouble(parts[2]);
        String operator = parts[1];

        switch (operator) {
            case "+":
                return new Addition(operand1, operand2);
            case "-":
                return new Subtraction(operand1, operand2);
            case "*":
                return new Multiplication(operand1, operand2);
            case "/":
                return new Division(operand1, operand2);
            case "//":
                return new IntegerDivision(operand1, operand2);
            case "^":
                return new Power(operand1, operand2);
            case "%":
                return new Modulus(operand1, operand2);
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + operator);
        }
    }
}

// Основной класс калькулятора
class Calculator {
    private Scanner scanner = new Scanner(System.in);
    private ExpressionValidator validator;
    private ExpressionParser parser;

    private static final String DIVISION_BY_ZERO_MSG = "Ошибка - деление на 0.";
    public static final String RESULT_MSG = "Результат: ";

    public Calculator() {
        this.validator = new ExpressionValidator();
        this.parser = new ExpressionParser();
    }

    public String enterExpression() {
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayResult(double result) {
        displayMessage(RESULT_MSG + result);
    }

    public void displayError(String error) {
        displayMessage(error);
    }

    public boolean validateExpression(String expression) {
        return validator.validateExpression(expression);
    }

    public boolean checkStopWord(String expression, String stopWord) {
        if (validator.checkStopWord(expression, stopWord)) {
            displayMessage("Выход...");
            return true;
        }
        return false;
    }

    // Основной метод вычисления
    public void calculate(Operation operation) {
        try {
            double result = operation.calculate();
            displayResult(result);
        } catch (ArithmeticException e) {
            displayError(DIVISION_BY_ZERO_MSG);
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }

    public ExpressionParser getParser() {
        return parser;
    }
}

// Главный класс приложения
public class Main {
    public static void main(String[] args) {
        String expression;
        String stopWord = "exit";
        Calculator calculator = new Calculator();

        calculator.displayMessage("Поддерживаемые операции - сложение (+), вычитание (-), деление (/), " +
                "умножение (*), целочисленное деление (//), возведение в степень (^), остаток от деления (%).");

        // Непрерывная работа с калькулятором
        while (true) {
            calculator.displayMessage("Введите ваше выражение:");
            expression = calculator.enterExpression();

            // Валидация стоп слова
            if (calculator.checkStopWord(expression, stopWord)) {
                break;
            }

            // Валидация введенного выражения
            if (calculator.validateExpression(expression)) {
                try {
                    // Парсим выражение и создаем объект операции
                    Operation operation = calculator.getParser().parseExpression(expression);
                    // Вычисляем результат
                    calculator.calculate(operation);
                } catch (IllegalArgumentException e) {
                    calculator.displayError("Неверный формат выражения: " + e.getMessage());
                }
            } else {
                calculator.displayMessage("Неверное выражение. Введите еще раз:");
            }
        }
    }
}