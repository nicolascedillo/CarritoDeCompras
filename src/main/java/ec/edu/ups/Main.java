package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoDAO productoDAO = new ProductoDAOMemoria();
                CarritoDAO carritoDAO = new CarritoDAOMemoria();

                MenuPrincipalView menuPrincipalView = new MenuPrincipalView();

                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoModificarView productoModificarView = new ProductoModificarView();
                ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                menuPrincipalView.getjDesktopPane().add(productoAnadirView);

                CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                CarritoEliminarView carritoEliminarView = new CarritoEliminarView();
                menuPrincipalView.getjDesktopPane().add(carritoAnadirView);
                menuPrincipalView.getjDesktopPane().add(carritoEliminarView);

                ProductoController productoController = new ProductoController(productoAnadirView,
                        productoListaView,productoDAO,productoEliminarView,
                        productoModificarView,carritoAnadirView);

                CarritoController carritoController = new CarritoController(productoDAO, carritoDAO,
                        carritoAnadirView,carritoEliminarView);

                menuPrincipalView.getMenuItemAddProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        productoAnadirView.setVisible(true);

                    }
                });

                menuPrincipalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        menuPrincipalView.getjDesktopPane().add(productoListaView);
                    }
                });

                menuPrincipalView.getMenuItemModificarProdcuto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        menuPrincipalView.getjDesktopPane().add(productoModificarView);
                    }
                });

                menuPrincipalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        menuPrincipalView.getjDesktopPane().add(productoEliminarView);
                    }
                });

                menuPrincipalView.getMenuItemAnadirCarrito().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        carritoAnadirView.setVisible(true);
                    }
                });

                menuPrincipalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        carritoEliminarView.setVisible(true);
                    }
                });
            }
        });
    }
}
