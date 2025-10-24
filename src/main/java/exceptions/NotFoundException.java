package exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName, UUID id){
        super(entityName +"con id" + id + "non è stato trovato");
    }
    public NotFoundException(String message){
        super (message);
    }
}
