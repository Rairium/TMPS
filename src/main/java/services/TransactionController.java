package services;

import documents.BankDocument;
import org.jetbrains.annotations.NotNull;
import persons.clients.Client;
import services.transactions.Transaction;

import java.util.ArrayList;

public class TransactionController {
    private Client client;
    public TransactionController(Client client){
        this.client = client;
    }

    public void createTransaction(BankDocument bankDocument){
        client.getMediator().createTransaction(bankDocument, this.client);
    }

    public void viewTransactionList(@NotNull ArrayList<Transaction> transactions) {
        for (Transaction transaction: transactions) {
            if (transaction.getClientId() == this.client.getClientId())
                System.out.println("transaction id = " + transaction.getTransactionId() + " document id = " + transaction.getDocument().getDocumentId());
        }
    }
}
