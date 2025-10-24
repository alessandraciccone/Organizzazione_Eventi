package payload;

import java.util.UUID;

public record RegisterResponseDTO (

       UUID id,
       String nome,
       String email,
       String ruolo

){}
