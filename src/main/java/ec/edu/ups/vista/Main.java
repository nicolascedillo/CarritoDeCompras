package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoDAO productoDAO = new ProductoDAOMemoria();
                Principal principal = new Principal();
                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoModificarView productoModificarView = new ProductoModificarView();
                ProductoEliminarView  productoEliminarView = new ProductoEliminarView();

                ProductoController productoController = new ProductoController(productoDAO);

                productoController.setProductoAnadirView(productoAnadirView);
                productoController.setProductoListaView(productoListaView);
                productoController.setProductoModificarView(productoModificarView);
                productoController.setProductoEliminarView(productoEliminarView);

                productoController.configurarEventosAnadir();
                productoController.configurarEventosModificar();
                productoController.configurarEventosEliminar();
                productoController.configurarEventosListar();


                principal.getMenuItemAddProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        principal.getjDesktopPane().add(productoAnadirView);

                    }
                });

                principal.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        principal.getjDesktopPane().add(productoListaView);
                    }
                });

                principal.getMenuItemModificarProdcuto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        principal.getjDesktopPane().add(productoModificarView);
                    }
                });

                principal.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        principal.getjDesktopPane().add(productoEliminarView);
                    }
                });

            }
        });
    }
}
