package com.jhon.proveedores.proveedor.service.impl;

import com.jhon.proveedores.proveedor.dto.ProveedorDTO;
import com.jhon.proveedores.proveedor.entity.Proveedor;
import com.jhon.proveedores.proveedor.exception.ProveedorNotFoundException;
import com.jhon.proveedores.proveedor.repository.ProveedorRepository;
import com.jhon.proveedores.proveedor.service.ProveedorService;
import com.jhon.proveedores.proveedor.validation.ProveedorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorValidation proveedorValidation;

    @Override
    public List<ProveedorDTO> findAll() {
        return proveedorRepository.findAll()
                .stream()
                .map(ProveedorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProveedorDTO> findAllPaged(int page, int size,
            String sortBy, String dir) {
        Sort sort = dir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return proveedorRepository.findAll(pageable).map(ProveedorDTO::fromEntity);
    }

    @Override
    public ProveedorDTO findById(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
        return ProveedorDTO.fromEntity(proveedor);
    }

    @Override
    public List<ProveedorDTO> findByNombre(String nombre) {
        return proveedorRepository.findByNombreIgnoreCase(nombre)
                .stream()
                .map(ProveedorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorDTO findByEmail(String email) {
        Proveedor proveedor = proveedorRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ProveedorNotFoundException(
                        "Proveedor no encontrado con email: " + email));
        return ProveedorDTO.fromEntity(proveedor);
    }

    @Override
    public ProveedorDTO save(ProveedorDTO dto) {
        List<String> errores = proveedorValidation.validar(dto);
        if (!errores.isEmpty()) {
            throw new RuntimeException("Errores de validación: " + errores);
        }
        return ProveedorDTO.fromEntity(proveedorRepository.save(dto.toEntity()));
    }

    @Override
    public ResponseEntity<ProveedorDTO> actualizarProveedor(Long id, ProveedorDTO dto) {
        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));

        existente.setNombre(dto.getNombre());
        existente.setEmail(dto.getEmail());
        existente.setTelefono(dto.getTelefono());
        existente.setDireccion(dto.getDireccion());
        existente.setPais(dto.getPais());
        existente.setActivo(dto.getActivo());
        existente.setFechaRegistro(dto.getFechaRegistro());

        return ResponseEntity.ok(
                ProveedorDTO.fromEntity(proveedorRepository.save(existente)));
    }

    @Override
    public ResponseEntity<ProveedorDTO> patchProveedor(Long id, ProveedorDTO parcial) {
        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));

        if (parcial.getNombre() != null)       existente.setNombre(parcial.getNombre());
        if (parcial.getEmail() != null)        existente.setEmail(parcial.getEmail());
        if (parcial.getTelefono() != null)     existente.setTelefono(parcial.getTelefono());
        if (parcial.getDireccion() != null)    existente.setDireccion(parcial.getDireccion());
        if (parcial.getPais() != null)         existente.setPais(parcial.getPais());
        if (parcial.getActivo() != null)       existente.setActivo(parcial.getActivo());
        if (parcial.getFechaRegistro() != null) existente.setFechaRegistro(parcial.getFechaRegistro());

        return ResponseEntity.ok(
                ProveedorDTO.fromEntity(proveedorRepository.save(existente)));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new ProveedorNotFoundException(id);
        }
        proveedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}