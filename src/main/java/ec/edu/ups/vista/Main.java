package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoAnadirView productoView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                ProductoOpcionView productoOpcionView = new ProductoOpcionView();

                new ProductoController(productoDAO, productoView, productoListaView, productoOpcionView);
            }
        });
    }
}
