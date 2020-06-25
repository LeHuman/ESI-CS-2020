package final_project;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * {@code Client} is a class that represents a buyer.
 * <p>
 * orders are stored using the {@code Order} class
 */
public class Client {
    private static long ID_COUNTER = 0;
    /**
     * Stores the client's unique id
     */
    private long id;
    /**
     * Stores the client's name
     */
    private String name;
    /**
     * Stores the date that the client first ordered something using a
     * {@code LocalDate}
     */
    private LocalDate joinedDate;
    /**
     * Stores the clients orders using the {@code Order} class
     */
    private HashMap<Long, Order> orders = new HashMap<Long, Order>();

    /**
     * Constructs a new instance of a client
     * 
     * @param name the name of the new client
     */
    public Client(String name) {
        this.name = name;
        this.id = ++ID_COUNTER;
        this.joinedDate = LocalDate.now();
    }

    /**
     * Gets the name field.
     * <p>
     * This method returns the {@code String} value for the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the joinedDate field.
     * <p>
     * This method returns the {@code LocalDate} value for the joinedDate.
     * 
     * @return the local date, not null
     */
    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    /**
     * Creates a new {@code Order}.
     * <p>
     * This method creates a new {@code Order} that gets stored in the
     * {@code orders} Map.
     * 
     * @param productID  the alphanumeric ID of the product
     * @param amount     the amount of the product being ordered
     * @param year       the year that the product should be delivered
     * @param month      the month that the product should be delivered
     * @param dayOfMonth the day of the month that the product should be delivered
     * @return the ID of the order
     * @throws DateTimeException if the value of any field is out of range, or if
     *                           the day-of-month is invalid for the month-year
     */
    public long newOrder(String productID, int amount, int year, int month, int dayOfMonth) {
        LocalDate deliverDate = LocalDate.of(year, month, dayOfMonth);
        Order order = new Order(productID, amount, deliverDate);
        orders.put(order.getID(), order);
        return order.getID();
    }

    /**
     * Retrieves an {@code Order}.
     * <p>
     * This method returns the {@code Order} with the matching {@code orderID}.
     * 
     * @param orderID the ID of the order
     * @return the matching order, not null
     * @throws OrderDoesNotExistException if no matching {@code Order} is found
     */
    public Order getOrder(long orderID) throws OrderDoesNotExistException {
        if (!orders.containsKey(orderID))
            throw new OrderDoesNotExistException(orderID);
        return orders.get(orderID);
    }

    /**
     * Gets the id field.
     * <p>
     * This methods returns the {@code long} value for the id.
     * 
     * @return the id
     */
    public long getID() {
        return id;
    }

    /**
     * Outputs this client as a {@code String}.
     * <p>
     * This method returns a {@code String} representing this client and any orders
     * that it contains.
     *
     * @return a string representation of this client, not null
     */
    @Override
    public String toString() {
        String str = "Name: " + getName() + "\nID: " + getID() + "\nJoined: " + getJoinedDate().toString() + "\n";
        for (HashMap.Entry<Long, Order> order : orders.entrySet()) {
            str += ("\n" + order.getValue().toString()).replace("\n", "\n  ") + "\n";
        }
        return str;
    }

}