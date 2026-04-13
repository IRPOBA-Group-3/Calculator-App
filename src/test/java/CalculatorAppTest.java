import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorAppTest {

    @Test
    void testAddition() {
        assertEquals(5, CalculatorApp.add(2, 3));
    }

    @Test
    void testSubtraction() {
        assertEquals(3, CalculatorApp.subtract(5, 2));
    }

    @Test
    void testMultiplication() {
        assertEquals(12, CalculatorApp.multiply(4, 3));
    }

    @Test
    void testDivision() {
        assertEquals(4, CalculatorApp.divide(8, 2));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> CalculatorApp.divide(8, 0));
    }
}
