package com.jhon.productos.producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreIgnoreCase(String nombre);
    List<Producto> findByMarca(String marca);
    Page<Producto> findByCategoria(String categoria, Pageable pageable);
    Page<Producto> findByActivo(Boolean activo, Pageable pageable);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Producto> findByNombreContaining(@Param("nombre") String nombre, Pageable pageable);
}