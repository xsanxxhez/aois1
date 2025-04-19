package by.ageenko.aois1;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void testNumberConversion() {
        String input = "1\n13\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});
        String output = out.toString();

        assertTrue(output.contains("Число введено: 13"));
        assertTrue(output.contains("Прямой код: [0 01101]"));
        assertTrue(output.contains("Обратный код: [0 01101]"));
        assertTrue(output.contains("Дополнительный код: [0 01101]"));
    }

    @Test
    void testAddition() {
        String input = "2\n13\n-23\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});
        String output = out.toString();

        assertTrue(output.contains("Результат: -10"));
        assertTrue(output.contains("Прямой код: [1 01010]"));
        assertTrue(output.contains("Обратный код: [1 10101]"));
        assertTrue(output.contains("Дополнительный код: [1 10110]"));
    }

    @Test
    void testFloatingAddition() {
        // Используем правильный разделитель для текущей локали
        String decimalSeparator = java.text.DecimalFormatSymbols.getInstance().getDecimalSeparator() + "";
        String input = "6\n1" + decimalSeparator + "5\n2" + decimalSeparator + "5\n";

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});
        String output = out.toString();

        assertTrue(output.contains("Результат: 4.0") || output.contains("Результат: 4,0"));
        assertTrue(output.contains("IEEE-754 представление:"));
    }
}