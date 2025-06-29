package ec.edu.ups.vista.producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public ProductoListaView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {mIH.get("ventana.producto.codigo"), mIH.get("ventana.producto.nombre"), mIH.get("ventana.producto.precio")};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        setTitle(mIH.get("menu.producto.buscar"));
        lblTitulo.setText(mIH.get("ventana.producto.buscar.titulo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        btnBuscar.setText(mIH.get("ventana.buscar"));
        btnListar.setText(mIH.get("ventana.listar"));

    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatos(List<Producto> listaProductos) {
        modelo.setNumRows(0);

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    FormateadorUtils.formatearMoneda(producto.getPrecio(), mIH.getLocale())
            };
            modelo.addRow(fila);
        }

    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        Object[] columnas = {mIH.get("ventana.producto.codigo"), mIH.get("ventana.producto.nombre"), mIH.get("ventana.producto.precio")};
        modelo.setColumnIdentifiers(columnas);
        setTitle(mIH.get("menu.producto.buscar"));
        lblTitulo.setText(mIH.get("ventana.producto.buscar.titulo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        btnBuscar.setText(mIH.get("ventana.buscar"));
        btnListar.setText(mIH.get("ventana.listar"));
    }

    //GETTERS Y SETTERS

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

}
