package ec.edu.ups.modelo;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    /**
     * Constructor por defecto.
     */
    public ItemCarrito() {
    }

    /**
     * Constructor que recibe el producto y la cantidad.
     * @param producto Producto asociado al item.
     * @param cantidad Cantidad del producto.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Establece el producto del item.
     * @param producto Producto a asignar.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Establece la cantidad del producto en el item.
     * @param cantidad Cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado al item.
     * @return Producto del item.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad del producto en el item.
     * @return Cantidad del producto.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal del item (precio * cantidad).
     * @return Subtotal como double.
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    /**
     * Retorna una representación en texto del item, incluyendo código, nombre, precio y cantidad.
     * @return String representando el item.
     */
    @Override
    public String toString() {
        return producto.getCodigo() + ";" +  producto.getNombre() + ";" + producto.getPrecio()  + ";" + cantidad;
    }

}
