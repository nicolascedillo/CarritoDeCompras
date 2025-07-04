package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.*;

public class Carrito {

    private int codigo;
    private static int contador = 1;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;
    private Usuario usuario;

    public Carrito(Usuario usuario) {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
        this.usuario = usuario;
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

    public Date getFechaCreacionDate() {
        return fechaCreacion.getTime();
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

