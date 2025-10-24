package payload;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewPrenotazioneDTO(
        @NotNull(message="L'utente è obbligatorio")
        UUID utenteId,
        @NotNull(message="L'evento è obbligatorio")
        UUID eventoId
)

{}
