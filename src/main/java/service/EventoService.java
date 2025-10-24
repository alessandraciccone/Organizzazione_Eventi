package service;


import entities.Evento;
import entities.User;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import payload.EventoResponseDTO;
import payload.NewEventoDTO;
import payload.UpdateEventoDTO;
import repositories.EventoRepository;
import repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EventoService {
    private final EventoRepository eventoRepository;
    private final UserRepository userRepository;

    public EventoService(EventoRepository eventoRepository, UserRepository userRepository){
        this.eventoRepository= eventoRepository;
        this.userRepository=userRepository;
    }

    //crea nuovo evento

    public EventoResponseDTO create(NewEventoDTO dto) {
        if (dto.data().isBefore(LocalDate.now())) {
            throw new BadRequestException("Non puoi creare un evento con data passata");
        }
        User organizzatore = userRepository.findById(dto.organizzatoreId())
                .orElseThrow(() -> new NotFoundException("User", dto.organizzatoreId()));

        Evento evento = new Evento(dto.titolo(), dto.descrizione(), dto.luogo(),
                dto.data(), dto.postiDisponibili(), organizzatore, null);

        Evento saved = eventoRepository.save(evento);
        return new EventoResponseDTO(saved.getId(), saved.getTitolo(), saved.getDescrizione(),
                saved.getLuogo(), saved.getData(), saved.getPostiDisponibili(), organizzatore.getNome());
    }


    //trova tutti
    public List<EventoResponseDTO> findAll() {
        return eventoRepository.findAll().stream()
                .map(e -> new EventoResponseDTO(e.getId(), e.getTitolo(), e.getDescrizione(),
                        e.getLuogo(), e.getData(), e.getPostiDisponibili(), e.getOrganizzatore().getNome()))
                .toList();
    }

    //trova x id
    public EventoResponseDTO findById(UUID id) {
        Evento e = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento", id));
        return new EventoResponseDTO(e.getId(), e.getTitolo(), e.getDescrizione(),
                e.getLuogo(), e.getData(), e.getPostiDisponibili(), e.getOrganizzatore().getNome());
    }

    //modifica evento
    public EventoResponseDTO update(UUID id, UpdateEventoDTO dto) {
        Evento existing = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento", id));

        if (dto.titolo() != null) existing.setTitolo(dto.titolo());
        if (dto.descrizione() != null) existing.setDescrizione(dto.descrizione());
        if (dto.luogo() != null) existing.setLuogo(dto.luogo());
        if (dto.data() != null) existing.setData(dto.data());
        if (dto.postiDisponibili() != null) existing.setPostiDisponibili(dto.postiDisponibili());

        Evento saved = eventoRepository.save(existing);
        return new EventoResponseDTO(saved.getId(), saved.getTitolo(), saved.getDescrizione(),
                saved.getLuogo(), saved.getData(), saved.getPostiDisponibili(), saved.getOrganizzatore().getNome());
    }
//cancella
    public void delete(UUID id) {
        Evento existing = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento", id));
        eventoRepository.delete(existing);
    }
}



