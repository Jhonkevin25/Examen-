package com.jhon.productos.producto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoDTO save(ProductoDTO dto) {
        return ProductoDTO.fromEntity(productoRepository.save(dto.toEntity()));
    }

    public List<ProductoDTO> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<ProductoDTO> findAllPaged(int page, int size, String sortBy, String dir) {
        Sort sort = dir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productoRepository.findAll(pageable).map(ProductoDTO::fromEntity);
    }

    public Optional<ProductoDTO> findById(Long id) {
        return productoRepository.findById(id).map(ProductoDTO::fromEntity);
    }

    public List<ProductoDTO> findByNombre(String nombre) {
        return productoRepository.findByNombreIgnoreCase(nombre)
                .stream()
                .map(ProductoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<ProductoDTO> findByNombreParcial(String nombre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productoRepository.findByNombreContaining(nombre, pageable)
                .map(ProductoDTO::fromEntity);
    }

    public ResponseEntity<ProductoDTO> actualizarProducto(Long id, ProductoDTO dto) {
        try {
            Optional<Producto> optional = productoRepository.findById(id);
            if (optional.isEmpty()) return ResponseEntity.notFound().build();

            Producto existente = optional.get();
            existente.setNombre(dto.getNombre());
            existente.setDescripcion(dto.getDescripcion());
            existente.setCategoria(dto.getCategoria());
            existente.setPrecio(dto.getPrecio());
            existente.setStock(dto.getStock());
            existente.setMarca(dto.getMarca());
            existente.setActivo(dto.getActivo());
            existente.setFechaIngreso(dto.getFechaIngreso());

            return ResponseEntity.ok(ProductoDTO.fromEntity(productoRepository.save(existente)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<ProductoDTO> patchProducto(Long id, ProductoDTO parcial) {
        try {
            Optional<Producto> optional = productoRepository.findById(id);
            if (optional.isEmpty()) return ResponseEntity.notFound().build();

            Producto existente = optional.get();
            if (parcial.getNombre() != null)       existente.setNombre(parcial.getNombre());
            if (parcial.getDescripcion() != null)  existente.setDescripcion(parcial.getDescripcion());
            if (parcial.getCategoria() != null)    existente.setCategoria(parcial.getCategoria());
            if (parcial.getPrecio() != null)       existente.setPrecio(parcial.getPrecio());
            if (parcial.getStock() != null)        existente.setStock(parcial.getStock());
            if (parcial.getMarca() != null)        existente.setMarca(parcial.getMarca());
            if (parcial.getActivo() != null)       existente.setActivo(parcial.getActivo());
            if (parcial.getFechaIngreso() != null) existente.setFechaIngreso(parcial.getFechaIngreso());

            return ResponseEntity.ok(ProductoDTO.fromEntity(productoRepository.save(existente)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deleteById(Long id) {
        if (!productoRepository.existsById(id)) return ResponseEntity.notFound().build();
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}