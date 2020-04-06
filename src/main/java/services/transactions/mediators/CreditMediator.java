package services.transactions.mediators;

import documents.BankDocument;
import documents.CreditDocument;
import persons.clients.Client;
import services.transactions.CreditTransaction;
import services.transactions.Transaction;

import java.util.ArrayList;

public class CreditMediator implements ServiceMediator {
    private CreditDocument mockDocument = new CreditDocument();
    private ServiceMediator nextServiceMediator;
    private ArrayList<Transaction> transactionStock;
    private ArrayList<Client> clientStock;
    private int createdTransactionId = 0;

    public CreditMediator() {
        transactionStock = new ArrayList<Transaction>();
        clientStock = new ArrayList<Client>();
    }

    public void setNextMediator(ServiceMediator nextMediator) {
        nextServiceMediator = nextMediator;
    }

    public void createTransaction(BankDocument bankDocument, Client client) {
        if (verifyClass(bankDocument)) {
            addClient(client);
            boolean isInStock = false;
            for (Transaction transaction : transactionStock) {
                if (transaction.getDocument().getDocumentId() == bankDocument.getDocumentId()) {
                    System.out.println("the transaction with such document id = " + transaction.getDocument().getDocumentId() + " already exists ");
                    isInStock = true;
                    break;
                }
            }
            if(!isInStock) {
                CreditTransaction creditTransaction = new CreditTransaction(bankDocument, createdTransactionId, client.getClientId());
                transactionStock.add(creditTransaction);
                System.out.println("the transaction with id = " + createdTransactionId + " was added by client with id =  " + client.getClientId());
                createdTransactionId++;
            }
        } else {
            nextServiceMediator.createTransaction(bankDocument, client);
        }
    }

    public void confirmTransaction(BankDocument bankDocument, int workerId) {
        if (bankDocument.getClass() == mockDocument.getClass()) {
            boolean isApproved = false;
            for (Transaction transaction : transactionStock) {
                if (transaction.getDocument().getDocumentId() == bankDocument.getDocumentId()) {
                    System.out.println("Transaction with id = " + transaction.getTransactionId() + " with document id = " + transaction.getDocument().getDocumentId() + " of client with id = " + transaction.getClientId() + " was approved by worker with id = " + workerId);
                    transactionStock.remove(transaction);
                    isApproved = true;
                }
                if (isApproved){
                    break;
                }
            }
        } else nextServiceMediator.confirmTransaction(bankDocument, workerId);
    }

    public boolean verifyClass(BankDocument bankDocument) {
        return bankDocument.getClass() == this.mockDocument.getClass();
    }


    public void addClient(Client client) {
        if (!clientStock.contains(client))
            clientStock.add(client);
    }

    public void getAllClients() {
        System.out.println("Clients in Credit Mediator List");
        for (Client client : clientStock) {
            System.out.println(client.getClientId());
        }
    }

    public void getAllTransactions() {
        System.out.println("Transactions in Credit Mediator List");
        for (Transaction transaction : transactionStock) {
            System.out.println("transaction id = " + transaction.getTransactionId() + " client id = " + transaction.getClientId()+ " document id = " + transaction.getDocument().getDocumentId());
        }
    }

    public ArrayList<Transaction> getTransactionStock() {
        return this.transactionStock;
    }
}

