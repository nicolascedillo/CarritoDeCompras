package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoCrearView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField codigoTextField;
    private JTextField nombreTextField;
    private JTextField precioTextField;
    private JButton buscarButton;
    private JButton anadirButton;
    private JTable table1;
    private JTextField subtotalTextField;
    private JTextField ivaTextField;
    private JTextField totalTextField;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JComboBox cantidadComboBox;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JLabel lblTitulo;
    private JLabel lblPago;
    private JLabel lblDatosProducto;
    private JLabel lblLista;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para crear un carrito.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public CarritoCrearView( MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.carrito.crear"),true,true,true,true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        setFrameIcon(Icono.icono(Url.CREAR));
        cargarDatosComboBox();
        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        guardarButton.setIcon(Icono.icono(Url.GUARDAR));
        anadirButton.setIcon(Icono.icono(Url.CREAR));
        cancelarButton.setIcon(Icono.icono(Url.CERRAR));

        cambiarIdioma(mIH.getLocale().getLanguage(),mIH.getLocale().getCountry());
    }

    /**
     * Carga los valores posibles en el combo box de cantidad (del 1 al 20).
     */
    private void cargarDatosComboBox(){
        cantidadComboBox.removeAllItems();
        for(int i = 1; i<=20; i++){
            cantidadComboBox.addItem(i);
        }
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
        for(ItemCarrito item : carrito.obtenerItems()) {
            Object[] fila = {
                item.getProducto().getCodigo(),
                item.getProducto().getNombre(),
                    FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), mIH.getLocale()),
                item.getCantidad()
            };
            modelo.addRow(fila);
        }

    }

    /**
     * Limpia todos los campos de la ventana y la tabla.
     */
    public void limpiarCampos() {
        codigoTextField.setText("");
        nombreTextField.setText("");
        precioTextField.setText("");
        cantidadComboBox.setSelectedIndex(0);
        subtotalTextField.setText("0.0");
        ivaTextField.setText("0.0");
        totalTextField.setText("0.0");
        modelo.setRowCount(0);
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.carrito.crear"));
        Object[] columnas = {mIH.get("ventana.producto.codigo"), mIH.get("ventana.producto.nombre"), mIH.get("ventana.producto.precio"),mIH.get("ventana.carrito.cantidad")};
        modelo.setColumnIdentifiers(columnas);
        lblTitulo.setText(mIH.get("ventana.carrito.crear.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        lblCantidad.setText(mIH.get("ventana.carrito.cantidad"));
        lblSubtotal.setText(mIH.get("ventana.carrito.subtotal"));
        lblIva.setText(mIH.get("ventana.carrito.iva"));
        lblTotal.setText(mIH.get("ventana.carrito.total"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        anadirButton.setText(mIH.get("ventana.anadir"));
        guardarButton.setText(mIH.get("ventana.guardar"));
        cancelarButton.setText(mIH.get("ventana.cancelar"));
        lblPago.setText(mIH.get("ventana.carrito.pago"));
        lblDatosProducto.setText(mIH.get("ventana.carrito.crear.producto"));
        lblLista.setText(mIH.get("ventana.carrito.lista"));
    }

    //GETTERS Y SETTERS

    /**
     * Obtiene el campo de texto para el código del producto.
     * @return JTextField del código.
     */
    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    /**
     * Obtiene el campo de texto para el nombre del producto.
     * @return JTextField del nombre.
     */
    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    /**
     * Obtiene el campo de texto para el precio del producto.
     * @return JTextField del precio.
     */
    public JTextField getPrecioTextField() {
        return precioTextField;
    }

    /**
     * Obtiene el botón de buscar producto.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

    /**
     * Establece el botón de buscar producto.
     * @param buscarButton JButton a asignar.
     */
    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    /**
     * Obtiene el botón para añadir producto al carrito.
     * @return JButton de añadir.
     */
    public JButton getAnadirButton() {
        return anadirButton;
    }

    /**
     * Obtiene la tabla de productos añadidos al carrito.
     * @return JTable de productos.
     */
    public JTable getTable1() {
        return table1;
    }

    /**
     * Establece la tabla de productos.
     * @param table1 JTable a asignar.
     */
    public void setTable1(JTable table1) {
        this.table1 = table1;
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
     * Obtiene el botón para guardar el carrito.
     * @return JButton de guardar.
     */
    public JButton getGuardarButton() {
        return guardarButton;
    }

    /**
     * Obtiene el botón para cancelar la operación.
     * @return JButton de cancelar.
     */
    public JButton getCancelarButton() {
        return cancelarButton;
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return DefaultTableModel de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo de la tabla.
     * @param modelo Modelo a asignar.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el combo box de cantidad.
     * @return JComboBox de cantidad.
     */
    public JComboBox getCantidadComboBox() {
        return cantidadComboBox;
    }

    /**
     * Obtiene la etiqueta del código.
     * @return JLabel del código.
     */
    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    /**
     * Establece la etiqueta del código.
     * @param lblCodigo JLabel a asignar.
     */
    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    /**
     * Obtiene la etiqueta del nombre.
     * @return JLabel del nombre.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Establece la etiqueta del nombre.
     * @param lblNombre JLabel a asignar.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * Obtiene la etiqueta del título.
     * @return JLabel del título.
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta del título.
     * @param lblTitulo JLabel a asignar.
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

}
