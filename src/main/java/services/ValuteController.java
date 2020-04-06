package services;


import services.exchanger.ExchangeController;

public class ValuteController {
    private Double cashInAccount;
    public ValuteController(Double cashInAccount){
        this.cashInAccount = cashInAccount;
    }

    public void viewBalance(){
        System.out.println("You have " + cashInAccount + " in your account");
    }

    public Double getBalance(){
        return this.cashInAccount;
    }

    public Double addMoney(Double amount){
        System.out.println(amount + " was successfully added to your bank account");
        this.cashInAccount += amount;
        return this.cashInAccount;
    }

    public Double withdrawMoney(Double amount){
        System.out.println("You withdraw " + amount + " from your bank account");
        this.cashInAccount -= amount;
        return this.cashInAccount;
    }

    public boolean isEnoughMoney(Double amount){
        return this.cashInAccount >= amount;
    }

    public boolean isEnoughMoney(Double amount, String fromValue, String toValue){
        return this.cashInAccount >= ExchangeController.exchange(CurrencyExchanger.getInstance(), fromValue, toValue, amount);
    }
}
