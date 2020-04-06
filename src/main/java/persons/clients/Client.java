package persons.clients;

import account.AccountCreator;
import documents.BankDocument;
import org.jetbrains.annotations.NotNull;
import services.transactions.Transaction;
import services.transactions.mediators.ServiceMediator;

import java.util.ArrayList;

public abstract class Client {
    private ServiceMediator mediator;
    private int clientId;
    private AccountCreator account;

    public Client(ServiceMediator newMediator) {
        mediator = newMediator;
    }

    public void createTransaction(BankDocument bankDocument) {
        mediator.createTransaction(bankDocument, this);
    }

    public void viewTransactionList(@NotNull ArrayList<Transaction> transactions) {
        for (Transaction transaction: transactions) {
            if (transaction.getClientId() == this.clientId)
                System.out.println("transaction id = " + transaction.getTransactionId() + " document id = " + transaction.getDocument().getDocumentId());
        }
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setAccount(AccountCreator account) {
        this.account = account;
    }

    public AccountCreator getAccount() {
        return account;
    }

    public ServiceMediator getMediator() {
        return mediator;
    }

    public void getAccountInfo(){
        System.out.println("-------------------------------");
        this.account.watchAccountData();
        System.out.println("-------------------------------");
        this.account.watchProfile();
        System.out.println("-------------------------------");
    }
}
