package exchange;

import java.util.HashMap;

public class Exchanger {
    private static Exchanger exchanger = null;
    private HashMap<String, Double> currency = new HashMap<String, Double>();

    private Exchanger() {
    }

    public HashMap<String, Double> getCurrency() {
        return currency;
    }

    public void setCurrency(String valuteName, Double valuteValue) {
        this.currency.put(valuteName, valuteValue);
    }

    public static Exchanger getInstance() {
        if (exchanger == null)
            exchanger = new Exchanger();
        return exchanger;
    }

    public Double convert(String fromCurrency, String toCurrency, Double amount) {
        Double fromValueConvertor = this.currency.get(fromCurrency);
        Double toValueConvertor = this.currency.get(toCurrency);
        return (toValueConvertor * amount) / fromValueConvertor;
    }
}
