package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class ItemListaView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField codigoDelCarritoTextField;
    private JTable table1;
    private JButton salirButton;
    private DefaultTableModel modelo;

    public ItemListaView() {
        super("Lista de Items del Carrito", true, true, true, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio","Cantidad"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);
        codigoDelCarritoTextField.setText(String.valueOf(carrito.getCodigo()));
        for(ItemCarrito itemCarrito: carrito.obtenerItems()){
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    itemCarrito.getCantidad()
            };
            modelo.addRow(fila);
        }
    }
}
