package com.jhon.proveedores.proveedor.exception;

public class ProveedorNotFoundException extends RuntimeException {

    public ProveedorNotFoundException(Long id) {
        super("Proveedor no encontrado con ID: " + id);
    }

    public ProveedorNotFoundException(String mensaje) {
        super(mensaje);
    }
}