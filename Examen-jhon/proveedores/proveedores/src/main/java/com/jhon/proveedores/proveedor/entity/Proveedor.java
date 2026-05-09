package com.jhon.proveedores.proveedor.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String pais;
    private Boolean activo;
    private LocalDate fechaRegistro;
}