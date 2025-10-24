package payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

        @NotBlank (message="il nome è obbligatorio")
        @Size(min=2, max=30, message="Il nome deve avere massimo 30 caratteri")
        String nome,

        @NotBlank(message="email obbligatoria!")
        @Email(message="formato email non valido")
        String email,

        @NotBlank(message="password obbligatoria")
        @Size(min=8, message="la password deve avere almeno 8 caratteri")
        String password

){}
