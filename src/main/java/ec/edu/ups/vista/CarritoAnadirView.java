package ec.edu.ups.vista;

import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CarritoAnadirView extends JInternalFrame{
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
    private List<ItemCarrito> itemsAniadidos;
    private DefaultTableModel modelo;

    public  CarritoAnadirView() {
        super("Carrito Anadir",true,true,true,true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400,700);
        cargarDatosComboBox();
        itemsAniadidos = new ArrayList<>();
        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio","Cantidad"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
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

    public void cargarDatos(Producto producto) {

        Object[] fila = {
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio(),
                cantidadComboBox.getSelectedItem()
        };
        modelo.addRow(fila);

        double subtotal = Double.parseDouble(subtotalTextField.getText()) + producto.getPrecio();
        subtotalTextField.setText(String.valueOf(subtotal));
        double iva = subtotal * 0.14;
        ivaTextField.setText(String.valueOf(iva));
        double total = subtotal + iva;
        totalTextField.setText(String.valueOf(total));

    }

    public void crearItems(){

    }

    //Getters y setters
    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    public void setCodigoTextField(JTextField codigoTextField) {
        this.codigoTextField = codigoTextField;
    }

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public void setNombreTextField(JTextField nombreTextField) {
        this.nombreTextField = nombreTextField;
    }

    public JTextField getPrecioTextField() {
        return precioTextField;
    }

    public void setPrecioTextField(JTextField precioTextField) {
        this.precioTextField = precioTextField;
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

    public void setAnadirButton(JButton anadirButton) {
        this.anadirButton = anadirButton;
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

    public void setSubtotalTextField(JTextField subtotalTextField) {
        this.subtotalTextField = subtotalTextField;
    }

    public JTextField getIvaTextField() {
        return ivaTextField;
    }

    public void setIvaTextField(JTextField ivaTextField) {
        this.ivaTextField = ivaTextField;
    }

    public JTextField getTotalTextField() {
        return totalTextField;
    }

    public void setTotalTextField(JTextField totalTextField) {
        this.totalTextField = totalTextField;
    }

    public JButton getGuardarButton() {
        return guardarButton;
    }

    public void setGuardarButton(JButton guardarButton) {
        this.guardarButton = guardarButton;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public void setCancelarButton(JButton cancelarButton) {
        this.cancelarButton = cancelarButton;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
}
