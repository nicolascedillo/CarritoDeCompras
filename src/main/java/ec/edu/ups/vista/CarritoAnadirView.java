package ec.edu.ups.vista;

import javax.swing.*;

public class CarritoAnadirView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField codigoTextField;
    private JTextField nombreTextField;
    private JTextField precioTextField;
    private JButton buscarButton;
    private JButton anadirButton;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JComboBox cantidadComboBox;

    public  CarritoAnadirView() {
        super("Carrito Anadir",true,true,true,true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        cargarDatosComboBox();
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

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
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
}
