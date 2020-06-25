package final_project;

public interface Supervisor {

    public Client newClient(String clientID);

    public long newOrder(String clientID, String productID, int amount, int year, int month, int dayOfMonth);

    public Client getClient(String clientID) throws ClientDoesNotExistException;

    public Order getOrder(String clientID, long id) throws OrderDoesNotExistException;

}