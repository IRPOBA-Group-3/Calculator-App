import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtraction() {
        assertEquals(3, calculator.subtract(5, 2));
    }

    @Test
    void testMultiplication() {
        assertEquals(12, calculator.multiply(4, 3));
    }

    @Test
    void testDivision() {
        assertEquals(4, calculator.divide(8, 2));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(8, 0));
    }

    @Test
    void testPercent() {
        assertEquals(0.5, calculator.percent(50));
    }

    @Test
    void testNegate() {
        assertEquals(-5, calculator.negate(5));
    }
}
