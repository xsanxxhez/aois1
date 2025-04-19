package by.ageenko.aois1;

public class FixedPointCalculator {
    private NumberConverter converter = new NumberConverter();

    public int add(int a, int b) {
        // Используем дополнительный код для сложения
        String aComplement = converter.toComplementCode(a);
        String bComplement = converter.toComplementCode(b);

        // Удаляем скобки и пробелы
        String aBits = aComplement.replaceAll("[\\[\\] ]", "");
        String bBits = bComplement.replaceAll("[\\[\\] ]", "");


        StringBuilder result = new StringBuilder();
        char carry = '0';
        for (int i = aBits.length() - 1; i >= 0; i--) {
            char aBit = aBits.charAt(i);
            char bBit = bBits.charAt(i);


            char sum = xor(xor(aBit, bBit), carry);
            carry = or(and(aBit, bBit), and(xor(aBit, bBit), carry));
            result.insert(0, sum);
        }

                //
        char signBit = result.charAt(0);
        String magnitude = result.substring(1);


        if (signBit == '0') {
            return Integer.parseInt(magnitude, 2);
        } else {
            // Если отрицательное, преобразуем из дополнительного кода
            String reverseMagnitude = invertBits(magnitude);
            int value = Integer.parseInt(reverseMagnitude, 2) + 1;
            return -value;
        }
    }

    public int subtract(int a, int b) {
        return add(a, -b);
    }

    public int multiply(int a, int b) {
        // Умножение в прямом коде (знак отдельно, модули перемножаются)
        int sign = (a < 0) ^ (b < 0) ? -1 : 1;
        int absA = Math.abs(a);
        int absB = Math.abs(b);

        int result = 0;
        for (int i = 0; i < 5; i++) {
            if ((absB & (1 << i)) != 0) {
                result += absA << i;
            }
        }

        return sign * result;
    }

    public double divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Деление на ноль");

        // Знак результата
        int sign = (a < 0) ^ (b < 0) ? -1 : 1;
        int absA = Math.abs(a);
        int absB = Math.abs(b);

        // Целая часть
        int quotient = 0;
        while (absA >= absB) {
            absA -= absB;
            quotient++;
        }

        // Дробная часть (с точностью до 5 знаков)
        double fractional = 0;
        double remainder = absA;
        for (int i = 0; i < 5; i++) {
            remainder *= 2;
            if (remainder >= absB) {
                fractional += 1.0 / (1 << (i + 1));
                remainder -= absB;
            }
        }

        return sign * (quotient + fractional);
    }

    private char xor(char a, char b) {
        return a == b ? '0' : '1';
    }

    private char and(char a, char b) {
        return (a == '1' && b == '1') ? '1' : '0';
    }

    private char or(char a, char b) {
        return (a == '1' || b == '1') ? '1' : '0';
    }

    private String invertBits(String binary) {
        StringBuilder inverted = new StringBuilder();
        for (char c : binary.toCharArray()) {
            inverted.append(c == '0' ? '1' : '0');
        }
        return inverted.toString();
    }
}
