package ec.edu.ups.servicio;

import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoServiceImpl implements CarritoService {

    private final List<ItemCarrito> items;

    public CarritoServiceImpl() {
        items = new ArrayList<>();
    }

    @Override
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    @Override
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    @Override
    public void vaciarCarrito() {
        items.clear();
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }

    @Override
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    @Override
    public boolean estaVacio() {
        return items.isEmpty();
    }
}

