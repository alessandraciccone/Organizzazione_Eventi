package payload;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateEventoDTO(

        @Size(min=2, max=100, message="il titolo deve avere massimo 100 caratteri!")
        String titolo,
        @Size(min=5, message="La dscrizione deve avere almeno 5 caratteri!")
        String descrizione,
        String luogo,
        @Future(message="La data deve essere futura!")
        LocalDate data,
        @Min(value=1, message="i posti disponibili devono essere almeno uno")
        Integer postiDisponibili

){}
