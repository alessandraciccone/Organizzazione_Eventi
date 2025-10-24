package payload;

import java.util.UUID;

public record LoginResponseDTO(

        String token,
        UUID userID,
        String nome,
        String email,
        String ruolo
){}
