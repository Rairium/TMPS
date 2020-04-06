package persons.clients;

import services.transactions.mediators.ServiceMediator;

public class MicrosoftCompany extends Client {

    public MicrosoftCompany(ServiceMediator newMediator) {
        super(newMediator);
        System.out.println("Concrete Client was created");
    }
}
