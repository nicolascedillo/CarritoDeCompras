package ec.edu.ups.modelo;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Clase que representa un carrito de compras.
 */
public class Carrito {

    private int codigo;
    private static int contador = 1;
    private GregorianCalendar fechaCreacion;
    private List<ItemCarrito> items;
    private Usuario usuario;

    /**
     * Constructor por defecto. Inicializa la lista de items y la fecha de creación.
     */
    public Carrito() {
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
    }

    /**
     * Constructor que recibe el usuario propietario del carrito.
     * Inicializa el código, la lista de items y la fecha de creación.
     * @param usuario Usuario propietario del carrito.
     */
    public Carrito(Usuario usuario) {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
        this.usuario = usuario;
    }

    /**
     * Obtiene el código único del carrito.
     * @return Código del carrito.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del carrito.
     * @param codigo Código a asignar.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la fecha de creación del carrito en formato dd/MM/yyyy.
     * @return Fecha de creación como String.
     */
    public String getFechaCreacion() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(fechaCreacion.getTime());
    }

    /**
     * Obtiene la fecha de creación como objeto Date.
     * @return Fecha de creación como Date.
     */
    public Date getFechaCreacionDate() {
        return fechaCreacion.getTime();
    }

    /**
     * Establece la fecha de creación del carrito.
     * @param fechaCreacion Fecha de creación como GregorianCalendar.
     */
    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Agrega un producto al carrito con la cantidad especificada.
     * @param producto Producto a agregar.
     * @param cantidad Cantidad del producto.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    /**
     * Elimina un producto del carrito según su código.
     * @param codigoProducto Código del producto a eliminar.
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Vacía el carrito, eliminando todos los items.
     */
    public void vaciarCarrito() {
        items.clear();
    }

    /**
     * Obtiene la lista de items del carrito.
     * @return Lista de ItemCarrito.
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    /**
     * Verifica si el carrito está vacío.
     * @return true si no hay items, false en caso contrario.
     */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /**
     * Calcula el subtotal de todos los productos en el carrito.
     * @return Subtotal como double.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    /**
     * Calcula el IVA (14%) sobre el subtotal del carrito.
     * @return Valor del IVA como double.
     */
    public double calcularIva() {
        return calcularSubtotal() * 0.14;
    }

    /**
     * Calcula el total a pagar (subtotal + IVA).
     * @return Total como double.
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIva();
    }

    /**
     * Obtiene el contador estático de carritos.
     * @return Valor actual del contador.
     */
    public static int getContador() {
        return contador;
    }

    /**
     * Establece el valor del contador estático de carritos.
     * @param contador Valor a asignar.
     */
    public static void setContador(int contador) {
        Carrito.contador = contador;
    }

    /**
     * Obtiene el usuario propietario del carrito.
     * @return Usuario propietario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario propietario del carrito.
     * @param usuario Usuario a asignar.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna una representación en texto del carrito, incluyendo código, usuario, fecha y items.
     * @return String representando el carrito.
     */
    @Override
    public String toString() {
        return  codigo + "_" + usuario.getUsername() + "_" +
                fechaCreacion.getTimeInMillis() + "_" + items ;
    }
}
