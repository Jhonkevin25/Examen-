package com.jhon.proveedores.proveedor.controller;

import com.jhon.proveedores.proveedor.dto.ProveedorDTO;
import com.jhon.proveedores.proveedor.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> findAll() {
        return ResponseEntity.ok(proveedorService.findAll());
    }

    // GET ALL PAGINADO
    @GetMapping("/paginado")
    public ResponseEntity<Page<ProveedorDTO>> findAllPaged(
            @RequestParam(defaultValue = "0")   int page,
            @RequestParam(defaultValue = "10")  int size,
            @RequestParam(defaultValue = "id")  String sortBy,
            @RequestParam(defaultValue = "asc") String dir) {
        return ResponseEntity.ok(
                proveedorService.findAllPaged(page, size, sortBy, dir));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.findById(id));
    }

    // GET BY NAME
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProveedorDTO>> findByNombre(
            @PathVariable String nombre) {
        return ResponseEntity.ok(proveedorService.findByNombre(nombre));
    }

    // GET BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<ProveedorDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(proveedorService.findByEmail(email));
    }

    // POST
    @PostMapping
    public ResponseEntity<ProveedorDTO> save(@RequestBody ProveedorDTO dto) {
        return ResponseEntity.status(201).body(proveedorService.save(dto));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizarProveedor(
            @PathVariable Long id, @RequestBody ProveedorDTO dto) {
        return proveedorService.actualizarProveedor(id, dto);
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<ProveedorDTO> patchProveedor(
            @PathVariable Long id, @RequestBody ProveedorDTO parcial) {
        return proveedorService.patchProveedor(id, parcial);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return proveedorService.deleteById(id);
    }
}