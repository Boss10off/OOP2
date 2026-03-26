package WeahrungsrechnerAPP.Waehrungsrechner;

public class Rechner implements WeahrungsrechnerAPP.Waehrungsrechner.Calculate {
    Currency[] currencies;
    int currencyCount = 0;


    public Rechner() {
        initializeBaseCurrencies();
    }

    private void initializeBaseCurrencies() {
        currencies = new Currency[200];
        currencies[0] = new Currency("Euro",1);
        currencies[1] = new Currency("US-Dollar",0.87);
        currencies[2] = new Currency("Pound Sterling",1.16);
        currencies[3] = new Currency("Russian Ruble",0.01);
        currencyCount = 4;
    }

    public void createNewCurrency(String currency, double rate, String relativeTo) {
        double relativeRate;
        for (int i = 0; i < currencyCount; i++) {
            if (relativeTo.equals(currencies[i].getName())) {
                relativeRate = currencies[i].getCurrencyRate();
                rate = rate*relativeRate;
                break;
            }
        }
        currencies[currencyCount++] = new Currency(currency,rate);
    }

    public String[] getCurrencyNames() {
        String[] names = new String[currencyCount];
        for (int i = 0; i < currencyCount; i++) {
            names[i] = currencies[i].getName();
        }
        return names;
    }

    public boolean checkIfNewName(String currency) {
        for (int i = 0; i < currencyCount; i++) {
            if (currencies[i].getName().equals(currency)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double calculate(String fromCurrency, String toCurrency, double amount) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }
        double fromRate = 0;
        double toRate = 0;
        int foundRates=0;
        for (int i = 0; i < currencyCount; i++) {
            if (fromCurrency.equals(currencies[i].getName()) || toCurrency.equals(currencies[i].getName())) {
                if (fromCurrency.equals(currencies[i].getName())) {
                    fromRate = currencies[i].getCurrencyRate();
                    if (foundRates > 0) {
                        break;
                    }
                    foundRates++;
                }else{
                    toRate = currencies[i].getCurrencyRate();
                    if (foundRates > 0) {
                        break;
                    }
                    foundRates++;
                }
            }
        }
        return amount * fromRate / toRate;
    }

}
