package services.transactions;

import documents.BankDocument;

public abstract class Transaction {
    private BankDocument document;
    private int transactionId;
    private int clientId;

    public Transaction(BankDocument document, int transactionId, int clientId) {
        this.document = document;
        this.transactionId = transactionId;
        this.clientId = clientId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public BankDocument getDocument() {
        return document;
    }

    public int getClientId(){
        return clientId;
    }
}
