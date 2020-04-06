package services.transactions.mediators;

import documents.BankDocument;
import persons.clients.Client;

public class InitialMediator implements ServiceMediator {
    private ServiceMediator nextServiceMediator;
    public void setNextMediator(ServiceMediator nextMediator) {
        nextServiceMediator = nextMediator;
    }

    public void createTransaction(BankDocument bankDocument, Client client) {
        nextServiceMediator.createTransaction(bankDocument, client);
    }

    public void confirmTransaction(BankDocument bankDocument, int workerId) {
        nextServiceMediator.confirmTransaction(bankDocument, workerId);
    }

    public boolean verifyClass(BankDocument bankDocument) {
        return false;
    }
}
