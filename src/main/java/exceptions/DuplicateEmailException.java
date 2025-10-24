package exceptions;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String emaill){
        super("Esiste già un utente registrato con questa email");

    }
}
