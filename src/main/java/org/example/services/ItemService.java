package org.example.services;

import org.example.entities.Item;
import org.example.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    // Inyección de dependencias por constructor
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Listar todos los artículos del almacén
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    // Guardar o actualizar un artículo
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    // Buscar un artículo por ID
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    // Buscar un artículo por su código SKU único
    public Item findBySku(String sku) {
        return itemRepository.findBySku(sku);
    }

    // Eliminar un artículo por ID
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
