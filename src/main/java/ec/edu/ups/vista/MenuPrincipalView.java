package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemBuscarProducto;
    private JMenuItem menuItemModificarProdcuto;
    private JMenuItem menuItemAnadirCarrito;
    private JMenuItem menuItemEliminarCarrito;
    private JMenuItem menuItemListaCarrito;
    private JMenuItem menuItemModificarCarrito;
    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemBuscarUsuario;
    private JMenuItem menuItemModificarUsuario;
    private JMenuItem menuItemEliminarUsuario;
    private JButton btnCerrarSesion;
    private JDesktopPane jDesktopPane;

    public MenuPrincipalView(){
        jDesktopPane = new JDesktopPane();

        menuBar = new JMenuBar();
        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");
        menuUsuario = new JMenu("Usuario");

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");
        menuItemModificarProdcuto = new JMenuItem("Modificar Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");

        menuItemAnadirCarrito = new JMenuItem("Crear Carrito");
        menuItemListaCarrito = new JMenuItem("Buscar Carrito");
        menuItemModificarCarrito = new JMenuItem("Modificar Carrito");
        menuItemEliminarCarrito = new JMenuItem("Eliminar Carrito");

        menuItemCrearUsuario = new JMenuItem("Crear Usuario");
        menuItemBuscarUsuario = new JMenuItem("Buscar Usuario");
        menuItemModificarUsuario = new JMenuItem("Modificar Usuario");
        menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);

        btnCerrarSesion = new JButton("Cerrar Sesión");
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(btnCerrarSesion);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuProducto.add(menuItemModificarProdcuto);

        menuCarrito.add(menuItemAnadirCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuCarrito.add(menuItemListaCarrito);
        menuCarrito.add(menuItemModificarCarrito);

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemBuscarUsuario);
        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

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
    }

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

    public JMenuItem getMenuItemAnadirCarrito() {
        return menuItemAnadirCarrito;
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

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}
