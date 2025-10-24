package service;


import entities.User;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import payload.NewUserDTO;
import payload.UpdateUserDTO;
import payload.UserResponseDTO;
import repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional // uso transactionalx gestire le transazioni con il db in modo automatico
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //crea nuovo utente

    public UserResponseDTO create(NewUserDTO dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new BadRequestException("Esiste già un utente con questa email");
        }

        User user = new User(dto.nome(), dto.email(), dto.password(), dto.ruolo(), null, null);
        User saved = userRepository.save(user);
        return new UserResponseDTO(saved.getId(), saved.getNome(), saved.getEmail(), saved.getRuolo().name());

    }
    //cerca utenti

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream().map(u->new UserResponseDTO(u.getId(),u.getNome(),u.getEmail(), u.getRuolo().name()
        )).toList();
    }

    //cerco utente x id
    public UserResponseDTO findById(UUID id){
        User u= userRepository.findById(id).orElseThrow(()->new NotFoundException("user", id));
        return new UserResponseDTO(u.getId(),u.getNome(),u.getEmail(),u.getRuolo().name());
    }
    //modifica utente

    public UserResponseDTO update (UUID id, UpdateUserDTO dto){
        User existing= userRepository.findById(id).orElseThrow(()->new NotFoundException("User", id));
        if(dto.nome()!=null)existing.setNome(dto.nome());
        if(dto.email()!=null)existing.setEmail(dto.email());
        if(dto.password()!=null)existing.setPassword(dto.password());
        User saved= userRepository.save(existing);
        return new UserResponseDTO(saved.getId(), saved.getNome(), saved.getEmail(), saved.getRuolo().name());
    }
    //cancella utente

    public void delete(UUID id){
        User existing= userRepository.findById(id).orElseThrow(()->new NotFoundException("user", id));
        userRepository.delete(existing);
    }


}