package com.jhon.proveedores.proveedor.validation;

import com.jhon.proveedores.proveedor.dto.ProveedorDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProveedorValidation {

    public List<String> validar(ProveedorDTO dto) {
        List<String> errores = new ArrayList<>();

        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            errores.add("El nombre es obligatorio");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            errores.add("El email es obligatorio");
        } else if (!dto.getEmail().contains("@")) {
            errores.add("El email no tiene un formato válido");
        }

        if (dto.getTelefono() == null || dto.getTelefono().isBlank()) {
            errores.add("El teléfono es obligatorio");
        }

        return errores;
    }
}