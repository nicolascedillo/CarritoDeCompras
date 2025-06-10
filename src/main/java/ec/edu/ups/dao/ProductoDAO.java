package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;

import java.util.List;

public interface ProductoDAO {

    void crear(Producto producto);

    Producto buscarPorCodigo(int codigo);

    List<Producto> buscarPorNombre(String nombre);

    void actualizar(Producto producto);

    void eliminar(int codigo);

    List<Producto> listarTodos();

}
