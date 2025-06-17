package ec.edu.ups.vista;

import javax.swing.*;

public class ProductoModificarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JButton btnGuardar;
    private JLabel lblNombre;
    private JButton salirButton;
    private JButton buscarButton;

    public  ProductoModificarView(){
        setContentPane(panelPrincipal);
        setTitle("Modificar Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(true);
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    //Getters y setters
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JButton getSalirButton() {
        return salirButton;
    }

    public void setSalirButton(JButton salirButton) {
        this.salirButton = salirButton;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

}
