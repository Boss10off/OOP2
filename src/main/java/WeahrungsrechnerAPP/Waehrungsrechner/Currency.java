package WeahrungsrechnerAPP.Waehrungsrechner;

public class Currency {
    String name;
    double currencyRate;

    Currency(String name, double rate) {
        this.name = name;
        currencyRate = rate;
    }

    public String getName() {
        return name;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }
}
