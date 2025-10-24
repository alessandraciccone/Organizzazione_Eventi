package repositories;


import entities.Evento;
import entities.Prenotazione;
import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
List<Prenotazione> findByUtente(User utente);
List<Prenotazione> findByEvento(Evento evento);

}
