package by.ageenko.aois1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FixedPointCalculatorTest {
    private final FixedPointCalculator calculator = new FixedPointCalculator();

    @Test
    void testAdd_PositiveNumbers() {
        assertEquals(15, calculator.add(10, 5));
        assertEquals(25, calculator.add(10, 15));
        assertEquals(0, calculator.add(0, 0));
    }

    @Test
    void testAdd_NegativeNumbers() {
        assertEquals(-15, calculator.add(-10, -5));
        assertEquals(-25, calculator.add(-10, -15));
    }

    @Test
    void testAdd_MixedNumbers() {
        assertEquals(5, calculator.add(10, -5));
        assertEquals(-5, calculator.add(-10, 5));
        assertEquals(0, calculator.add(5, -5));
    }

    @Test
    void testSubtract_PositiveNumbers() {
        assertEquals(5, calculator.subtract(10, 5));
        assertEquals(-5, calculator.subtract(5, 10));
        assertEquals(0, calculator.subtract(10, 10));
    }

    @Test
    void testSubtract_NegativeNumbers() {
        assertEquals(-5, calculator.subtract(-10, -5));
        assertEquals(5, calculator.subtract(-5, -10));
    }

    @Test
    void testSubtract_MixedNumbers() {
        assertEquals(15, calculator.subtract(10, -5));
        assertEquals(-15, calculator.subtract(-10, 5));
    }

    @Test
    void testMultiply_PositiveNumbers() {
        assertEquals(50, calculator.multiply(10, 5));
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(100, calculator.multiply(10, 10));
    }

    @Test
    void testMultiply_NegativeNumbers() {
        assertEquals(50, calculator.multiply(-10, -5));
        assertEquals(-50, calculator.multiply(-10, 5));
        assertEquals(-50, calculator.multiply(10, -5));
    }

    @Test
    void testDivide_PositiveNumbers() {
        assertEquals(2.0, calculator.divide(10, 5), 0.0001);
        assertEquals(0.5, calculator.divide(5, 10), 0.0001);
        assertEquals(0.0, calculator.divide(0, 10), 0.0001);
    }

    @Test
    void testDivide_NegativeNumbers() {
        assertEquals(2.0, calculator.divide(-10, -5), 0.0001);
        assertEquals(-2.0, calculator.divide(-10, 5), 0.0001);
        assertEquals(-2.0, calculator.divide(10, -5), 0.0001);
    }

    @Test
    void testDivide_ByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }
}