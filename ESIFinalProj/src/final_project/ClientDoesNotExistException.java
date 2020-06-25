package final_project;

public class ClientDoesNotExistException extends Exception {

    private static final long serialVersionUID = 2383130794486129488L;

    public ClientDoesNotExistException(long badID) {
        super("Client" + badID + "does not exist");
    }

}