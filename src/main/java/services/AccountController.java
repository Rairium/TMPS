package services;

import account.AccountCreator;
import account.AccountData;

public class AccountController {
    private AccountCreator account;
    public AccountController(AccountCreator account){
        this.account = account;
    }

    public void getAccountInfo(){
        System.out.println("-------------------------------");
        this.account.watchAccountData();
        System.out.println("-------------------------------");
        this.account.watchProfile();
        System.out.println("-------------------------------");
    }

    public AccountCreator getAccount() {
        return account;
    }

    public void setAccountData(AccountData accountData){
        this.account.setAccountData(accountData);
    }
}
