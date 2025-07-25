package ec.edu.ups.modelo;

public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private static int contador = 1;

    /**
     * Constructor por defecto.
     */
    public Producto() {
    }

    /**
     * Constructor que recibe el nombre y el precio del producto.
     * Asigna un código único automáticamente.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public Producto(String nombre, double precio) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Constructor que recibe el código, nombre y precio del producto.
     * @param codigo Código del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Establece el código del producto.
     * @param codigo Código a asignar.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     * @param precio Precio a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el código del producto.
     * @return Código del producto.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     * @return Precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene el valor actual del contador de productos.
     * @return Valor del contador.
     */
    public static int getContador() {
        return contador;
    }

    /**
     * Establece el valor del contador de productos.
     * @param contador Valor a asignar.
     */
    public static void setContador(int contador) {
        Producto.contador = contador;
    }

    /**
     * Retorna una representación en texto del producto, incluyendo nombre y precio.
     * @return String representando el producto.
     */
    @Override
    public String toString() {
        return nombre + ";" + precio;
    }

}