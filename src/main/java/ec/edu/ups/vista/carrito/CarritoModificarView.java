package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoModificarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField codigoTextField;
    private JTextField nombreTextField;
    private JButton buscarButton;
    private JTable table1;
    private JTextField subtotalTextField;
    private JTextField ivaTextField;
    private JTextField totalTextField;
    private JButton editarButton;
    private DefaultTableModel modelo;

    public CarritoModificarView() {
        setTitle("Modificar Carrito");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        modelo = new DefaultTableModel();
        String[] columnas = {"Código", "Nombre", "Precio","Cantidad"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatos(Carrito carrito) {

        for(ItemCarrito itemCarrito: carrito.obtenerItems()){
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    itemCarrito.getCantidad(),

            };
            modelo.addRow(fila);
        }
    }

    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTextField getSubtotalTextField() {
        return subtotalTextField;
    }

    public JTextField getIvaTextField() {
        return ivaTextField;
    }

    public JTextField getTotalTextField() {
        return totalTextField;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

}
