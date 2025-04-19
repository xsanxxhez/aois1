package by.ageenko.aois1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberConverter converter = new NumberConverter();
        FixedPointCalculator fixedPointCalc = new FixedPointCalculator();
        FloatingPointCalculator floatingPointCalc = new FloatingPointCalculator();


        System.out.println("Выберите операцию:");
        System.out.println("1. Перевод числа в двоичные коды");
        System.out.println("2. Сложение двух чисел в дополнительном коде");
        System.out.println("3. Вычитание двух чисел в дополнительном коде");
        System.out.println("4. Умножение двух чисел в прямом коде");
        System.out.println("5. Деление двух чисел в прямом коде");
        System.out.println("6. Сложение двух чисел с плавающей точкой");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                handleNumberConversion(converter, scanner);
                break;
            case 2:
                handleAddition(fixedPointCalc, converter, scanner);
                break;
            case 3:
                handleSubtraction(fixedPointCalc, converter, scanner);
                break;
            case 4:
                handleMultiplication(fixedPointCalc, converter, scanner);
                break;
            case 5:
                handleDivision(fixedPointCalc, converter, scanner);
                break;
            case 6:
                handleFloatingAddition(floatingPointCalc, scanner);
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private static void handleNumberConversion(NumberConverter converter, Scanner scanner) {
        System.out.print("Введите число: ");
        int number = scanner.nextInt();
        displayNumberInfo(converter, number);
    }

    private static void handleAddition(FixedPointCalculator calc, NumberConverter converter, Scanner scanner) {
        System.out.print("Ввод числа №1: ");
        int num1 = scanner.nextInt();
        displayNumberInfo(converter, num1);

        System.out.print("Ввод числа №2: ");
        int num2 = scanner.nextInt();
        displayNumberInfo(converter, num2);

        int result = calc.add(num1, num2);
        System.out.println("Результат: " + result);
        displayNumberInfo(converter, result);
    }

    private static void handleSubtraction(FixedPointCalculator calc, NumberConverter converter, Scanner scanner) {
        System.out.print("Введите уменьшаемое: ");
        int num1 = scanner.nextInt();
        displayNumberInfo(converter, num1);

        System.out.print("Введите вычитаемое: ");
        int num2 = scanner.nextInt();
        displayNumberInfo(converter, num2);

        int result = calc.subtract(num1, num2);
        System.out.println("Результат: " + result);
        displayNumberInfo(converter, result);
    }

    private static void handleMultiplication(FixedPointCalculator calc, NumberConverter converter, Scanner scanner) {
        System.out.print("Введите множимое: ");
        int num1 = scanner.nextInt();
        displayNumberInfo(converter, num1);

        System.out.print("Введите множитель: ");
        int num2 = scanner.nextInt();
        displayNumberInfo(converter, num2);

        int result = calc.multiply(num1, num2);
        System.out.println("Результат: " + result);
        displayNumberInfo(converter, result);
    }

    private static void handleDivision(FixedPointCalculator calc, NumberConverter converter, Scanner scanner) {
        System.out.print("Введите делимое: ");
        int num1 = scanner.nextInt();
        displayNumberInfo(converter, num1);

        System.out.print("Введите делитель: ");
        int num2 = scanner.nextInt();
        displayNumberInfo(converter, num2);

        try {
            double result = calc.divide(num1, num2);
            System.out.println("Результат: " + result);
            System.out.println("Двоичное представление: " + converter.doubleToBinary(result));
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void handleFloatingAddition(FloatingPointCalculator calc, Scanner scanner) {
        System.out.print("Введите первое число: ");
        float num1 = scanner.nextFloat();

        System.out.print("Введите второе число: ");
        float num2 = scanner.nextFloat();

        float result = calc.add(num1, num2);
        System.out.println("Результат: " + result);
        System.out.println("IEEE-754 представление: " + calc.floatToBinary(result));
    }

    private static void displayNumberInfo(NumberConverter converter, int number) {
        System.out.println("Число введено: " + number);
        System.out.println("Прямой код: " + converter.toDirectCode(number));
        System.out.println("Обратный код: " + converter.toReverseCode(number));
        System.out.println("Дополнительный код: " + converter.toComplementCode(number));
    }
}