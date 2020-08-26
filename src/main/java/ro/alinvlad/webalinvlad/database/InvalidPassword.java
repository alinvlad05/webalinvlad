package ro.alinvlad.webalinvlad.database;

public class InvalidPassword extends Exception {

    public InvalidPassword(String message) {
        super(message);
    }
}
