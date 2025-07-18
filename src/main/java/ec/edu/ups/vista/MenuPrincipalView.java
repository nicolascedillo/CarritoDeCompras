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



    /**
     * Constructor de la ventana principal del sistema.
     * Inicializa los menús, items, iconos y el fondo, usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
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
        //mdi sistema de multiples documentos

    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Deshabilita los menús y opciones exclusivas del administrador.
     */
    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setVisible(false);
        getMenuItemModificarProdcuto().setVisible(false);
        getMenuItemEliminarProducto().setVisible(false);
        getMenuItemCrearUsuario().setVisible(false);
        getMenuItemBuscarUsuario().setVisible(false);
    }

    /**
     * Cambia el idioma de la ventana y todos los menús usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
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

    /**
     * Obtiene el menú item para crear producto.
     * @return JMenuItem de crear producto.
     */
    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    /**
     * Obtiene el menú item para eliminar producto.
     * @return JMenuItem de eliminar producto.
     */
    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    /**
     * Obtiene el menú item para buscar producto.
     * @return JMenuItem de buscar producto.
     */
    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    /**
     * Obtiene el desktop pane principal.
     * @return MiDesktopPane.
     */
    public MiDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    /**
     * Obtiene el menú item para modificar producto.
     * @return JMenuItem de modificar producto.
     */
    public JMenuItem getMenuItemModificarProdcuto() {
        return menuItemModificarProdcuto;
    }

    /**
     * Obtiene el menú item para crear carrito.
     * @return JMenuItem de crear carrito.
     */
    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    /**
     * Obtiene el menú item para eliminar carrito.
     * @return JMenuItem de eliminar carrito.
     */
    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    /**
     * Obtiene el menú item para listar carritos.
     * @return JMenuItem de listar carritos.
     */
    public JMenuItem getMenuItemListaCarrito() {
        return menuItemListaCarrito;
    }

    /**
     * Obtiene el menú item para modificar carrito.
     * @return JMenuItem de modificar carrito.
     */
    public JMenuItem getMenuItemModificarCarrito() {
        return menuItemModificarCarrito;
    }

    /**
     * Obtiene el menú item para crear usuario.
     * @return JMenuItem de crear usuario.
     */
    public JMenuItem getMenuItemCrearUsuario() {
        return menuItemCrearUsuario;
    }

    /**
     * Obtiene el menú item para buscar usuario.
     * @return JMenuItem de buscar usuario.
     */
    public JMenuItem getMenuItemBuscarUsuario() {
        return menuItemBuscarUsuario;
    }

    /**
     * Obtiene el menú item para modificar usuario.
     * @return JMenuItem de modificar usuario.
     */
    public JMenuItem getMenuItemModificarUsuario() {
        return menuItemModificarUsuario;
    }

    /**
     * Obtiene el menú item para eliminar usuario.
     * @return JMenuItem de eliminar usuario.
     */
    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    /**
     * Obtiene el menú item para idioma español.
     * @return JMenuItem de español.
     */
    public JMenuItem getMenuItemEspanol() {
        return menuItemEspanol;
    }

    /**
     * Obtiene el menú item para idioma inglés.
     * @return JMenuItem de inglés.
     */
    public JMenuItem getMenuItemIngles() {
        return menuItemIngles;
    }

    /**
     * Obtiene el menú item para idioma francés.
     * @return JMenuItem de francés.
     */
    public JMenuItem getMenuItemFrances() {
        return menuItemFrances;
    }

    /**
     * Obtiene el menú item para idioma alemán.
     * @return JMenuItem de alemán.
     */
    public JMenuItem getMenuItemAleman() {
        return menuItemAleman;
    }

    /**
     * Obtiene el menú item para idioma italiano.
     * @return JMenuItem de italiano.
     */
    public JMenuItem getMenuItemItaliano() {
        return menuItemItaliano;
    }

    /**
     * Obtiene el menú item para cerrar sesión.
     * @return JMenuItem de cerrar sesión.
     */
    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    /**
     * Obtiene el menú item para salir de la aplicación.
     * @return JMenuItem de salir.
     */
    public JMenuItem getMenuItemSalir() {
        return menuItemSalir;
    }

    /**
     * Obtiene el handler de internacionalización.
     * @return Handler de internacionalización.
     */
    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mIH;
    }


}
