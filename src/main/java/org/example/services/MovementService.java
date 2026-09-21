package org.example.services;

import org.example.entities.Item;
import org.example.entities.Movement;
import org.example.enums.ItemStatus;
import org.example.enums.MovementType;
import org.example.repositories.ItemRepository;
import org.example.repositories.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final ItemRepository itemRepository;

    public MovementService(MovementRepository movementRepository, ItemRepository itemRepository) {
        this.movementRepository = movementRepository;
        this.itemRepository = itemRepository;
    }

    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    public Optional<Movement> getMovementById(Long id) {
        return movementRepository.findById(id);
    }

    @Transactional
    public Movement saveMovement(Movement movement) {
        Item item = itemRepository.findById(movement.getItem().getId())
                .orElseThrow(() -> new RuntimeException("Item no encontrado con ID: " + movement.getItem().getId()));

        // REGLA DE NEGOCIO: Si el ítem está RESERVADO, no se permite un traslado interno
        if (item.getStatus() == ItemStatus.RESERVADO) {
            if (movement.getMovementType() == MovementType.TRASLADO_INTERNO) {
                throw new RuntimeException("Operación denegada: La mercancía está RESERVADA y no puede cambiar de ubicación interna. Solo se permite salida.");
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
