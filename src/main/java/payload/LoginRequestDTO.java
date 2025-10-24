package payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message="email obbligatoria!")
        @Email(message="formato email non valido")
        String email,

        @NotBlank(message="password obbligatoria!")
        String password
){}
