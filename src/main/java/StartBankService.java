import account.Account;
import account.AccountCreator;
import account.AccountData;
import account.Profile.GoldenProfile;
import account.Profile.SimpleProfile;
import documents.CreditDocument;
import locations.Location;
import services.DocumentMaker;
import services.CurrencyExchanger;

public class StartBankService {
    public static void main(String[] args) {
        CurrencyExchanger currencyExchanger = CurrencyExchanger.getInstance();
        currencyExchanger.setCurrency("USD", 0.06d);
        currencyExchanger.setCurrency("Leu", 1d);
        currencyExchanger.setCurrency("Euro", 0.05d);
        System.out.println(currencyExchanger.getCurrency());
        System.out.println(currencyExchanger.convert("Euro", "Leu", 200d));
        CurrencyExchanger anotherCurrencyExchanger = CurrencyExchanger.getInstance();
        anotherCurrencyExchanger.setCurrency("RUB", 3.57d);
        System.out.println(anotherCurrencyExchanger.getCurrency());
        System.out.println(anotherCurrencyExchanger.convert("Leu", "RUB", 200d));

//      ------------------------------------------

        DocumentMaker documentMaker = new DocumentMaker();
        CreditDocument creditDocument = new CreditDocument();
        creditDocument.setDate("Today");
        creditDocument.setSignature("Gutu");
        CreditDocument clonedCreditDocument = (CreditDocument) documentMaker.getClonedDocument(creditDocument);
        CreditDocument clonedCreditDocument2 = (CreditDocument) documentMaker.getClonedDocument(creditDocument);
        System.out.println(clonedCreditDocument.getDate());
        System.out.println(clonedCreditDocument.getSignature());

//      -------------------------------------------
        Location location = new Location("Balti", "Balti", "Bulgara", 41);
        AccountData accountData = new AccountData.AccountBuilder()
                .email("gutsueric@gmail.com")
                .id(1).name("Eric")
                .surname("Gutu")
                .phone("+37379583373")
                .location(location)
                .build();
        // ---------------------------------------------------
        AccountCreator simpleAccount = new Account(new SimpleProfile());
        System.out.println(simpleAccount.watchProfile());
        simpleAccount.setAccountData(accountData);
        simpleAccount.watchAccountData();
        AccountCreator goldenAccount = new Account(new GoldenProfile());
        System.out.println(goldenAccount.watchProfile());
    }
}
