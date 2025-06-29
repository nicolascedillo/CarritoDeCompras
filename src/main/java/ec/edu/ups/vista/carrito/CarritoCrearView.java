package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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
    private Carrito carrito;
    private Usuario usuario;
    private MensajeInternacionalizacionHandler mIH;

    public CarritoCrearView(Usuario usuario, MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.carrito.crear"),true,true,true,true);
        this.mIH = mIH;
        this.usuario = usuario;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        cargarDatosComboBox();
        modelo = new DefaultTableModel();
        table1.setModel(modelo);
        carrito = new Carrito(usuario);

        cambiarIdioma(mIH.getLocale().getLanguage(),mIH.getLocale().getCountry());
    }

    private void cargarDatosComboBox(){
        cantidadComboBox.removeAllItems();
        for(int i = 1; i<=20; i++){
            cantidadComboBox.addItem(i);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

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

    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public JTextField getPrecioTextField() {
        return precioTextField;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    public JButton getAnadirButton() {
        return anadirButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
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

    public JButton getGuardarButton() {
        return guardarButton;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JComboBox getCantidadComboBox() {
        return cantidadComboBox;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    public JLabel getLblSubtotal() {
        return lblSubtotal;
    }

    public void setLblSubtotal(JLabel lblSubtotal) {
        this.lblSubtotal = lblSubtotal;
    }

    public JLabel getLblIva() {
        return lblIva;
    }

    public void setLblIva(JLabel lblIva) {
        this.lblIva = lblIva;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

}
