package exceptions;

import java.util.List;


public class ValidationException extends RuntimeException {
    private final List<String> errorsMessages;

    public ValidationException(List<String> errorsMessages) {
        super("Ci sono stati errori di validazione");
        this.errorsMessages = errorsMessages;
    }

    public List<String> getErrorsMessages() {
        return errorsMessages;
    }
}




