package persons;

import documents.BankDocument;
import services.transactions.mediators.ServiceMediator;

public class Worker {
    private ServiceMediator mediator;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    private int workerId;
    public Worker(ServiceMediator newMediator){
        mediator = newMediator;
    }
    public void confirmTransaction(BankDocument bankDocument){
        mediator.confirmTransaction(bankDocument, this.workerId);
    }
}
