package by.ageenko.aois1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberConverterTest {
    private final NumberConverter converter = new NumberConverter();

    @Test
    void testToDirectCode_PositiveNumber() {
        assertEquals("[0 00101]", converter.toDirectCode(5));
        assertEquals("[0 01010]", converter.toDirectCode(10));
        assertEquals("[0 00000]", converter.toDirectCode(0));
    }

    @Test
    void testToDirectCode_NegativeNumber() {
        assertEquals("[1 00101]", converter.toDirectCode(-5));
        assertEquals("[1 01010]", converter.toDirectCode(-10));
    }

    @Test
    void testToReverseCode_PositiveNumber() {
        assertEquals("[0 00101]", converter.toReverseCode(5));
        assertEquals("[0 01010]", converter.toReverseCode(10));
        assertEquals("[0 00000]", converter.toReverseCode(0));
    }

    @Test
    void testToReverseCode_NegativeNumber() {
        assertEquals("[1 11010]", converter.toReverseCode(-5));
        assertEquals("[1 10101]", converter.toReverseCode(-10));
    }

    @Test
    void testToComplementCode_PositiveNumber() {
        assertEquals("[0 00101]", converter.toComplementCode(5));
        assertEquals("[0 01010]", converter.toComplementCode(10));
        assertEquals("[0 00000]", converter.toComplementCode(0));
    }

    @Test
    void testToComplementCode_NegativeNumber() {
        assertEquals("[1 11011]", converter.toComplementCode(-5));
        assertEquals("[1 10110]", converter.toComplementCode(-10));
    }

    @Test
    void testDoubleToBinary() {
        assertEquals("00101.00000", converter.doubleToBinary(5.0));
        assertEquals("01010.10100", converter.doubleToBinary(10.625));
        assertEquals("00000.00000", converter.doubleToBinary(0.0));
    }
}