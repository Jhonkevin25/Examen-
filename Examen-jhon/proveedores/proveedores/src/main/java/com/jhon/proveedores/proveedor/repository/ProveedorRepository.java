package com.jhon.proveedores.proveedor.repository;

import com.jhon.proveedores.proveedor.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // GET BY NAME
    List<Proveedor> findByNombreIgnoreCase(String nombre);

    // GET BY EMAIL
    Optional<Proveedor> findByEmailIgnoreCase(String email);

    // GET BY PAIS
    List<Proveedor> findByPais(String pais);

    // PAGINADO por activo
    Page<Proveedor> findByActivo(Boolean activo, Pageable pageable);

    // BÚSQUEDA PARCIAL paginada
    @Query("SELECT p FROM Proveedor p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Proveedor> findByNombreContaining(@Param("nombre") String nombre, Pageable pageable);
}