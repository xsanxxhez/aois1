package by.ageenko.aois1;

public class FloatingPointCalculator {
    public float add(float a, float b) {
        // Разбор чисел на знак, экспоненту и мантиссу
        int aBits = Float.floatToRawIntBits(a);
        int bBits = Float.floatToRawIntBits(b);
        int aSign = aBits >>> 31;
        int aExponent = (aBits >>> 23) & 0xFF;
        int aMantissa = aBits & 0x7FFFFF;

        int bSign = bBits >>> 31;
        int bExponent = (bBits >>> 23) & 0xFF;
        int bMantissa = bBits & 0x7FFFFF;

        // одно из чисел - ноль, возвращаем другое
        if (aExponent == 0 && aMantissa == 0) return b;
        if (bExponent == 0 && bMantissa == 0) return a;

        // если число не денормализованное
        if (aExponent != 0) aMantissa |= 0x800000;
        if (bExponent != 0) bMantissa |= 0x800000;

        // Выравнивание экспонент
        while (aExponent > bExponent) {
            bMantissa >>= 1;
            bExponent++;
        }
        while (bExponent > aExponent) {
            aMantissa >>= 1;
            aExponent++;
        }

        // Сложение мантисс с учетом знаков
        int resultMantissa;
        int resultSign;
        if (aSign == bSign) {
            resultMantissa = aMantissa + bMantissa;
            resultSign = aSign;
        } else {
            if (aMantissa >= bMantissa) {
                resultMantissa = aMantissa - bMantissa;
                resultSign = aSign;
            } else {
                resultMantissa = bMantissa - aMantissa;
                resultSign = bSign;
            }
        }

        // Нормализация
        if ((resultMantissa & 0x1000000) != 0) {
            resultMantissa >>= 1;
            aExponent++;
        }
        while (resultMantissa != 0 && (resultMantissa & 0x800000) == 0) {
            resultMantissa <<= 1;
            aExponent--;
        }

        // переполнение
        if (aExponent >= 0xFF) {
            return Float.POSITIVE_INFINITY;
        }
        if (aExponent <= 0) {
            return 0.0f;
        }

        // Удаление скрытого бита
        resultMantissa &= 0x7FFFFF;

        // Сборка результата
        int resultBits = (resultSign << 31) | (aExponent << 23) | resultMantissa;
        return Float.intBitsToFloat(resultBits);
    }

    public String floatToBinary(float number) {
        int bits = Float.floatToRawIntBits(number);
        StringBuilder binary = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            binary.append((bits & (1 << i)) != 0 ? '1' : '0');
            if (i == 31 || i == 23) binary.append(' ');
        }
        return binary.toString();
    }
}
