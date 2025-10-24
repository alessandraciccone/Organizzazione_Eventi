package payload;

import entities.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record NewUserDTO(
        @NotBlank(message = "Nome user obbligatorio")
        @Size(min = 2, max = 30, message = "Il nome deve avere una lunghezza massima di 30 caratteri")
        String nome,

        @NotBlank(message = "Email obbligatoria!")
        @Email(message = "L'indirizzo email è nel formato errato")
        String email,

        @NotBlank(message = "Password obbligatoria!")
        @Size(min = 8, message = "La password deve avere minimo 8 caratteri!")
        String password,
  @NotNull(message = "Il ruolo è obbligatorio")
                Ruolo ruolo
){}

