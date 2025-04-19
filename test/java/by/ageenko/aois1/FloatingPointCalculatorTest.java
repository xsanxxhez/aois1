package by.ageenko.aois1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloatingPointCalculatorTest {
    private final FloatingPointCalculator calculator = new FloatingPointCalculator();

    @Test
    void testAdd_PositiveNumbers() {
        assertEquals(15.0f, calculator.add(10.0f, 5.0f));
        assertEquals(25.0f, calculator.add(10.0f, 15.0f));
        assertEquals(0.0f, calculator.add(0.0f, 0.0f));
    }

    @Test
    void testAdd_NegativeNumbers() {
        assertEquals(-15.0f, calculator.add(-10.0f, -5.0f));
        assertEquals(-25.0f, calculator.add(-10.0f, -15.0f));
    }


    @Test
    void testAdd_FractionalNumbers() {
        assertEquals(3.3f, calculator.add(1.1f, 2.2f), 0.0001f);
        assertEquals(-3.3f, calculator.add(-1.1f, -2.2f), 0.0001f);
        assertEquals(1.1f, calculator.add(3.3f, -2.2f), 0.0001f);
    }

    @Test
    void testFloatToBinary() {
        assertEquals("0 10000010 01000000000000000000000", calculator.floatToBinary(10.0f));
        assertEquals("0 01111111 00000000000000000000000", calculator.floatToBinary(1.0f));
        assertEquals("1 10000010 01000000000000000000000", calculator.floatToBinary(-10.0f));
    }
}
