package account;

import account.Profile.Profilable;

import java.util.ArrayList;

public abstract class AccountCreator {
    Profilable profile;
    AccountData accountData;

    public AccountCreator(Profilable profile) {
        this.profile = profile;
    }

    abstract public void watchProfile();

    abstract public void watchAccountData();

    abstract public AccountData getAccountData();

    abstract public void setAccountData(AccountData accountData);

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<String>();
        data.add(this.accountData.getEmail());
        data.add(this.accountData.getPassword());
        return data;
    }
}
