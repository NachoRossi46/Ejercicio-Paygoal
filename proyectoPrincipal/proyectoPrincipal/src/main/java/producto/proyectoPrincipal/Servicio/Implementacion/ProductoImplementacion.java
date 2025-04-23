package producto.proyectoPrincipal.Servicio.Implementacion;

import org.springframework.stereotype.Service;
import producto.proyectoPrincipal.Model.Producto;
import producto.proyectoPrincipal.Repositorio.ProductoRepositorio;
import producto.proyectoPrincipal.Servicio.IProductoServicio;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductoImplementacion implements IProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    public ProductoImplementacion(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public List<Producto> obtenerTodosOrdenadosPorPrecio() {
        return productoRepositorio.findAllByOrderByPrecioAsc();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto obtenerPorNombre(String nombre) {
        return productoRepositorio.findByNombre(nombre).orElseThrow(() -> new IllegalStateException("Producto no encontrado con nombre: " + nombre));
    }

    @Override
    @Transactional
    public Producto crear(Producto producto) {
        // Verifico si ya existe un producto con el mismo nombre para que no haya duplicados
        if (productoRepositorio.findByNombre(producto.getNombre()).isPresent()) {
            throw new IllegalStateException("Ya existe un producto con el nombre: " + producto.getNombre());
        }
        return productoRepositorio.save(producto);
    }

    @Override
    @Transactional
    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto productoExistente = obtenerPorId(id);

        // Verifico si el nuevo nombre ya existe en otro producto
        if (!productoExistente.getNombre().equals(productoActualizado.getNombre()) &&
                productoRepositorio.findByNombre(productoActualizado.getNombre()).isPresent()) {
            throw new IllegalStateException("Ya existe un producto con el nombre: " + productoActualizado.getNombre());
        }

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setCantidad(productoActualizado.getCantidad());

        return productoRepositorio.save(productoExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!productoRepositorio.existsById(id)) {
            throw new IllegalStateException("Producto no encontrado con ID: " + id);
        }
        productoRepositorio.deleteById(id);
    }
}
