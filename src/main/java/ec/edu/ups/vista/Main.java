package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoView productoView = new ProductoView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                new ProductoController(productoDAO, productoView);
            }
        });
    }
}
