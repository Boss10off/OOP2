package CurrencyCalculatorApp.CurrencyCalculator;

public enum BaseCurrencyEnum implements Calculate {
    EURO("Euro", 1.0),
    USD("US-Dollar", 0.87),
    GBP("Pound Sterling", 1.16),
    RUB("Russian Ruble", 0.01);

    private final String name;
    private final double rate;
    
    BaseCurrencyEnum(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public static BaseCurrencyEnum byName(String name) {
        for (BaseCurrencyEnum currency : values()) {
            if (currency.name.equals(name)) {
                return currency;
            }
        }
        return null;
    }

    @Override
    public double calculate(String fromCurrency, String toCurrency, double amount) {
        BaseCurrencyEnum from = byName(fromCurrency);
        BaseCurrencyEnum to = byName(toCurrency);

        if (from == to) return amount;

        return amount * from.getRate() / to.getRate();
    }
}