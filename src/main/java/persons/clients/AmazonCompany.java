package persons.clients;

import services.transactions.mediators.ServiceMediator;

public class AmazonCompany extends Client {

    public AmazonCompany(ServiceMediator newMediator) {
        super(newMediator);
        System.out.println("Concrete Client was created");
    }
}
