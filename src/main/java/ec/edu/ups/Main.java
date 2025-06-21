package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                // Iniciar sesion
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                LogInView logInView = new LogInView();
                logInView.setVisible(true);

                UsuarioController usuarioController = new UsuarioController(usuarioDAO, logInView);

                logInView.addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosed(WindowEvent e) {

                        super.windowClosed(e);
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();

                        if(usuarioAutenticado != null){
                            // instanciamos DAO (Singleton)
                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO carritoDAO = new CarritoDAOMemoria();

                            MenuPrincipalView menuPrincipalView = new MenuPrincipalView();

                            //Instanciar vistas del producto
                            ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                            ProductoListaView productoListaView = new ProductoListaView();
                            ProductoModificarView productoModificarView = new ProductoModificarView();
                            ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                            menuPrincipalView.getjDesktopPane().add(productoAnadirView);

                            //Instancias vistas del carrito
                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                            CarritoEliminarView carritoEliminarView = new CarritoEliminarView();
                            menuPrincipalView.getjDesktopPane().add(carritoAnadirView);
                            menuPrincipalView.getjDesktopPane().add(carritoEliminarView);

                            ProductoController productoController = new ProductoController(productoAnadirView,
                                    productoListaView,productoDAO,productoEliminarView,
                                    productoModificarView,carritoAnadirView);

                            CarritoController carritoController = new CarritoController(productoDAO, carritoDAO,
                                    carritoAnadirView,carritoEliminarView);

                            menuPrincipalView.mostrarMensaje("Bienvenido: " + usuarioAutenticado.getUsername());
                            if (usuarioAutenticado.getRol().equals(Rol.USUARIO)) {
                                menuPrincipalView.deshabilitarMenusAdministrador();
                            }

                            // Configurar eventos de la vista del menu principal
                            menuPrincipalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
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

                    }

                });

            }

        });

    }

}
