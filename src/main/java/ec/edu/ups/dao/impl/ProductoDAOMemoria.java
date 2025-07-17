package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDAOMemoria implements ProductoDAO {

    private List<Producto> productos;

    /**
     * Constructor de ProductoDAOMemoria.
     * Inicializa la lista interna de productos en memoria y agrega productos de ejemplo.
     */
    public ProductoDAOMemoria() {
        productos = new ArrayList<Producto>();
        crear(new Producto( "Computadora", 500.0));
        crear(new Producto( "TV", 300.0));
        crear(new Producto( "Celular", 700.0));
        crear(new Producto( "Microfono", 40.0));
        crear(new Producto( "Teclado", 50.0));
        crear(new Producto( "Mouse", 20.0));
        crear(new Producto( "Monitor", 150.0));
        crear(new Producto( "Impresora", 120.0));
        crear(new Producto( "Tablet", 250.0));
        crear(new Producto( "Auriculares", 80.0));
    }

    /**
     * Crea un nuevo producto y lo agrega a la lista en memoria.
     * Este método permite almacenar un producto en la estructura interna.
     *
     * @param producto Producto a agregar.
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un producto por su código.
     * Recorre la lista interna y retorna el producto que coincide con el código proporcionado.
     *
     * @param codigo Código del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Busca productos por su nombre.
     * Retorna una lista de productos cuyo nombre coincide con el parámetro proporcionado.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    /**
     * Actualiza un producto existente en la lista en memoria.
     * Reemplaza el producto que coincide con el código por el nuevo objeto proporcionado.
     *
     * @param producto Producto con los datos actualizados.
     */
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
            }
        }
    }

    /**
     * Elimina un producto de la lista en memoria por su código.
     * Busca el producto por código y lo elimina de la estructura interna.
     *
     * @param codigo Código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    /**
     * Lista todos los productos almacenados en memoria.
     * Retorna la lista completa de productos registrados.
     *
     * @return Lista de todos los productos.
     */
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}
