package services.transactions.mediators;

import documents.BankDocument;
import persons.clients.Client;

public interface ServiceMediator {
    void setNextMediator(ServiceMediator nextMediator);
    void createTransaction(BankDocument document, Client client);
    void confirmTransaction(BankDocument bankDocument, int workerId);
    boolean verifyClass(BankDocument bankDocument);
}

