package services.transactions;

import documents.BankDocument;

public class CreditTransaction extends Transaction {

    public CreditTransaction(BankDocument document, int transactionId, int clientId) {
        super(document, transactionId, clientId);
    }
}
