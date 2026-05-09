package com.jhon.productos.producto;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String categoria;
    private BigDecimal precio;
    private Integer stock;
    private String marca;
    private Boolean activo;
    private LocalDate fechaIngreso;
}