package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoModificarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField codigoTextField;
    private JTextField fechaTextField;
    private JButton buscarButton;
    private JTable table1;
    private JTextField subtotalTextField;
    private JTextField ivaTextField;
    private JTextField totalTextField;
    private JButton editarButton;
    private JTextField usuarioTextField;
    private JLabel lblTitulo;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JLabel lblPago;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JLabel lblUsuario;
    private JLabel lblDatos;
    private JButton eliminarButton;
    private JButton anadirButton;
    private JLabel lblLista;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public CarritoModificarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.carrito.modificar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
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
                    itemCarrito.getCantidad(),
            };
            modelo.addRow(fila);
        }
    }

    public void cambiarIdioma(String language, String country) {
        mIH.setLenguaje(language, country);
        setTitle(mIH.get("menu.carrito.modificar"));
        lblTitulo.setText(mIH.get("ventana.carrito.modificar.titulo"));
        lblSubtotal.setText(mIH.get("ventana.carrito.subtotal"));
        lblIva.setText(mIH.get("ventana.carrito.iva"));
        lblTotal.setText(mIH.get("ventana.carrito.total"));
        lblPago.setText(mIH.get("ventana.carrito.pago"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        editarButton.setText(mIH.get("ventana.editar"));
        eliminarButton.setText(mIH.get("ventana.eliminar"));
        anadirButton.setText(mIH.get("ventana.anadir"));
        lblCodigo.setText(mIH.get("ventana.carrito.codigo"));
        lblFecha.setText(mIH.get("ventana.carrito.fecha"));
        lblUsuario.setText(mIH.get("ventana.carrito.usuario"));
        lblDatos.setText(mIH.get("ventana.carrito.datos"));
        lblLista.setText(mIH.get("ventana.carrito.lista"));
        Object[] columnas = {
            mIH.get("ventana.producto.codigo"),
            mIH.get("ventana.producto.nombre"),
            mIH.get("ventana.producto.precio"),
            mIH.get("ventana.carrito.cantidad")
        };
        modelo.setColumnIdentifiers(columnas);
        lblPago.setText(mIH.get("ventana.carrito.pago"));
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

    public JButton getEditarButton() {
        return editarButton;
    }

    public JTable getTable1() {
        return table1;
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

    public JTextField getUsuarioTextField() {
        return usuarioTextField;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

}
