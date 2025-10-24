package controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import payload.NewPrenotazioneDTO;
import payload.PrenotazioneResponseDTO;
import service.PrenotazioneService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")

public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    //metodo post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponseDTO create(@RequestBody NewPrenotazioneDTO dto) {
        return prenotazioneService.create(dto);
    }
//metodo get

    @GetMapping
    public List<PrenotazioneResponseDTO> findAll() {
        return prenotazioneService.findAll();
    }
//metodo get x id

    @GetMapping("/{id}")
    public PrenotazioneResponseDTO findById(@PathVariable UUID id) {
        return prenotazioneService.findById(id);
    }

    //metodo delete

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        prenotazioneService.delete(id);
    }


}
