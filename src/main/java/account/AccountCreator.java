package account;

import account.Profile.Profilable;

public abstract class AccountCreator {
    Profilable profile;
    AccountData accountData;

    public AccountCreator(Profilable profile) {
        this.profile = profile;
    }

    abstract public String watchProfile();

    abstract public void watchAccountData();

    abstract public void setAccountData(AccountData accountData);
}
