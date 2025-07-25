package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CarritoDAOMemoria implements CarritoDAO {

    private List<Carrito> carritos;

    /**
     * Constructor de CarritoDAOMemoria.
     * Inicializa la lista interna de carritos en memoria.
     */
    public CarritoDAOMemoria() {
        carritos = new ArrayList<>();
    }

    /**
     * Crea un nuevo carrito y lo agrega a la lista en memoria.
     * Este método permite almacenar un carrito en la estructura interna.
     *
     * @param carrito Carrito a agregar.
     */
    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
    }

    /**
     * Busca un carrito por su código.
     * Recorre la lista interna y retorna el carrito que coincide con el código proporcionado.
     *
     * @param codigo Código del carrito a buscar.
     * @return El carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Busca un carrito por su código y usuario.
     * Retorna el carrito que coincide con el código y pertenece al usuario especificado.
     *
     * @param codigo Código del carrito.
     * @param usuario Usuario propietario del carrito.
     * @return El carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigoYUsuario(int codigo, Usuario usuario) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo && carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente en la lista en memoria.
     * Reemplaza el carrito que coincide con el código por el nuevo objeto proporcionado.
     *
     * @param carrito Carrito con los datos actualizados.
     */
    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    /**
     * Elimina un carrito de la lista en memoria por su código.
     * Busca el carrito por código y lo elimina de la estructura interna.
     *
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == codigo) {
                carritos.remove(carritos.get(i));
                break;
            }
        }
    }

    /**
     * Lista todos los carritos almacenados en memoria.
     * Retorna la lista completa de carritos registrados.
     *
     * @return Lista de todos los carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    /**
     * Lista todos los carritos asociados a un usuario específico.
     * Filtra y retorna los carritos que pertenecen al usuario indicado.
     *
     * @param usuario Usuario propietario de los carritos.
     * @return Lista de carritos del usuario.
     */
    @Override
    public List<Carrito> listarPorUsuario(Usuario usuario) {
        List<Carrito> carritosUsuario = new ArrayList<>();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                carritosUsuario.add(carrito);
            }
        }
        return carritosUsuario;
    }
}
