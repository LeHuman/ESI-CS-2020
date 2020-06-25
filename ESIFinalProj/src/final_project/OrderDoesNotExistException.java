package final_project;

public class OrderDoesNotExistException extends Exception {

    private static final long serialVersionUID = 2383130794486129488L;

    public OrderDoesNotExistException(long badID) {
        super("Order" + badID + "does not exist");
    }

}