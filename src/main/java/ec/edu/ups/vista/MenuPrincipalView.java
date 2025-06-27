package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemSalir;
    private JDesktopPane jDesktopPane;
    private MensajeInternacionalizacionHandler mIH;



    public MenuPrincipalView(MensajeInternacionalizacionHandler mIH){
        this.mIH = mIH;

        jDesktopPane = new JDesktopPane();
        setTitle(mIH.get("app.titulo"));

        menuBar = new JMenuBar();
        menuProducto = new JMenu(mIH.get("menu.producto"));
        menuCarrito = new JMenu(mIH.get("menu.carrito"));
        menuUsuario = new JMenu(mIH.get("menu.usuario"));
        menuIdioma = new JMenu(mIH.get("menu.idiomas"));
        menuSalir = new JMenu(mIH.get("menu.salir"));

        menuItemCrearProducto = new JMenuItem(mIH.get("menu.producto.crear"));
        menuItemBuscarProducto = new JMenuItem(mIH.get("menu.producto.buscar"));
        menuItemModificarProdcuto = new JMenuItem(mIH.get("menu.producto.modificar"));
        menuItemEliminarProducto = new JMenuItem(mIH.get("menu.producto.eliminar"));

        menuItemCrearCarrito = new JMenuItem(mIH.get("menu.carrito.crear"));
        menuItemListaCarrito = new JMenuItem(mIH.get("menu.carrito.buscar"));
        menuItemModificarCarrito = new JMenuItem(mIH.get("menu.carrito.modificar"));
        menuItemEliminarCarrito = new JMenuItem(mIH.get("menu.carrito.eliminar"));

        menuItemCrearUsuario = new JMenuItem(mIH.get("menu.usuario.crear"));
        menuItemBuscarUsuario = new JMenuItem(mIH.get("menu.usuario.buscar"));
        menuItemModificarUsuario = new JMenuItem(mIH.get("menu.usuario.modificar"));
        menuItemEliminarUsuario = new JMenuItem(mIH.get("menu.usuario.eliminar"));

        menuItemEspanol = new JMenuItem(mIH.get("menu.idioma.es"));
        menuItemIngles = new JMenuItem(mIH.get("menu.idioma.en"));
        menuItemFrances = new JMenuItem(mIH.get("menu.idioma.fr"));

        menuItemCerrarSesion = new JMenuItem(mIH.get("menu.salir.cerrar"));
        menuItemSalir = new JMenuItem(mIH.get("menu.salir.salir"));

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);
        menuBar.add(menuSalir);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuProducto.add(menuItemModificarProdcuto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuCarrito.add(menuItemListaCarrito);
        menuCarrito.add(menuItemModificarCarrito);

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemBuscarUsuario);
        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuIdioma.add(menuItemEspanol);
        menuIdioma.add(menuItemIngles);
        menuIdioma.add(menuItemFrances);

        menuSalir.add(menuItemCerrarSesion);
        menuSalir.add(menuItemSalir);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        jDesktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carrito de Compras En Linea");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        //mdi sistema de multiples documentos

    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemModificarProdcuto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
        getMenuItemListaCarrito().setEnabled(false);
        getMenuItemCrearUsuario().setEnabled(false);
        getMenuItemBuscarUsuario().setEnabled(false);
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

    public JDesktopPane getjDesktopPane() {
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
