package payload;

import java.time.LocalDate;
import java.util.UUID;

public record EventoResponseDTO (

        UUID id,
        String titolo,
        String descrizione,
        String luogo,
        LocalDate data,
        int postiDisponibili,
        String organizzatore
){}
