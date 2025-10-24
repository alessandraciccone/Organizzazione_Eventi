package controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import payload.EventoResponseDTO;
import payload.NewEventoDTO;
import payload.UpdateEventoDTO;
import service.EventoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")

public class EventoController {

    private final EventoService eventoService;
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;}

//metodo post

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoResponseDTO create(@RequestBody NewEventoDTO dto) {
        return eventoService.create(dto);
    }


    //metodo get
    @GetMapping
    public List<EventoResponseDTO> findAll() {
        return eventoService.findAll();
    }
//metodo get id
@GetMapping("/{id}")
public EventoResponseDTO findById(@PathVariable UUID id) {
    return eventoService.findById(id);
}

//metodo put

    @PutMapping("/{id}")
    public EventoResponseDTO update(@PathVariable UUID id, @RequestBody UpdateEventoDTO dto) {
        return eventoService.update(id, dto);
    }
//metodo delete
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable UUID id) {
    eventoService.delete(id);
}

}
