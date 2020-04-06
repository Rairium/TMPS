package facade;

import account.AccountData;
import account.Profile.ClientList;
import documents.BankDocument;
import exceptions.AccountDataNotFound;
import org.jetbrains.annotations.NotNull;
import persons.clients.Client;
import services.AccountController;
import services.CurrencyExchanger;
import services.TransactionController;
import services.ValuteController;
import services.exchanger.ExchangeController;
import services.transactions.Transaction;

import java.util.ArrayList;


public class BankFacade {
    private ClientList clientList = ClientList.getInstance();
    private Client client;
    private ValuteController valuteController;
    private TransactionController transactionController;
    private AccountController accountController;

    public BankFacade(String email, String password) {
        this.client = findClient(email, password);
        this.valuteController = new ValuteController(this.client.getAccount().getAccountData().getAmount());
        this.transactionController = new TransactionController(this.client);
        this.accountController = new AccountController(this.client.getAccount());
        WelcomeToBank.WelcomeToBank();
    }

    private Client findClient(String email, String password) {
        for (Client client : clientList.getClientList()) {
            if (client.getAccount().getData().get(0).equals(email) && client.getAccount().getData().get(1).equals(password)) {
                return client;
            }
        }
        throw new AccountDataNotFound("Wrong Data, Please check your credentials");
    }

    public void viewBalance(){
        valuteController.viewBalance();
    }

    private AccountData.AccountBuilder maintainAccountData(){
        return new AccountData.AccountBuilder().accountBuilder(this.accountController.getAccount().getAccountData());
    }

    public void addMoney(Double amount){
        Double addedMoney = valuteController.addMoney(amount);
        this.accountController.setAccountData(new AccountData.AccountBuilder().accountBuilder(maintainAccountData().build()).amount(addedMoney).build());
        this.accountController.setAccountData(maintainAccountData().build());
    }

    public void withdrawMoney(Double amount) {
        if (valuteController.isEnoughMoney(amount)){
            Double withdrawMoney = valuteController.withdrawMoney(amount);
            this.accountController.setAccountData(new AccountData.AccountBuilder().accountBuilder(maintainAccountData().build()).amount(withdrawMoney).build());
            this.accountController.setAccountData(maintainAccountData().build());
        } else System.out.println("Insufficient cash in bank account, your current balance is: " + valuteController.getBalance());
    }

    public void exchangeMoney(String fromValute, String toValute, Double amount){
        if(valuteController.isEnoughMoney(amount, fromValute, toValute)) {
            System.out.println("You have successfully exchanged an amount of " + amount + " from " + fromValute + " to " + toValute);
            if(fromValute.equals("Leu")){
                Double withdrawMoney = valuteController.withdrawMoney(amount);
                this.accountController.setAccountData(new AccountData.AccountBuilder().accountBuilder(maintainAccountData().build()).amount(withdrawMoney).build());
                this.accountController.setAccountData(maintainAccountData().build());
            } else {
                Double withdrawMoney = valuteController.withdrawMoney(ExchangeController.exchange(CurrencyExchanger.getInstance(), fromValute, toValute, amount));
                this.accountController.setAccountData(new AccountData.AccountBuilder().accountBuilder(maintainAccountData().build()).amount(withdrawMoney).build());
                this.accountController.setAccountData(maintainAccountData().build());
            }
            System.out.println("You got " + ExchangeController.exchange(CurrencyExchanger.getInstance(), fromValute, toValute, amount) + " " + toValute);
        }
        else System.out.println("Insufficient cash in bank account, your current balance is: " + valuteController.getBalance());
    }

    public void getAccountInfo(){
        this.accountController.setAccountData(maintainAccountData().build());
        accountController.getAccountInfo();
    }

    public void viewTransactionList(@NotNull ArrayList<Transaction> transactions) {
        transactionController.viewTransactionList(transactions);
    }

    public void createTransaction(BankDocument bankDocument){
        transactionController.createTransaction(bankDocument);
    }
}
