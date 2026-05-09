package com.jhon.proveedores.proveedor.service;

import com.jhon.proveedores.proveedor.dto.ProveedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProveedorService {

    // GET ALL
    List<ProveedorDTO> findAll();

    // GET ALL PAGINADO
    Page<ProveedorDTO> findAllPaged(int page, int size, String sortBy, String dir);

    // GET BY ID
    ProveedorDTO findById(Long id);

    // GET BY NAME
    List<ProveedorDTO> findByNombre(String nombre);

    // GET BY EMAIL
    ProveedorDTO findByEmail(String email);

    // POST
    ProveedorDTO save(ProveedorDTO dto);

    // PUT
    ResponseEntity<ProveedorDTO> actualizarProveedor(Long id, ProveedorDTO dto);

    // PATCH
    ResponseEntity<ProveedorDTO> patchProveedor(Long id, ProveedorDTO parcial);

    // DELETE
    ResponseEntity<Void> deleteById(Long id);
}