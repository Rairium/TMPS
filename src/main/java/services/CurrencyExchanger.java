package services;

import java.util.HashMap;

public class CurrencyExchanger {
    private static CurrencyExchanger currencyExchanger = null;
    private HashMap<String, Double> currency = new HashMap<String, Double>();

    private CurrencyExchanger() {
    }

    public HashMap<String, Double> getCurrency() {
        return currency;
    }

    public void setCurrency(String valuteName, Double valuteValue) {
        this.currency.put(valuteName, valuteValue);
    }

    public static CurrencyExchanger getInstance() {
        if (currencyExchanger == null)
            currencyExchanger = new CurrencyExchanger();
        return currencyExchanger;
    }

    public Double convert(String fromCurrency, String toCurrency, Double amount) {
        Double fromValueConvertor = this.currency.get(fromCurrency);
        Double toValueConvertor = this.currency.get(toCurrency);
        return (toValueConvertor * amount) / fromValueConvertor;
    }
}
