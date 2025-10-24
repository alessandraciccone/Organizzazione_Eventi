package payload;

import java.util.UUID;

public record UserResponseDTO (
        UUID id,
        String nome,
        String email,
        String ruolo
){}
