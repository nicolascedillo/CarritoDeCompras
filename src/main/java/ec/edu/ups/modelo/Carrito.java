package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Carrito {

    private int codigo;
    private static int contador = 1;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;

    public Carrito() {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFechaCreacion() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(fechaCreacion.getTime());
    }

    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularIva() {
        return calcularSubtotal() * 0.14;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIva();
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Carrito.contador = contador;
    }


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Carrito{" +
                "codigo=" + codigo +
                ", fechaCreacion=" + dateFormat.format(fechaCreacion.getTime()) +
                ", items=" + items +
                '}';
    }
}

