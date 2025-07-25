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

    /**
     * Constructor de la ventana para eliminar un carrito.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
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

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Carga los datos de los items del carrito en la tabla.
     * @param carrito Carrito cuyos items se mostrarán.
     */
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

    /**
     * Limpia todos los campos de la ventana y la tabla.
     */
    public void limpiarCampos() {
        codigoTextField.setText("");
        fechaTextField.setText("");
        subtotalTextField.setText("0.0");
        ivaTextField.setText("0.0");
        totalTextField.setText("0.0");
        usuarioTextField.setText("");
        modelo.setRowCount(0);
    }

    /**
     * Limpia únicamente la tabla de productos.
     */
    public void limpiarTabla() {
        modelo.setRowCount(0);
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
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

    /**
     * Obtiene el campo de texto para el código del carrito.
     * @return JTextField del código.
     */
    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    /**
     * Obtiene el campo de texto para la fecha del carrito.
     * @return JTextField de la fecha.
     */
    public JTextField getFechaTextField() {
        return fechaTextField;
    }

    /**
     * Obtiene el botón de buscar carrito.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

    /**
     * Obtiene el campo de texto para el subtotal.
     * @return JTextField del subtotal.
     */
    public JTextField getSubtotalTextField() {
        return subtotalTextField;
    }

    /**
     * Obtiene el campo de texto para el IVA.
     * @return JTextField del IVA.
     */
    public JTextField getIvaTextField() {
        return ivaTextField;
    }

    /**
     * Obtiene el campo de texto para el total.
     * @return JTextField del total.
     */
    public JTextField getTotalTextField() {
        return totalTextField;
    }

    /**
     * Obtiene el botón para eliminar el carrito.
     * @return JButton de eliminar.
     */
    public JButton getEliminarButton() {
        return eliminarButton;
    }

    /**
     * Obtiene el panel principal de la ventana.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return DefaultTableModel de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Obtiene el campo de texto para el usuario.
     * @return JTextField del usuario.
     */
    public JTextField getUsuarioTextField() {
        return usuarioTextField;
    }

    /**
     * Establece el modelo de la tabla.
     * @param modelo Modelo a asignar.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
}
