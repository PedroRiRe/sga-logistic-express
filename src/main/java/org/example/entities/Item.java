package org.example.entities;

import jakarta.persistence.*;
import org.example.enums.Company;
import org.example.enums.ItemStatus;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sku; // Código de referencia único del producto (ej: "SKU-98765")

    @Column(nullable = false)
    private String name; // Nombre del artículo

    private String description; // Descripción detallada

    private int stockQuantity; // Cantidad disponible en stock

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemStatus status = ItemStatus.DISPONIBLE; // Valor por defecto

    // NUEVO: Asociación con la compañía propietaria de la mercancía
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Company company;

    public Item() {
    }

    public Item(String sku, String name, String description, int stockQuantity, Company company) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.company = company;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}