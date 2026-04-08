package CurrencyCalculatorApp.CurrencyCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void testCurrencyProperties() {
        Currency currency = new Currency("Yen", 0.008);
        assertEquals("Yen", currency.getName());
        assertEquals(0.008, currency.getCurrencyRate());
    }
}