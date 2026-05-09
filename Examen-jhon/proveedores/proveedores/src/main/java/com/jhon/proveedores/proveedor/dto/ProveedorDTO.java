package com.jhon.proveedores.proveedor.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProveedorDTO {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String pais;
    private Boolean activo;
    private LocalDate fechaRegistro;

    public static ProveedorDTO fromEntity(
            com.jhon.proveedores.proveedor.entity.Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setEmail(proveedor.getEmail());
        dto.setTelefono(proveedor.getTelefono());
        dto.setDireccion(proveedor.getDireccion());
        dto.setPais(proveedor.getPais());
        dto.setActivo(proveedor.getActivo());
        dto.setFechaRegistro(proveedor.getFechaRegistro());
        return dto;
    }

    public com.jhon.proveedores.proveedor.entity.Proveedor toEntity() {
        com.jhon.proveedores.proveedor.entity.Proveedor proveedor =
                new com.jhon.proveedores.proveedor.entity.Proveedor();
        proveedor.setId(this.id);
        proveedor.setNombre(this.nombre);
        proveedor.setEmail(this.email);
        proveedor.setTelefono(this.telefono);
        proveedor.setDireccion(this.direccion);
        proveedor.setPais(this.pais);
        proveedor.setActivo(this.activo);
        proveedor.setFechaRegistro(this.fechaRegistro);
        return proveedor;
    }
}