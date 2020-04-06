package services.transactions;

import documents.BankDocument;

public class DepositTransaction extends Transaction {

    public DepositTransaction(BankDocument document, int transactionId, int clientId) {
        super(document, transactionId, clientId);
    }
}
