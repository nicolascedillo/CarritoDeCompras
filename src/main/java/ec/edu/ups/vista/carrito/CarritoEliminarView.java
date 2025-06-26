package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoEliminarView extends JInternalFrame{
    private JTextField codigoTextField;
    private JTextField nombreTextField;
    private JButton buscarButton;
    private JTable table1;
    private JTextField subtotalTextField;
    private JTextField ivaTextField;
    private JTextField totalTextField;
    private JButton eliminarButton;
    private JPanel panelPrincipal;
    private JTextField usuarioTextField;
    private DefaultTableModel modelo;

    public CarritoEliminarView() {
        super("Eliminar Carrito",true,true,true,true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio","Cantidad"};
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
                    itemCarrito.getCantidad()
            };
            modelo.addRow(fila);
        }
    }

    public void limpiarCampos() {
        codigoTextField.setText("");
        nombreTextField.setText("");
        subtotalTextField.setText("0.0");
        ivaTextField.setText("0.0");
        totalTextField.setText("0.0");
        modelo.setRowCount(0);
    }

    public void limpiarTabla() {
        modelo.setRowCount(0);
    }

    //Getters y Setters
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

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTextField getUsuarioTextField() {
        return usuarioTextField;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
}
