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
import ec.edu.ups.vista.carrito.*;
import ec.edu.ups.vista.producto.ProductoCrearView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

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
                            ProductoCrearView productoCrearView = new ProductoCrearView();
                            ProductoListaView productoListaView = new ProductoListaView();
                            ProductoModificarView productoModificarView = new ProductoModificarView();
                            ProductoEliminarView productoEliminarView = new ProductoEliminarView();

                            //Instancias vistas del carrito
                            CarritoCrearView carritoCrearView = new CarritoCrearView();
                            CarritoEliminarView carritoEliminarView = new CarritoEliminarView();
                            CarritoListaView carritoListaView = new CarritoListaView();
                            ItemListaView itemListaView = new ItemListaView();
                            CarritoModificarView carritoModificarView = new CarritoModificarView();
                            menuPrincipalView.getjDesktopPane().add(itemListaView);


                            ProductoController productoController = new ProductoController(productoCrearView,
                                    productoListaView,productoDAO,productoEliminarView,
                                    productoModificarView, carritoCrearView);

                            CarritoController carritoController = new CarritoController(productoDAO, carritoDAO,
                                    carritoCrearView,carritoEliminarView,carritoModificarView,carritoListaView,itemListaView);

                            menuPrincipalView.mostrarMensaje("Bienvenido: " + usuarioAutenticado.getUsername());
                            if (usuarioAutenticado.getRol().equals(Rol.USUARIO)) {
                                menuPrincipalView.deshabilitarMenusAdministrador();
                            }

                            // Configurar eventos de la vista del menu principal

                            //PRODUCTO

                            menuPrincipalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!productoCrearView.isVisible()){
                                        productoCrearView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(productoCrearView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!productoListaView.isVisible()){
                                        productoListaView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(productoListaView);
                                    }                                }
                            });

                            menuPrincipalView.getMenuItemModificarProdcuto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!productoModificarView.isVisible()){
                                        productoModificarView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(productoModificarView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!productoEliminarView.isVisible()) {
                                        productoEliminarView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(productoEliminarView);
                                    }
                                }
                            });

                            //CARRITO

                            menuPrincipalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!carritoCrearView.isVisible()){
                                        carritoCrearView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(carritoCrearView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!carritoEliminarView.isVisible()){
                                        carritoEliminarView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(carritoEliminarView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemListaCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!carritoListaView.isVisible()){
                                        carritoListaView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(carritoListaView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemModificarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!carritoModificarView.isVisible()){
                                        carritoModificarView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(carritoModificarView);
                                    }
                                }
                            });

                            //USUARIO

                            //CERRAR SESIÓN

                            menuPrincipalView.getBtnCerrarSesion().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    menuPrincipalView.dispose();
                                    run();
                                }
                            });

                        }

                    }

                });

            }

        });

    }

}
