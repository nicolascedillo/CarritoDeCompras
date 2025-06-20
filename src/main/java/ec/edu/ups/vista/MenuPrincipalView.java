package ec.edu.ups.vista;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenuItem menuItemAddProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemBuscarProducto;
    private JMenuItem menuItemModificarProdcuto;
    private JMenuItem menuItemAnadirCarrito;
    private JMenuItem menuItemEliminarCarrito;
    private JDesktopPane jDesktopPane;

    public MenuPrincipalView(){
        jDesktopPane = new JDesktopPane();

        menuBar = new JMenuBar();
        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");

        menuItemAddProducto = new JMenuItem("Add Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");
        menuItemModificarProdcuto = new JMenuItem("Modificar Producto");

        menuItemAnadirCarrito = new JMenuItem("Anadir Carrito");
        menuItemEliminarCarrito = new JMenuItem("Eliminar Carrito");

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);

        menuProducto.add(menuItemAddProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuProducto.add(menuItemModificarProdcuto);

        menuCarrito.add(menuItemAnadirCarrito);
        menuCarrito.add(menuItemEliminarCarrito);

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

    public JMenuItem getMenuItemAddProducto() {
        return menuItemAddProducto;
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
}
