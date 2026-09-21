package org.example.entities;

import jakarta.persistence.*;
import org.example.enums.ZoneStatus;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String locationCode; // Ej: "001-001-01" corresponde a número de estantería-posición de rack- nivel de altura

    private String zone;         // Ej: "PICKING", "ESTANTERIA", "P03"

    private String aisle;        // Pasillo (Ej: "016")

    private Integer rackPosition;// Posición en estantería (Ej: 104)

    private Integer level;       // Altura/Nivel (Ej: 01)

    private boolean available = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ZoneStatus zoneStatus;

    public Location() {
    }

    public Location(String locationCode, String zone, String aisle, Integer rackPosition, Integer level, boolean available, ZoneStatus zoneStatus) {
        this.locationCode = locationCode;
        this.zone = zone;
        this.aisle = aisle;
        this.rackPosition = rackPosition;
        this.level = level;
        this.available = available;
        this.zoneStatus = zoneStatus;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public Integer getRackPosition() {
        return rackPosition;
    }

    public void setRackPosition(Integer rackPosition) {
        this.rackPosition = rackPosition;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ZoneStatus getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(ZoneStatus zoneStatus) {
        this.zoneStatus = zoneStatus;
    }
}