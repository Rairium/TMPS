package services.exchanger;

import services.CurrencyExchanger;

public class ExchangeController {
    public static Double exchange(CurrencyExchanger currencyExchanger, String fromValute, String toValute, Double amount){
        return currencyExchanger.convert(fromValute, toValute, amount);
    }
}
