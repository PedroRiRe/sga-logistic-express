package org.example.services;

import org.example.entities.Item;
import org.example.entities.Movement;
import org.example.entities.User;
import org.example.enums.Company;
import org.example.enums.ItemStatus;
import org.example.enums.MovementType;
import org.example.enums.UserRole;
import org.example.repositories.ItemRepository;
import org.example.repositories.MovementRepository;
import org.example.repositories.UserRepository; // <-- Asegúrate de importar esto
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository; // <-- Inyectar repositorio de usuarios

    public MovementService(MovementRepository movementRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.movementRepository = movementRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    public Optional<Movement> getMovementById(Long id) {
        return movementRepository.findById(id);
    }

    @Transactional
    public Movement saveMovement(Movement movement) {
        // 1. Buscar el Item
        Item item = itemRepository.findById(movement.getItem().getId())
                .orElseThrow(() -> new RuntimeException("Item no encontrado con ID: " + movement.getItem().getId()));

        // 2. Buscar el Usuario real de la BD para obtener su rol correctamente
        User user = userRepository.findById(movement.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + movement.getUser().getId()));

        movement.setUser(user); // Asignamos el usuario completo y verificado

        // REGLA DE NEGOCIO 1: Si el ítem está RESERVADO, no se permite un traslado interno
        if (item.getStatus() == ItemStatus.RESERVADO) {
            if (movement.getMovementType() == MovementType.TRASLADO_INTERNO) {
                throw new RuntimeException("Operación denegada: La mercancía está RESERVADA y no puede cambiar de ubicación interna. Solo se permite salida.");
            }
        }

        // REGLA DE NEGOCIO 2: Restricción de CIA3 a palés completos (Picking bloqueado para OPERATOR)
        if (item.getCompany() == Company.CIA3 && movement.getMovementType() == MovementType.SALIDA) {
            if (user.getRole() == UserRole.OPERATOR) {
                throw new RuntimeException("Operación denegada: La compañía CIA3 solo permite salidas por palé completo. Los operadores no pueden realizar picking de esta mercancía.");
            }
        }

        // Actualización de stock según el tipo de movimiento
        if (movement.getMovementType() == MovementType.ENTRADA) {
            item.setStockQuantity(item.getStockQuantity() + movement.getQuantity());
        } else if (movement.getMovementType() == MovementType.SALIDA) {
            if (item.getStockQuantity() < movement.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para realizar la salida.");
            }
            item.setStockQuantity(item.getStockQuantity() - movement.getQuantity());
        }

        itemRepository.save(item);
        return movementRepository.save(movement);
    }

    public void deleteMovement(Long id) {
        movementRepository.deleteById(id);
    }
}
