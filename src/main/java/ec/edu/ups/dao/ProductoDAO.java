package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;

import java.util.List;

public interface ProductoDAO {

    /**
     * Crea un nuevo producto y lo almacena en el sistema.
     * @param producto Producto a agregar.
     */
    void crear(Producto producto);

    /**
     * Busca un producto por su código.
     * @param codigo Código del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    Producto buscarPorCodigo(int codigo);

    /**
     * Busca productos por su nombre.
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    List<Producto> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un producto existente.
     * @param producto Producto con los datos actualizados.
     */
    void actualizar(Producto producto);

    /**
     * Elimina un producto por su código.
     * @param codigo Código del producto a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Lista todos los productos almacenados en el sistema.
     * @return Lista de todos los productos.
     */
    List<Producto> listarTodos();

}
