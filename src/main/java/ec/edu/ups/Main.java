package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.PreguntaController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.PreguntaDAOMeroria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;
import ec.edu.ups.vista.carrito.*;
import ec.edu.ups.vista.login.LogInView;
import ec.edu.ups.vista.login.RecuperarContraseniaView;
import ec.edu.ups.vista.login.RegistraseView;
import ec.edu.ups.vista.producto.ProductoCrearView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import ec.edu.ups.vista.usuario.UsuarioCrearView;
import ec.edu.ups.vista.usuario.UsuarioEliminarView;
import ec.edu.ups.vista.usuario.UsuarioListarView;
import ec.edu.ups.vista.usuario.UsuarioModificarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                CarritoDAO carritoDAO = new CarritoDAOMemoria();
                PreguntaDAO preguntaDAO = new PreguntaDAOMeroria();

                MensajeInternacionalizacionHandler mIH = new MensajeInternacionalizacionHandler("es", "EC");

                LogInView logInView = new LogInView(mIH);
                RegistraseView registraseView = new RegistraseView(mIH);
                RecuperarContraseniaView recuperarContraseniaView = new RecuperarContraseniaView(mIH);
                UsuarioController usuarioController = new UsuarioController(usuarioDAO, logInView, mIH);
                PreguntaController preguntaController = new PreguntaController(usuarioDAO, preguntaDAO, logInView,registraseView, mIH, recuperarContraseniaView);

                logInView.getEspanolMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("es", "EC");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                logInView.getInglesMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("en", "US");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                logInView.getFrancesMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("fr", "FR");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                logInView.getAlemanMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("de", "DE");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                logInView.getItalianoMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("it", "IT");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });

                registraseView.getEspanolMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("es", "EC");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                registraseView.getInglesMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("en", "US");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                registraseView.getFrancesMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("fr", "FR");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                registraseView.getAlemanMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("de", "DE");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                registraseView.getItalianoMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("it", "IT");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });

                recuperarContraseniaView.getEspanolMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("es", "EC");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                recuperarContraseniaView.getInglesMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("en", "US");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                recuperarContraseniaView.getFrancesMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("fr", "FR");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                recuperarContraseniaView.getAlemanMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("de", "DE");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });
                recuperarContraseniaView.getItalianoMenuItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mIH.setLenguaje("it", "IT");
                        usuarioController.cambiarIdiomaLogIn(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                        preguntaController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                    }
                });

                logInView.setVisible(true);

                logInView.addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosed(WindowEvent e) {

                        super.windowClosed(e);
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();

                        if(usuarioAutenticado != null){

                            MenuPrincipalView menuPrincipalView = new MenuPrincipalView(mIH);
                            menuPrincipalView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());

                            //Instanciar vistas del producto

                            ProductoCrearView productoCrearView = new ProductoCrearView(mIH);
                            ProductoListaView productoListaView = new ProductoListaView(mIH);
                            ProductoModificarView productoModificarView = new ProductoModificarView(mIH);
                            ProductoEliminarView productoEliminarView = new ProductoEliminarView(mIH);

                            //Instancias vistas del carrito

                            CarritoCrearView carritoCrearView = new CarritoCrearView(mIH);
                            CarritoEliminarView carritoEliminarView = new CarritoEliminarView(mIH);
                            CarritoListaView carritoListaView = new CarritoListaView(mIH);
                            ItemListaView itemListaView = new ItemListaView(mIH);
                            CarritoModificarView carritoModificarView = new CarritoModificarView(mIH);
                            menuPrincipalView.getjDesktopPane().add(itemListaView);

                            //Instancias vistas del usuario

                            UsuarioCrearView usuarioCrearView = new UsuarioCrearView(mIH);
                            UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(mIH);
                            UsuarioListarView usuarioListarView = new UsuarioListarView(mIH);
                            UsuarioModificarView usuarioModificarView = new UsuarioModificarView(mIH);

                            // Instanciar controladores

                            ProductoController productoController = new ProductoController(productoCrearView,
                                    productoListaView,productoDAO,productoEliminarView,
                                    productoModificarView, mIH);

                            CarritoController carritoController = new CarritoController(productoDAO, carritoDAO,
                                    carritoCrearView,carritoEliminarView,carritoModificarView,carritoListaView,
                                    itemListaView, usuarioAutenticado,mIH);

                            UsuarioController usuarioController = new UsuarioController(usuarioDAO,usuarioCrearView,
                                    usuarioEliminarView, usuarioListarView, usuarioModificarView, usuarioAutenticado, mIH);

                            if(usuarioAutenticado.getPreguntasVerificacion().isEmpty() && usuarioAutenticado.getRol().equals(Rol.USUARIO)){
                                registraseView.setVisible(true);
                                registraseView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                preguntaController.configurarEventosUsuarioSinPregunta(usuarioAutenticado, menuPrincipalView);
                                menuPrincipalView.mostrarMensaje(mIH.get("sin.preguntas"));
                            }else{
                                menuPrincipalView.setVisible(true);
                                menuPrincipalView.mostrarMensaje(mIH.get("app.bienvenida") + ": " + usuarioAutenticado.getUsername());
                            }

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
                                    }
                                }
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

                            menuPrincipalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!usuarioCrearView.isVisible()){
                                        usuarioCrearView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(usuarioCrearView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!usuarioEliminarView.isVisible()){
                                        usuarioEliminarView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(usuarioEliminarView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemBuscarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!usuarioListarView.isVisible()){
                                        usuarioListarView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(usuarioListarView);
                                    }
                                }
                            });

                            menuPrincipalView.getMenuItemModificarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if(!usuarioModificarView.isVisible()){
                                        usuarioModificarView.setVisible(true);
                                        menuPrincipalView.getjDesktopPane().add(usuarioModificarView);
                                    }
                                }
                            });

                            //INTERNACIONALIZACION

                            menuPrincipalView.getMenuItemEspanol().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mIH.setLenguaje("es", "EC");
                                    menuPrincipalView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    productoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    carritoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    usuarioController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                }
                            });

                            menuPrincipalView.getMenuItemIngles().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mIH.setLenguaje("en", "US");
                                    menuPrincipalView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    productoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    carritoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    usuarioController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());

                                }
                            });

                            menuPrincipalView.getMenuItemFrances().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mIH.setLenguaje("fr", "FR");
                                    menuPrincipalView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    productoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    carritoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    usuarioController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                }
                            });

                            menuPrincipalView.getMenuItemAleman().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mIH.setLenguaje("de", "DE");
                                    menuPrincipalView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    productoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    carritoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    usuarioController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                }
                            });

                            menuPrincipalView.getMenuItemItaliano().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    mIH.setLenguaje("it", "IT");
                                    menuPrincipalView.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    productoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    carritoController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                    usuarioController.cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
                                }
                            });



                            //SALIR

                            menuPrincipalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    menuPrincipalView.setVisible(false);
                                    logInView.setVisible(true);
                                    Carrito.setContador(Carrito.getContador() - 1);
                                    logInView.getContrasenaPasswordField().setText("");
                                }
                            });

                            menuPrincipalView.getMenuItemSalir().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.exit(0);
                                }
                            });







                        }

                    }

                });

            }

        });

    }
}
