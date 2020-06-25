package final_project;

import java.time.LocalDate;

/**
 * {@code Order} is a class that represents an order.
 * <p>
 * orders are used to represent purchases made by a {@code Client}
 */
public class Order {
    private static long ID_COUNTER = 0;
    private long id;
    private String productID;
    private int amount;
    private LocalDate deliverDate;

    /**
     * Constructs a new instance of an order
     * 
     * @param productID the ID of the product being purchased
     * @param amount the amount of the product to be delivered
     * @param deliverDate the date that the product should be delivered
     */
    public Order(String productID, int amount, LocalDate deliverDate) {
        this.id = ++ID_COUNTER;
        this.productID = productID;
        this.amount = amount;
        this.deliverDate = deliverDate;
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
     * Gets the productID field.
     * <p>
     * This methods returns the {@code String} value for the productID.
     * 
     * @return the product ID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Gets the amount field.
     * <p>
     * This method returns the {@code int} value for the amount of the product to be
     * shipped.
     * 
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gets the deliverDate field.
     * <p>
     * This method returns the {@code LocalDate} value of deliverDate.
     * 
     * @return LocalDate
     */
    public LocalDate getDeliverDate() {
        return deliverDate;
    }

    /**
     * Outputs this order as a {@code String}.
     * <p>
     * This method returns a {@code String} representing this order.
     *
     * @return a string representation of this order, not null
     */
    @Override
    public String toString() {
        String str = "Product: " + getProductID();
        str += "\nAmount: " + getAmount();
        str += "\nDelivery Date: " + getDeliverDate().toString();
        str += "\nOrder ID: " + getID();
        return str;
    }

}