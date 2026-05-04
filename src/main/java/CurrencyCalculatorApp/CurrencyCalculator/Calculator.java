package CurrencyCalculatorApp.CurrencyCalculator;

import java.util.*;

public class Calculator implements Calculate{
    Set<Currency> currencies;
    int currencyCount = 0;

    public Calculator() {
        initializeBaseCurrencies();
    }

    private void initializeBaseCurrencies() {
        currencies = new HashSet<>();
        //currencies = new Currency[200];
        currencies.add(new Currency("Euro",1));
        currencies.add(new Currency("US-Dollar",0.87));
        currencies.add(new Currency("Pound Sterling",1.16));
        currencies.add(new Currency("Russian Ruble",0.01));
        currencyCount = currencies.size();
    }

    public void createNewCurrency(String currencyName, double rate, String relativeTo) {
        double relativeRate;
        for (Currency currency : currencies) {
            if (relativeTo.equals(currency.getName())) {
                relativeRate = currency.getCurrencyRate();
                rate = rate * relativeRate;
                break;
            }
        }
        /*for (int i = 0; i < currencyCount; i++) {
            if (relativeTo.equals(currencies[i].getName())) {
                relativeRate = currencies[i].getCurrencyRate();
                rate = rate*relativeRate;
                break;
            }
        }*/
        currencies.add(new Currency(currencyName,rate));
    }

    public List<String> getCurrencyNames() {
        List<String> names = new ArrayList<>();
        for (Currency currency : currencies) {
            names.add(currency.getName());
        }
        return names;
    }

    public boolean checkIfNewName(String currencyName) {
        for (Currency currency : currencies) {
            if (currency.getName().equals(currencyName)) {
                return true;
            }
        }
//        for (int i = 0; i < currencyCount; i++) {
//            if (currencies[i].getName().equals(currency)) {
//                return true;
//            }
//        }
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
        for (Currency currency : currencies) {
            if (fromCurrency.equals(currency.getName()) || toCurrency.equals(currency.getName())) {
                if (fromCurrency.equals(currency.getName())) {
                    fromRate = currency.getCurrencyRate();
                }else{
                    toRate = currency.getCurrencyRate();
                }
                if (foundRates > 0) {
                    break;
                }
                foundRates++;
            }
        }
//        for (int i = 0; i < currencyCount; i++) {
//            if (fromCurrency.equals(currencies[i].getName()) || toCurrency.equals(currencies[i].getName())) {
//                if (fromCurrency.equals(currencies[i].getName())) {
//                    fromRate = currencies[i].getCurrencyRate();
//                }else{
//                    toRate = currencies[i].getCurrencyRate();
//                }
//                if (foundRates > 0) {
//                    break;
//                }
//                foundRates++;
//            }
//        }
        return amount * fromRate / toRate;
    }
}
