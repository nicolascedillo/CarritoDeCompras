package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoEliminarView extends JInternalFrame{
    private JTextField codigoTextField;
    private JTextField fechaTextField;
    private JButton buscarButton;
    private JTable table1;
    private JTextField subtotalTextField;
    private JTextField ivaTextField;
    private JTextField totalTextField;
    private JButton eliminarButton;
    private JPanel panelPrincipal;
    private JTextField usuarioTextField;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JLabel lblUsuario;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JLabel lblPago;
    private JLabel lblLista;
    private JLabel lblDatos;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public CarritoEliminarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.carrito.eliminar"),true,true,true,true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        setFrameIcon(Icono.icono(Url.ELIMINAR));

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        eliminarButton.setIcon(Icono.icono(Url.ELIMINAR));

        cambiarIdioma(mIH.getLocale().getLanguage(),mIH.getLocale().getCountry());

    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);
        for(ItemCarrito itemCarrito: carrito.obtenerItems()){
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    FormateadorUtils.formatearMoneda(producto.getPrecio(), mIH.getLocale()),
                    itemCarrito.getCantidad()
            };
            modelo.addRow(fila);
        }
    }

    public void limpiarCampos() {
        codigoTextField.setText("");
        fechaTextField.setText("");
        subtotalTextField.setText("0.0");
        ivaTextField.setText("0.0");
        totalTextField.setText("0.0");
        modelo.setRowCount(0);
    }

    public void limpiarTabla() {
        modelo.setRowCount(0);
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.carrito.eliminar"));
        lblTitulo.setText(mIH.get("ventana.carrito.eliminar.titulo"));
        Object[] columnas = {mIH.get("ventana.producto.codigo"), mIH.get("ventana.producto.nombre"), mIH.get("ventana.producto.precio"),mIH.get("ventana.carrito.cantidad")};
        modelo.setColumnIdentifiers(columnas);
        lblCodigo.setText(mIH.get("ventana.carrito.codigo"));
        lblFecha.setText(mIH.get("ventana.carrito.fecha"));
        lblUsuario.setText(mIH.get("ventana.carrito.usuario"));
        lblSubtotal.setText(mIH.get("ventana.carrito.subtotal"));
        lblIva.setText(mIH.get("ventana.carrito.iva"));
        lblTotal.setText(mIH.get("ventana.carrito.total"));
        lblPago.setText(mIH.get("ventana.carrito.pago"));
        lblLista.setText(mIH.get("ventana.carrito.lista"));
        lblDatos.setText(mIH.get("ventana.carrito.datos"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        eliminarButton.setText(mIH.get("ventana.eliminar"));
    }

    //GETTERS Y SETTERS

    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    public JTextField getFechaTextField() {
        return fechaTextField;
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
