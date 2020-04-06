package services.transactions;

import documents.BankDocument;

public class TransferTransaction extends Transaction{
    public TransferTransaction(BankDocument document, int transactionId, int clientId) {
        super(document, transactionId, clientId);
    }
}
