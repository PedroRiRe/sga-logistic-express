package org.example.controllers;

import org.example.entities.User;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Inyección de dependencias por constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Listar todos los usuarios del sistema
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // Buscar un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo usuario (Administrador u Operario)
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    // Eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
