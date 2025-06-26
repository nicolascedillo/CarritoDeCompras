package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

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
    private DefaultTableModel modelo;
    private Carrito carrito;
    private Usuario usuario;

    public CarritoCrearView(Usuario usuario) {
        super("Crear Carrito",true,true,true,true);
        this.usuario = usuario;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        cargarDatosComboBox();
        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio","Cantidad"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
        carrito = new Carrito(usuario);
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
                item.getProducto().getPrecio(),
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

    //Getters y setters
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
}
