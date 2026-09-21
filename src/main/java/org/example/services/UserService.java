package org.example.services;

import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Inyección de dependencias por constructor (recomendado en Spring)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Listar todos los usuarios del sistema
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Guardar o actualizar un usuario
    public User save(User user) {
        return userRepository.save(user);
    }

    // Buscar un usuario por ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Buscar un usuario por su nombre de usuario
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Eliminar un usuario por ID
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
