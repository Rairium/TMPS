package account;

import account.Profile.Profilable;

public class Account extends AccountCreator {
    public Account(Profilable profile) {
        super(profile);
    }

    public void watchProfile() {
        System.out.println("This account is with a " + profile.getProfile());
    }

    public void watchAccountData() {
        System.out.println(this.accountData.getId());
        System.out.println(this.accountData.getEmail());
        System.out.println(this.accountData.getLocation());
        System.out.println(this.accountData.getName());
        System.out.println(this.accountData.getSurname());
        System.out.println(this.accountData.getPhone());
        System.out.println(this.accountData.getPassword());
        System.out.println(this.accountData.getAmount());
    }

    public AccountData getAccountData() {
        return this.accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

}
