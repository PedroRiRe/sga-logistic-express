package org.example.repositories;

import org.example.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Método personalizado para buscar un artículo por su SKU único
    Item findBySku(String sku);
}