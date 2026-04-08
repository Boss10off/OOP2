package CurrencyCalculatorApp.CurrencyCalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testInitializeBaseCurrencies() {
        String[] names = calculator.getCurrencyNames();
        assertTrue(names.length >= 4);
        assertEquals("Euro", names[0]);
    }

    @Test
    void testCreateNewCurrencyRelative() {
        calculator.createNewCurrency("Gold", 2.0, "Euro");

        double result = calculator.calculate("Gold", "Euro", 100.0);
        assertEquals(200.0, result, 0.001);
    }

    @Test
    void testCheckIfNewName() {
        assertTrue(calculator.checkIfNewName("Euro"));
        assertFalse(calculator.checkIfNewName("Bitcoin"));
    }
}