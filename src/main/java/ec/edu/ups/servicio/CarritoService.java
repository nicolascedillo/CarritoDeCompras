package ec.edu.ups.servicio;

import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import java.util.List;

public interface CarritoService {

    void agregarProducto(Producto producto, int cantidad);

    void eliminarProducto(int codigoProducto);

    void vaciarCarrito();

    double calcularTotal();

    List<ItemCarrito> obtenerItems();

    boolean estaVacio();

}

