package account.Profile;

import persons.clients.Client;


import java.util.ArrayList;

public class ClientList {
    private static ClientList clientList = null;
    private ArrayList<Client> clients = new ArrayList<Client>();

    private ClientList() {

    }

    public static ClientList getInstance() {
        if (clientList == null)
            clientList = new ClientList();
        return clientList;
    }

    public void setClientInList(Client client) {
        if (!clients.contains(client))
            this.clients.add(client);
    }

    public ArrayList<Client> getClientList() {
        return clients;
    }
}
