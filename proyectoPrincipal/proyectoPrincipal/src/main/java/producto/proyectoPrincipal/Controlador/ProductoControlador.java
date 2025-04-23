package producto.proyectoPrincipal.Controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import producto.proyectoPrincipal.Model.Producto;
import producto.proyectoPrincipal.Servicio.IProductoServicio;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    private final IProductoServicio productoServicio;

    public ProductoControlador(IProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping("/ordenados-por-precio")
    public ResponseEntity<List<Producto>> obtenerTodosOrdenadosPorPrecio() {
        return ResponseEntity.ok(productoServicio.obtenerTodosOrdenadosPorPrecio());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoServicio.obtenerPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Producto> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productoServicio.obtenerPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoServicio.crear(producto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoServicio.actualizar(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
