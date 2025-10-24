package entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Prenotazione {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="utente_id")
    private User utente;

    @ManyToOne
    @JoinColumn(name="evento_id")
    private Evento evento;

    private LocalDateTime timestamp;//uso timestamp per registrare il momento esatto della prenotazione


    public Prenotazione(){};

    public Prenotazione( User utente, Evento evento, LocalDateTime timestamp) {
        this.utente = utente;
        this.evento = evento;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }



    public User getUtente() {
        return utente;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
