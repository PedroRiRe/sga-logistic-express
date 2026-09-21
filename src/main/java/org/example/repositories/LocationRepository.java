package org.example.repositories;

import org.example.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    // Buscar ubicación exacta por su código en el plano (ej. "016-036-01")
    Optional<Location> findByLocationCode(String locationCode);

    // Buscar todas las ubicaciones de un pasillo específico (ej. "016")
    List<Location> findByAisle(String aisle);

    // Buscar todas las ubicaciones de una zona específica (ej. "ZAR", "MAD")
    List<Location> findByZone(String zone);
}