package dao;

public class validation extends Exception {

    public validation(String message) {
        super(message);
    }

    public validation(Throwable t) {
        super(t);
    }


}
