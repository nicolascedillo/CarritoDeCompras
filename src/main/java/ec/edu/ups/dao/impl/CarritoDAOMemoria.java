package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.util.ArrayList;
import java.util.List;

public class CarritoDAOMemoria implements CarritoDAO {

    private List<Carrito> carritos;

    public CarritoDAOMemoria() {
        carritos = new ArrayList<>();
    }

    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == codigo) {
                carritos.remove(carritos.get(i));
                break;
            }
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }
}
