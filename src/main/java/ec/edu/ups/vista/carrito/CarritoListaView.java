package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JLabel lblCarrito;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public CarritoListaView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.carrito.buscar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 700);

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatosLista(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            Object[] fila = {
                carrito.getCodigo(),
                    FormateadorUtils.formatearFecha(carrito.getFechaCreacionDate(),mIH.getLocale()),
                carrito.getUsuario().getUsername(),
                FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularIva(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularTotal(),mIH.getLocale())
            };
            modelo.addRow(fila);
        }
    }

    public void cargarDatosBusqueda(Carrito carrito) {
        modelo.setRowCount(0);
        Object[] fila = {
                carrito.getCodigo(),
                FormateadorUtils.formatearFecha(carrito.getFechaCreacionDate(),mIH.getLocale()),
                carrito.getUsuario().getUsername(),
                FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularIva(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularTotal(),mIH.getLocale())
        };
        modelo.addRow(fila);
    }

    public void limpiarCampos() {
        codigoTextField.setText("");
        modelo.setRowCount(0);
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.carrito.buscar"));
        String[] columnas = {mIH.get("ventana.carrito.codigo"), mIH.get("ventana.carrito.fecha"), mIH.get("ventana.carrito.usuario"), mIH.get("ventana.carrito.subtotal"), mIH.get("ventana.carrito.iva"), mIH.get("ventana.carrito.total")};
        modelo.setColumnIdentifiers(columnas);
        lblTitulo.setText(mIH.get("ventana.carrito.buscar.titulo"));
        lblCodigo.setText(mIH.get("ventana.carrito.codigo"));
        listarButton.setText(mIH.get("ventana.listar"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        verDetallesButton.setText(mIH.get("ventana.carrito.buscar.detalles"));
        lblCarrito.setText(mIH.get("ventana.carrito.buscar.encontrados"));
    }

    //GETTERS Y SETTERS

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
