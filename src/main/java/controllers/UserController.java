package controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import payload.NewUserDTO;
import payload.UpdateUserDTO;
import payload.UserResponseDTO;
import service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //metodo post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(@RequestBody NewUserDTO dto) {
        return userService.create(dto);
    }

    //metodo get
    @GetMapping
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    //metodo  get id

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    //metodo put
    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable UUID id, @RequestBody UpdateUserDTO dto) {
        return userService.update(id, dto);
    }

    //metodo delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

}