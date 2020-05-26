package persons.clients;

import services.transactions.mediators.ServiceMediator;

public class SimpleClient extends Client {

    public SimpleClient(ServiceMediator newMediator) {
        super(newMediator);
    }
}