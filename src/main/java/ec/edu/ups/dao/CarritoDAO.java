package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

public interface CarritoDAO {

    void crear(Carrito carrito);

    Carrito buscarPorCodigo(int codigo);

    Carrito buscarPorCodigoYUsuario(int codigo, Usuario usuario);

    void actualizar(Carrito carrito);

    void eliminar(int codigo);

    List<Carrito> listarTodos();

    List<Carrito> listarPorUsuario(Usuario usuario);
}
