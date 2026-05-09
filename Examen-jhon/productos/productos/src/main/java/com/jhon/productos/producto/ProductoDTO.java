package com.jhon.productos.producto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private BigDecimal precio;
    private Integer stock;
    private String marca;
    private Boolean activo;
    private LocalDate fechaIngreso;

    public static ProductoDTO fromEntity(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCategoria(producto.getCategoria());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setMarca(producto.getMarca());
        dto.setActivo(producto.getActivo());
        dto.setFechaIngreso(producto.getFechaIngreso());
        return dto;
    }

    public Producto toEntity() {
        Producto producto = new Producto();
        producto.setId(this.id);
        producto.setNombre(this.nombre);
        producto.setDescripcion(this.descripcion);
        producto.setCategoria(this.categoria);
        producto.setPrecio(this.precio);
        producto.setStock(this.stock);
        producto.setMarca(this.marca);
        producto.setActivo(this.activo);
        producto.setFechaIngreso(this.fechaIngreso);
        return producto;
    }
}