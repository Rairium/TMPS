import account.Account;
import account.AccountCreator;
import account.AccountData;
import account.Profile.ClientList;
import account.Profile.SimpleProfile;
import gui.MainFrame;
import locations.Location;
import persons.clients.SimpleClient;
import services.transactions.mediators.CreditMediator;
import services.transactions.mediators.DepositMediator;
import services.transactions.mediators.InitialMediator;
import services.transactions.mediators.TransferMediator;

import javax.swing.*;

public class StartGUI {
    public static void main(String[] args) {

        final InitialMediator initialMediator = new InitialMediator();
        CreditMediator creditMediator = new CreditMediator();
        DepositMediator depositMediator = new DepositMediator();
        TransferMediator transferMediator = new TransferMediator();
        initialMediator.setNextMediator(creditMediator);
        creditMediator.setNextMediator(depositMediator);
        depositMediator.setNextMediator(transferMediator);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(initialMediator, ClientList.getInstance());
            }
        });

    }
}
