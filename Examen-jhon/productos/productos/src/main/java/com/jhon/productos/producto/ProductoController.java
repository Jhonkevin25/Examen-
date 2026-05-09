package com.jhon.productos.producto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<ProductoDTO>> findAllPaged(
            @RequestParam(defaultValue = "0")   int page,
            @RequestParam(defaultValue = "10")  int size,
            @RequestParam(defaultValue = "id")  String sortBy,
            @RequestParam(defaultValue = "asc") String dir) {
        return ResponseEntity.ok(productoService.findAllPaged(page, size, sortBy, dir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProductoDTO>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.findByNombre(nombre));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<ProductoDTO>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productoService.findByNombreParcial(nombre, page, size));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@RequestBody ProductoDTO dto) {
        return ResponseEntity.status(201).body(productoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable Long id, @RequestBody ProductoDTO dto) {
        return productoService.actualizarProducto(id, dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoDTO> patchProducto(
            @PathVariable Long id, @RequestBody ProductoDTO parcial) {
        return productoService.patchProducto(id, parcial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return productoService.deleteById(id);
    }
}