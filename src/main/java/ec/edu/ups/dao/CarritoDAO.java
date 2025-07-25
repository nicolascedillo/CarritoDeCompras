package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

public interface CarritoDAO {

    /**
     * Crea un nuevo carrito y lo almacena en el sistema.
     * @param carrito Carrito a agregar.
     */
    void crear(Carrito carrito);

    /**
     * Busca un carrito por su código.
     * @param codigo Código del carrito a buscar.
     * @return El carrito encontrado o null si no existe.
     */
    Carrito buscarPorCodigo(int codigo);

    /**
     * Busca un carrito por su código y usuario propietario.
     * @param codigo Código del carrito.
     * @param usuario Usuario propietario del carrito.
     * @return El carrito encontrado o null si no existe.
     */
    Carrito buscarPorCodigoYUsuario(int codigo, Usuario usuario);

    /**
     * Actualiza los datos de un carrito existente.
     * @param carrito Carrito con los datos actualizados.
     */
    void actualizar(Carrito carrito);

    /**
     * Elimina un carrito por su código.
     * @param codigo Código del carrito a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Lista todos los carritos almacenados en el sistema.
     * @return Lista de todos los carritos.
     */
    List<Carrito> listarTodos();

    /**
     * Lista todos los carritos asociados a un usuario específico.
     * @param usuario Usuario propietario de los carritos.
     * @return Lista de carritos del usuario.
     */
    List<Carrito> listarPorUsuario(Usuario usuario);
}
