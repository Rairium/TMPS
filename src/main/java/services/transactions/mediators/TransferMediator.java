package services.transactions.mediators;

import documents.BankDocument;
import documents.CreditDocument;
import documents.TransferDocument;
import persons.clients.Client;
import services.transactions.CreditTransaction;
import services.transactions.DepositTransaction;
import services.transactions.Transaction;
import services.transactions.TransferTransaction;

import java.util.ArrayList;

public class TransferMediator implements ServiceMediator {
    private TransferDocument mockDocument = new TransferDocument();
    private ServiceMediator nextServiceMediator;
    private ArrayList<Transaction> transactionStock;
    private ArrayList<Client> clientStock;
    private int createdTransactionId = 0;

    public TransferMediator() {
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
                    System.out.println("the transaction with id = " + transaction.getDocument().getDocumentId() + " already exists ");
                    isInStock = true;
                    break;
                }
            }
            if(!isInStock) {
                TransferTransaction transferTransaction = new TransferTransaction(bankDocument, createdTransactionId, client.getClientId());
                transactionStock.add(transferTransaction);
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
        for (Client client : clientStock) {
            System.out.println("Clients in Transfer Mediator List");
            System.out.println(client.getClientId());
        }
    }

    public ArrayList<Transaction> getTransactionStock() {
        return this.transactionStock;
    }

}
