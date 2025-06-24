package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoListaView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField codigoTextField;
    private JButton listarButton;
    private JButton buscarButton;
    private JTable table1;
    private JButton verDetallesButton;
    private DefaultTableModel modelo;

    public CarritoListaView() {
        super("Buscar o Listar Carritos", true, true, true, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 700);
        modelo = new DefaultTableModel();
        String[] columnas = {"Código", "Fecha", "Usuario", "Subtotal", "IVA", "Total"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatosLista(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            Object[] fila = {
                carrito.getCodigo(),
                carrito.getFechaCreacion(),
                "",
                carrito.calcularSubtotal(),
                carrito.calcularIva(),
                carrito.calcularTotal()
            };
            modelo.addRow(fila);
        }
    }

    public void cargarDatosBusqueda(Carrito carrito) {
        modelo.setRowCount(0);
        Object[] fila = {
                carrito.getCodigo(),
                carrito.getFechaCreacion(),
                "",
                carrito.calcularSubtotal(),
                carrito.calcularIva(),
                carrito.calcularTotal()
        };
        modelo.addRow(fila);
    }

    public void limpiarCampos() {
        codigoTextField.setText("");
        modelo.setRowCount(0);
    }

    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    public JButton getListarButton() {
        return listarButton;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JButton getVerDetallesButton() {
        return verDetallesButton;
    }
}
