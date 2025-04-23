package producto.proyectoPrincipal.Servicio;

import producto.proyectoPrincipal.Model.Producto;

import java.util.List;

public interface IProductoServicio {
    List<Producto> obtenerTodosOrdenadosPorPrecio();
    Producto obtenerPorNombre(String nombre);
    Producto obtenerPorId(Long id);
    Producto crear(Producto producto);
    Producto actualizar(Long id, Producto productoActualizado);
    void eliminar(Long id);
}
