package firstHomeWork;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите размер массива:");

            int sizeArray = Integer.parseInt(scanner.nextLine());

            // Выкидываем ошибку, если введено отрицательное число или ноль
            if (sizeArray <= 0) {
                System.out.println("Размер массива не может быть меньше или равен нулю");
                return;
            }

            // Инициализация массивов
            int[] intArray = new int[sizeArray];
            double[] doubleArray = new double[sizeArray];

            System.out.println("Вы хотите ввести границы для генерации чисел? (y/n)");
            String answer = scanner.nextLine();

            // Валидация ответа
            if (answer.equals("y")) {
                System.out.println("Введите левую границу генерации случайных чисел для заполнения массива:");
                double minBound = scanner.nextDouble();
                System.out.println("Введите правую границу генерации случайных чисел для заполнения массива:");
                double maxBound = scanner.nextDouble();

                // Заполнение массивов значениями, учитывая границы распределения рандомных чисел
                for(int i = 0; i < sizeArray; ++i) {
                    intArray[i] = (int)(minBound + Math.random() * (maxBound - minBound + 1.0));
                    double value = minBound + Math.random() * (maxBound - minBound);
                    doubleArray[i] = (double)Math.round(value * 1000.0) / 1000.0;
                }
            } else { // Валидация отказа
                if (!answer.equals("n")) {
                    System.out.println("Данный ответ не поддерживается.");
                    return;
                }
                // Заполнение массивов значениями без границ распределения рандомных чисел
                for(int i = 0; i < sizeArray; ++i) {
                    intArray[i] = (int)(Math.random() * 201.0) - 100;
                    double value = Math.random() * 201.0 - 100.0;
                    doubleArray[i] = Math.round(value * 1000.0) / 1000.0;
                }
            }

            System.out.println("Целочисленный массив: " + Arrays.toString(intArray));
            System.out.println("Максимальное значение в массиве: " + max(intArray));
            System.out.println("Минимальное значение в массиве: " + min(intArray));
            System.out.println("Среднее значение элементов массива: " + average(intArray));
            System.out.println("Отсортированный по возрастанию массив: " + sortIntArray(intArray));
            System.out.println("Отсортированный по убыванию массив: " + sortIntArrayDesc(intArray));
            System.out.println("\nВещественный массив: " + Arrays.toString(doubleArray));
            System.out.println("Максимальное значение в вещественном массиве: " + max(doubleArray));
            System.out.println("Минимальное значение в вещественном массиве: " + min(doubleArray));
            System.out.println("Среднее значение элементов вещественного массива: " + average(doubleArray));
            System.out.println("Отсортированный по возрастанию вещественноого массива: " + sortDoubleArray(doubleArray));
            System.out.println("Отсортированный по убыванию вещественного массива: " + sortDoubleArrayDesc(doubleArray));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! Введите целое число.");
        }

    }

    // Для int массивов
    public static int max(int[] intArray) {
        int i = 0;
        int max;

        for(max = intArray[i]; i < intArray.length; ++i) {
            if (intArray[i] > max) {
                max = intArray[i];
            }
        }
        return max;
    }

    public static int min(int[] intArray) {
        int i = 0;

        int min;
        for(min = intArray[i]; i < intArray.length; ++i) {
            if (intArray[i] < min) {
                min = intArray[i];
            }
        }
        return min;
    }

    public static double average(int[] intArray) {
        double sum = 0.0;
        for(int i : intArray) {
            sum += i;
        }

        return sum / (double)intArray.length;
    }

    // Метод для сортировки по возрастанию
    public static String sortIntArray(int[] intArray) {
        Arrays.sort(intArray);
        return Arrays.toString(intArray);
    }

    // Метод для сортировки по убыванию
    public static String sortIntArrayDesc(int[] intArray) {
        Arrays.sort(intArray);

        for(int i = 0; i < intArray.length / 2; ++i) {
            int temp = intArray[i];
            intArray[i] = intArray[intArray.length - 1 - i];
            intArray[intArray.length - 1 - i] = temp;
        }

        return Arrays.toString(intArray);
    }

    // Для float массивов
    public static double max(double[] doubleArray) {
        int i = 0;

        double max;
        for(max = doubleArray[i]; i < doubleArray.length; ++i) {
            if (doubleArray[i] > max) {
                max = doubleArray[i];
            }
        }

        return max;
    }

    public static double min(double[] doubleArray) {
        int i = 0;

        double min;
        for(min = doubleArray[i]; i < doubleArray.length; ++i) {
            if (doubleArray[i] < min) {
                min = doubleArray[i];
            }
        }

        return min;
    }

    public static double average(double[] doubleArray) {
        double sum = 0.0;
        for(double i : doubleArray) {
            sum += i;
        }
        return sum / (double)doubleArray.length;
    }

    // Метод для сортировки по возрастанию
    public static String sortDoubleArray(double[] doubleArray) {
        Arrays.sort(doubleArray);
        return Arrays.toString(doubleArray);
    }

    // Метод для сортировки по убыванию
    public static String sortDoubleArrayDesc(double[] doubleArray) {
        Arrays.sort(doubleArray);

        for(int i = 0; i < doubleArray.length / 2; ++i) {
            double temp = doubleArray[i];
            doubleArray[i] = doubleArray[doubleArray.length - 1 - i];
            doubleArray[doubleArray.length - 1 - i] = temp;
        }

        return Arrays.toString(doubleArray);
    }
}
