package payload;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String nome,
        String email,
        String ruolo,
        Collection<? extends GrantedAuthority> authorities
) {}


