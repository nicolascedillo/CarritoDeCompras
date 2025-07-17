package ec.edu.ups.modelo;

public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private static int contador = 1;

    public Producto() {
    }

    public Producto(String nombre, double precio) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Producto.contador = contador;
    }

    @Override
    public String toString() {
        return nombre + ";" + precio;
    }

}