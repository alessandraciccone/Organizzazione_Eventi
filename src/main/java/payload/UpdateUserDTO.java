package payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO (
        @Size(min=2, max=30, message="il nome deve avere una lunghezza di massimo 30 caratteri")
        String nome,
        @Email(message="L'indirizzo email non è valido")
        String email,
        @Size(min=8, message="La password deve avere minimo 8 caratteri")
String password
){}
