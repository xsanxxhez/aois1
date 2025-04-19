package by.ageenko.aois1;

public class NumberConverter {
    public String toDirectCode(int number) {
        if (number == 0) return "[0 00000]";

        StringBuilder binary = new StringBuilder();
        int absNumber = Math.abs(number);


        while (absNumber > 0) {
            binary.insert(0, absNumber % 2);
            absNumber /= 2;
        }

        char signBit = number < 0 ? '1' : '0';
        return "[" + signBit + " " + String.format("%5s", binary.toString()).replace(' ', '0') + "]";
    }

    public String toReverseCode(int number) {
        if (number == 0) return "[0 00000]";

        String directCode = toDirectCode(number);
        if (number > 0) return directCode;

        // Инвертирование битов для отрицательных чисел
        String binaryPart = directCode.substring(3, 8);
        StringBuilder inverted = new StringBuilder();
        for (char c : binaryPart.toCharArray()) {
            inverted.append(c == '0' ? '1' : '0');
        }

        return "[1 " + inverted.toString() + "]";
    }

    public String toComplementCode(int number) {
        if (number == 0) return "[0 00000]";
        if (number > 0) return toDirectCode(number);

        String reverseCode = toReverseCode(number);
        String binaryPart = reverseCode.substring(3, 8);

        // Добавление 1 к обратному коду
        StringBuilder complement = new StringBuilder(binaryPart);
        boolean carry = true;
        for (int i = complement.length() - 1; i >= 0 && carry; i--) {
            if (complement.charAt(i) == '0') {
                complement.setCharAt(i, '1');
                carry = false;
            } else {
                complement.setCharAt(i, '0');
            }
        }

        return "[1 " + complement.toString() + "]";
    }

    public String doubleToBinary(double number) {
        int intPart = (int) number;
        String intBinary = toDirectCode(intPart).substring(3, 8);

        // Преобразование дробной части
        double fractionalPart = number - intPart;
        StringBuilder fracBinary = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            fractionalPart *= 2;
            int bit = (int) fractionalPart;
            fracBinary.append(bit);
            fractionalPart -= bit;
        }

        return intBinary + "." + fracBinary.toString();
    }
}