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
    private String locationCode;

    private String zone;

    // --- CAMBIAMOS A UN SOLO CAMPO 'available' ---
    private boolean available = true;

    // --- CAMPO ENUM ---
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ZoneStatus zoneStatus;

    public Location() {
    }

    public Location(String locationCode, String zone, ZoneStatus zoneStatus) {
        this.locationCode = locationCode;
        this.zone = zone;
        this.zoneStatus = zoneStatus;
        this.available = true;
    }

    // Getters y Setters
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