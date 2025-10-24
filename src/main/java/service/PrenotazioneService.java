package service;

import entities.Evento;
import entities.Prenotazione;
import entities.User;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import payload.NewPrenotazioneDTO;
import payload.PrenotazioneResponseDTO;
import repositories.EventoRepository;
import repositories.PrenotazioneRepository;
import repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final UserRepository userRepository;
    private final EventoRepository eventoRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository,
                               UserRepository userRepository,
                               EventoRepository eventoRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.userRepository = userRepository;
        this.eventoRepository = eventoRepository;
    }
//salvo nuova prenotazio ne
    public PrenotazioneResponseDTO create(NewPrenotazioneDTO dto) {
        User user = userRepository.findById(dto.utenteId())
                .orElseThrow(() -> new NotFoundException("User", dto.utenteId()));
        Evento evento = eventoRepository.findById(dto.eventoId())
                .orElseThrow(() -> new NotFoundException("Evento", dto.eventoId()));

        if (evento.getPostiDisponibili() <= 0) {
            throw new BadRequestException("L'evento è al completo");
        }

        // controllo doppia prenotazione
        if (prenotazioneRepository.findByUtenteAndEvento(user, evento).isPresent()) {
            throw new BadRequestException("Hai già prenotato questo evento");
        }

        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);

        Prenotazione prenotazione = new Prenotazione(user, evento, LocalDateTime.now());
        Prenotazione saved = prenotazioneRepository.save(prenotazione);

        return new PrenotazioneResponseDTO(saved.getId(), user.getId(), evento.getId(), saved.getTimestamp());
    }
//cerco tutte prenotazione
    public List<PrenotazioneResponseDTO> findAll() {
        return prenotazioneRepository.findAll().stream()
                .map(p -> new PrenotazioneResponseDTO(
                        p.getId(),
                        p.getUtente().getId(),
                        p.getEvento().getId(),
                        p.getTimestamp()
                ))
                .toList();
    }
//cerco prenotazioni x id
    public PrenotazioneResponseDTO findById(UUID id) {
        Prenotazione p = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione", id));
        return new PrenotazioneResponseDTO(p.getId(), p.getUtente().getId(), p.getEvento().getId(), p.getTimestamp());
    }
//cancello prenotazioni
    public void delete(UUID id) {
        Prenotazione existing = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione", id));
        prenotazioneRepository.delete(existing);
    }
}
