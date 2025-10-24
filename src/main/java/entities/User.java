package entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class User implements UserDetails { //ho dovuto implementare altrimenti mi dava errore nel tools

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @OneToMany(mappedBy = "organizzatore", cascade = CascadeType.ALL)
    private List<Evento> eventiCreati;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;

    public User() {}

    public User(String nome, String email, String password, Ruolo ruolo,
                List<Evento> eventiCreati, List<Prenotazione> prenotazioni) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.eventiCreati = eventiCreati;
        this.prenotazioni = prenotazioni;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public List<Evento> getEventiCreati() {
        return eventiCreati;
    }

    public void setEventiCreati(List<Evento> eventiCreati) {
        this.eventiCreati = eventiCreati;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.ruolo.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}