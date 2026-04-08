package CurrencyCalculatorApp.CurrencyCalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BaseCurrencyEnumTest {

    @Test
    void testByName() {
        assertNotNull(BaseCurrencyEnum.byName("Euro"));
        assertEquals(BaseCurrencyEnum.USD, BaseCurrencyEnum.byName("US-Dollar"));
        assertNull(BaseCurrencyEnum.byName("NonExistent"));
    }

    @Test
    void testCalculateSameCurrency() {
        double result = BaseCurrencyEnum.EURO.calculate("Euro", "Euro", 100.0);
        assertEquals(100.0, result, 0.001);
    }

    @Test
    void testCalculateCrossRate() {
        double result = BaseCurrencyEnum.EURO.calculate("US-Dollar", "Pound Sterling", 100.0);
        assertEquals(75.0, result, 0.001);
    }
}