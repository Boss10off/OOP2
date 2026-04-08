package CurrencyCalculatorApp.CurrencyCalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;

class CurrencyLogicTest {

    static Stream<Calculate> implementationProvider() {
        return Stream.of(
                new Calculator(),
                BaseCurrencyEnum.EURO
        );
    }

    @ParameterizedTest
    @MethodSource("implementationProvider")
    void shouldConvertCorrectUnits(Calculate implementation) {

        assertEquals(100.0, implementation.calculate("Euro", "Euro", 100.0),
                "Identity conversion failed for " + implementation.getClass().getSimpleName());

        double euroToUsd = implementation.calculate("Euro", "US-Dollar", 100.0);
        assertEquals(114.942, euroToUsd, 0.001);

        double usdToEuro = implementation.calculate("US-Dollar", "Euro", 100.0);
        assertEquals(87.0, usdToEuro, 0.001);

        double usdToGbp = implementation.calculate("US-Dollar", "Pound Sterling", 100.0);
        assertEquals(75.0, usdToGbp, 0.001);
    }

    @ParameterizedTest
    @MethodSource("implementationProvider")
    void shouldHandleZeroAmount(Calculate implementation) {
        double result = implementation.calculate("Euro", "Russian Ruble", 0.0);
        assertEquals(0.0, result, 0.0);
    }
}
