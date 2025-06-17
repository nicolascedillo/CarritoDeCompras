package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenuItem menuItemAddProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemBuscarProducto;
    private JMenuItem menuItemModificarProdcuto;
    private JDesktopPane jDesktopPane;

    public Principal(){
        jDesktopPane = new JDesktopPane();
        menuBar = new JMenuBar();
        menuProducto = new JMenu("Producto");
        menuItemAddProducto = new JMenuItem("Add Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");
        menuItemModificarProdcuto = new JMenuItem("Modificar Producto");

        menuBar.add(menuProducto);
        menuProducto.add(menuItemAddProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuProducto.add(menuItemModificarProdcuto);

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

    public JMenu getMenuProducto() {
        return menuProducto;
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
}
