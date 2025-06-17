package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;

public interface CarritoDAO {

    void crear(Carrito carrito);

    void actualizar(Carrito carrito);

    void eliminar(int codigo);
}
