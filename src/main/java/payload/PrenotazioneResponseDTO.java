package payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record PrenotazioneResponseDTO (

        UUID id,
        UUID utenteID,
        UUID eventoID,
        LocalDateTime timestamp
){}