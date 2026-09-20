package org.example.services;

import org.example.entities.Location;
import org.example.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    // Inyección de dependencias por constructor (recomendado en Spring)
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Listar todas las ubicaciones del almacén
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    // Guardar o actualizar una ubicación
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    // Buscar una ubicación por ID
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    // Eliminar una ubicación por ID
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
