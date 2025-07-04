package ec.edu.ups.vista;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuIdioma;
    private JMenu menuSalir;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemBuscarProducto;
    private JMenuItem menuItemModificarProdcuto;
    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemEliminarCarrito;
    private JMenuItem menuItemListaCarrito;
    private JMenuItem menuItemModificarCarrito;
    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemBuscarUsuario;
    private JMenuItem menuItemModificarUsuario;
    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemEspanol;
    private JMenuItem menuItemIngles;
    private JMenuItem menuItemFrances;
    private JMenuItem menuItemAleman;
    private JMenuItem menuItemItaliano;
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemSalir;
    private MiDesktopPane jDesktopPane;
    private MensajeInternacionalizacionHandler mIH;



    public MenuPrincipalView(MensajeInternacionalizacionHandler mIH){
        this.mIH = mIH;

        jDesktopPane = new MiDesktopPane();
        setTitle(mIH.get("app.titulo"));

        menuBar = new JMenuBar();
        menuProducto = new JMenu(mIH.get("menu.producto"));
        menuProducto.setIcon(Icono.icono(Url.PRODUCTO));
        menuCarrito = new JMenu(mIH.get("menu.carrito"));
        menuCarrito.setIcon(Icono.icono(Url.CARRITO));
        menuUsuario = new JMenu(mIH.get("menu.usuario"));
        menuUsuario.setIcon(Icono.icono(Url.USUARIO));
        menuIdioma = new JMenu(mIH.get("menu.idiomas"));
        menuIdioma.setIcon(Icono.icono(Url.IDIOMA));
        menuSalir = new JMenu(mIH.get("menu.salir"));
        menuSalir.setIcon(Icono.icono(Url.SALIR));

        menuItemCrearProducto = new JMenuItem(mIH.get("menu.producto.crear"));
        menuItemCrearProducto.setIcon(Icono.icono(Url.CREAR));
        menuItemBuscarProducto = new JMenuItem(mIH.get("menu.producto.buscar"));
        menuItemBuscarProducto.setIcon(Icono.icono(Url.BUSCAR));
        menuItemModificarProdcuto = new JMenuItem(mIH.get("menu.producto.modificar"));
        menuItemModificarProdcuto.setIcon(Icono.icono(Url.MODIFICAR));
        menuItemEliminarProducto = new JMenuItem(mIH.get("menu.producto.eliminar"));
        menuItemEliminarProducto.setIcon(Icono.icono(Url.ELIMINAR));

        menuItemCrearCarrito = new JMenuItem(mIH.get("menu.carrito.crear"));
        menuItemCrearCarrito.setIcon(Icono.icono(Url.CREAR));
        menuItemListaCarrito = new JMenuItem(mIH.get("menu.carrito.buscar"));
        menuItemListaCarrito.setIcon(Icono.icono(Url.LISTAR));
        menuItemModificarCarrito = new JMenuItem(mIH.get("menu.carrito.modificar"));
        menuItemModificarCarrito.setIcon(Icono.icono(Url.MODIFICAR));
        menuItemEliminarCarrito = new JMenuItem(mIH.get("menu.carrito.eliminar"));
        menuItemEliminarCarrito.setIcon(Icono.icono(Url.ELIMINAR));

        menuItemCrearUsuario = new JMenuItem(mIH.get("menu.usuario.crear"));
        menuItemCrearUsuario.setIcon(Icono.icono(Url.CREAR));
        menuItemBuscarUsuario = new JMenuItem(mIH.get("menu.usuario.buscar"));
        menuItemBuscarUsuario.setIcon(Icono.icono(Url.BUSCAR));
        menuItemModificarUsuario = new JMenuItem(mIH.get("menu.usuario.modificar"));
        menuItemModificarUsuario.setIcon(Icono.icono(Url.MODIFICAR));
        menuItemEliminarUsuario = new JMenuItem(mIH.get("menu.usuario.eliminar"));
        menuItemEliminarUsuario.setIcon(Icono.icono(Url.ELIMINAR));

        menuItemEspanol = new JMenuItem(mIH.get("menu.idioma.es"));
        menuItemEspanol.setIcon(Icono.icono(Url.IDIOMA));
        menuItemIngles = new JMenuItem(mIH.get("menu.idioma.en"));
        menuItemIngles.setIcon(Icono.icono(Url.IDIOMA));
        menuItemFrances = new JMenuItem(mIH.get("menu.idioma.fr"));
        menuItemFrances.setIcon(Icono.icono(Url.IDIOMA));
        menuItemAleman = new JMenuItem(mIH.get("menu.idioma.de"));
        menuItemAleman.setIcon(Icono.icono(Url.IDIOMA));
        menuItemItaliano = new JMenuItem(mIH.get("menu.idioma.it"));
        menuItemItaliano.setIcon(Icono.icono(Url.IDIOMA));

        menuItemCerrarSesion = new JMenuItem(mIH.get("menu.salir.cerrar"));
        menuItemCerrarSesion.setIcon(Icono.icono(Url.CERRAR));
        menuItemSalir = new JMenuItem(mIH.get("menu.salir.salir"));
        menuItemSalir.setIcon(Icono.icono(Url.SALIR));

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);
        menuBar.add(menuSalir);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuProducto.add(menuItemModificarProdcuto);
        menuProducto.add(menuItemEliminarProducto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemListaCarrito);
        menuCarrito.add(menuItemModificarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemBuscarUsuario);
        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuIdioma.add(menuItemEspanol);
        menuIdioma.add(menuItemIngles);
        menuIdioma.add(menuItemFrances);
        menuIdioma.add(menuItemAleman);
        menuIdioma.add(menuItemItaliano);

        menuSalir.add(menuItemCerrarSesion);
        menuSalir.add(menuItemSalir);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        jDesktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(mIH.get("app.titulo"));
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        //mdi sistema de multiples documentos

    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setVisible(false);
        getMenuItemModificarProdcuto().setVisible(false);
        getMenuItemEliminarProducto().setVisible(false);
        getMenuItemCrearUsuario().setVisible(false);
        getMenuItemBuscarUsuario().setVisible(false);
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("app.titulo"));
        menuProducto.setText(mIH.get("menu.producto"));
        menuCarrito.setText(mIH.get("menu.carrito"));
        menuUsuario.setText(mIH.get("menu.usuario"));
        menuIdioma.setText(mIH.get("menu.idiomas"));
        menuSalir.setText(mIH.get("menu.salir"));

        menuItemCrearProducto.setText(mIH.get("menu.producto.crear"));
        menuItemBuscarProducto.setText(mIH.get("menu.producto.buscar"));
        menuItemModificarProdcuto.setText(mIH.get("menu.producto.modificar"));
        menuItemEliminarProducto.setText(mIH.get("menu.producto.eliminar"));

        menuItemCrearCarrito.setText(mIH.get("menu.carrito.crear"));
        menuItemListaCarrito.setText(mIH.get("menu.carrito.buscar"));
        menuItemModificarCarrito.setText(mIH.get("menu.carrito.modificar"));
        menuItemEliminarCarrito.setText(mIH.get("menu.carrito.eliminar"));

        menuItemCrearUsuario.setText(mIH.get("menu.usuario.crear"));
        menuItemBuscarUsuario.setText(mIH.get("menu.usuario.buscar"));
        menuItemModificarUsuario.setText(mIH.get("menu.usuario.modificar"));
        menuItemEliminarUsuario.setText(mIH.get("menu.usuario.eliminar"));

        menuItemEspanol.setText(mIH.get("menu.idioma.es"));
        menuItemIngles.setText(mIH.get("menu.idioma.en"));
        menuItemFrances.setText(mIH.get("menu.idioma.fr"));
        menuItemAleman.setText(mIH.get("menu.idioma.de"));
        menuItemItaliano.setText(mIH.get("menu.idioma.it"));

        menuItemCerrarSesion.setText(mIH.get("menu.salir.cerrar"));
        menuItemSalir.setText(mIH.get("menu.salir.salir"));
    }

    //GETTERS

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public MiDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public JMenuItem getMenuItemModificarProdcuto() {
        return menuItemModificarProdcuto;
    }

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemListaCarrito() {
        return menuItemListaCarrito;
    }

    public JMenuItem getMenuItemModificarCarrito() {
        return menuItemModificarCarrito;
    }

    public JMenuItem getMenuItemCrearUsuario() {
        return menuItemCrearUsuario;
    }

    public JMenuItem getMenuItemBuscarUsuario() {
        return menuItemBuscarUsuario;
    }

    public JMenuItem getMenuItemModificarUsuario() {
        return menuItemModificarUsuario;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemEspanol() {
        return menuItemEspanol;
    }

    public JMenuItem getMenuItemIngles() {
        return menuItemIngles;
    }

    public JMenuItem getMenuItemFrances() {
        return menuItemFrances;
    }

    public JMenuItem getMenuItemAleman() {
        return menuItemAleman;
    }

    public JMenuItem getMenuItemItaliano() {
        return menuItemItaliano;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public JMenuItem getMenuItemSalir() {
        return menuItemSalir;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mIH;
    }


}
