package exceptions;

public class EventoSoldOutException extends RuntimeException {
    public EventoSoldOutException(String titoloEvento){
        super("l'Evemto  "+ titoloEvento + "è al completo");
    }
}
