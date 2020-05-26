import account.Account;
import account.AccountCreator;
import account.AccountData;
import account.Profile.ClientList;
import account.Profile.GoldenProfile;
import account.Profile.SimpleProfile;
import documents.CreditDocument;
import documents.DepositDocument;
import documents.TransferDocument;
import facade.BankFacade;
import gui.MainFrame;
import locations.Location;
import persons.Worker;
import persons.clients.AmazonCompany;
import persons.clients.MicrosoftCompany;
import persons.clients.SimpleClient;
import services.DocumentMaker;
import services.CurrencyExchanger;
import services.transactions.mediators.CreditMediator;
import services.transactions.mediators.DepositMediator;
import services.transactions.mediators.InitialMediator;
import services.transactions.mediators.TransferMediator;

import javax.swing.*;

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
        creditDocument.setAmount(2000d);
        creditDocument.setGarantPersonName("Garant Person");
        DepositDocument depositDocument = new DepositDocument();
        depositDocument.setDate("Today");
        depositDocument.setSignature("Gutu");
        depositDocument.setAmount(2000d);
        depositDocument.setRate(5.5);
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
                .password("root")
                .build();
        AccountData accountData2 = new AccountData.AccountBuilder()
                .email("example@gmail.com")
                .id(2).name("Client")
                .surname("Another")
                .phone("+37379583373")
                .location(location)
                .password("toor")
                .build();
        // ---------------------------------------------------
        AccountCreator simpleAccount = new Account(new SimpleProfile());
        simpleAccount.watchProfile();
        simpleAccount.setAccountData(accountData);
        simpleAccount.watchAccountData();
        AccountCreator goldenAccount = new Account(new GoldenProfile());
        goldenAccount.watchProfile();
        goldenAccount.setAccountData(accountData2);
        goldenAccount.watchAccountData();

        // ---------------------------------------------------
        TransferDocument transferDocument = new TransferDocument();
        transferDocument.setDate("Today");
        transferDocument.setSignature("Gutu");
        transferDocument.setAmount(2000d);
        transferDocument.setTransferType("P2P");
        transferDocument.setClientName("My Client");
        final InitialMediator initialMediator = new InitialMediator();
        CreditMediator creditMediator = new CreditMediator();
        DepositMediator depositMediator = new DepositMediator();
        TransferMediator transferMediator = new TransferMediator();
        initialMediator.setNextMediator(creditMediator);
        creditMediator.setNextMediator(depositMediator);
        depositMediator.setNextMediator(transferMediator);
        MicrosoftCompany microsoftClient = new MicrosoftCompany(initialMediator);
        microsoftClient.setClientId(101);
        microsoftClient.setAccount(simpleAccount);
        microsoftClient.getAccountInfo();
        AmazonCompany amazonClient = new AmazonCompany(initialMediator);
        amazonClient.setClientId(202);
        amazonClient.setAccount(goldenAccount);
        amazonClient.createTransaction(depositDocument);
        microsoftClient.createTransaction(creditDocument);
        microsoftClient.createTransaction(creditDocument);
        microsoftClient.createTransaction(creditDocument);
        amazonClient.createTransaction(clonedCreditDocument);
        amazonClient.createTransaction(transferDocument);
        depositMediator.getAllClients();
        creditMediator.getAllClients();
        transferMediator.getAllClients();
        System.out.println("See microsoftClient transactions in all mediators stocks");
        System.out.println("---------------------------------------");
        System.out.println("transactions from credit mediator");
        microsoftClient.viewTransactionList(creditMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        System.out.println("transactions from deposit mediator");
        microsoftClient.viewTransactionList(depositMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        System.out.println("transactions from transfer mediator");
        microsoftClient.viewTransactionList(transferMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        System.out.println("See amazonClient transactions in all mediators stocks");
        System.out.println("---------------------------------------");
        System.out.println("transactions from credit mediator");
        amazonClient.viewTransactionList(creditMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        System.out.println("transactions from deposit mediator");
        amazonClient.viewTransactionList(depositMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        System.out.println("transactions from transfer mediator");
        amazonClient.viewTransactionList(transferMediator.getTransactionStock());
        Worker worker = new Worker(initialMediator);
        worker.setWorkerId(303);
        worker.confirmTransaction(creditDocument);
        System.out.println("See client transactions in all mediators stocks");
        System.out.println("---------------------------------------");
        System.out.println("transactions from credit mediator");
        microsoftClient.viewTransactionList(creditMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        System.out.println("transactions from deposit mediator");
        microsoftClient.viewTransactionList(depositMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        System.out.println("transactions from transfer mediator");
        microsoftClient.viewTransactionList(transferMediator.getTransactionStock());
        System.out.println("---------------------------------------");
        // ---------------------------------------------------
        final ClientList clientList = ClientList.getInstance();
        clientList.setClientInList(microsoftClient);
        clientList.setClientInList(amazonClient);
        microsoftClient.setAccount(simpleAccount);
//        BankFacade accessingBank = new BankFacade("gutsueric@gmail.com", "root");
//        accessingBank.getAccountInfo();
//        accessingBank.addMoney(2000d);
//        accessingBank.viewBalance();
//        accessingBank.withdrawMoney(2500d);
//        accessingBank.viewBalance();
//        accessingBank.withdrawMoney(1500d);
//        accessingBank.withdrawMoney(1500d);
//        accessingBank.withdrawMoney(1500d);
//        accessingBank.viewBalance();
//        accessingBank.withdrawMoney(1500d);
//        accessingBank.viewBalance();
//        accessingBank.addMoney(50000d);
//        accessingBank.viewBalance();
//        accessingBank.exchangeMoney("Leu", "Euro", 500d);
//        accessingBank.viewBalance();
//        accessingBank.viewBalance();
//        accessingBank.viewBalance();
//        accessingBank.viewBalance();
//        accessingBank.createTransaction(clonedCreditDocument2);
//        accessingBank.viewTransactionList(creditMediator.getTransactionStock());
//        accessingBank.getAccountInfo();
    }
}
