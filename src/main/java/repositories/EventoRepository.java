package repositories;


import entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {

    List<Evento> findByLuogo(String luogo);
    List<Evento> findByData(LocalDate data);
}
