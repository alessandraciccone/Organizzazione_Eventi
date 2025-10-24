package payload;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventoDTO(
        @NotBlank(message="il titolo è obbligatorio")
        String titolo,
        @NotBlank(message="la descrizione è obbligatoia")
        String descrizione,
        @NotBlank(message="il luogo è obbligatorio")
        String luogo,
        @NotNull(message="la data è obbligatoria!")
        @Future( message="La data deve essere futura")
        LocalDate data,
        @Min(value=1, message ="i posti disponibili devono essere almeno uno")
        int postiDisponibili,
        @NotNull(message="L'organizzatore è obbligatorio")
        UUID organizzatoreID
){
    public UUID organizzatoreId() {
    }
}
