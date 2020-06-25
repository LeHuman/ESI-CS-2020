package final_project;

import java.util.HashMap;

public class OrderManager implements Supervisor {

    private HashMap<String, Client> clients = new HashMap<String, Client>();

    public Client newClient(String clientID) {
        Client client = new Client(clientID);
        clients.put(clientID, client);
        return client;
    }

    public long newOrder(String clientID, String productID, int amount, int year, int month, int dayOfMonth) {
        Client client;
        try {
            client = getClient(clientID);
        } catch (ClientDoesNotExistException e) {
            client = newClient(clientID);
        }
        return client.newOrder(productID, amount, year, month, dayOfMonth);
    }

    public Client getClient(String clientID) throws ClientDoesNotExistException {
        return clients.get(clientID);
    }

    public Order getOrder(String clientID, long id) throws OrderDoesNotExistException {
        return clients.get(clientID).getOrder(id);
    }

    @Override
    public String toString() {
        String str = "- - - - -  Clients  - - - - -\n";
        for (HashMap.Entry<String, Client> client : clients.entrySet()) {
            str += client.getValue().toString();
            str += "- - - - - - - - - - - - - - -\n";
        }
        return str;
    }

}
