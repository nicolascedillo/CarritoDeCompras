package ec.edu.ups.vista.producto;

import javax.swing.*;

public class ProductoEliminarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblNombre;
    private JButton salirButton;
    private JButton btnEliminar;
    private JButton buscarButton;
    private JOptionPane jOptionPane;

    public ProductoEliminarView() {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450,200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    //Getters y setters

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

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

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    public JOptionPane getjOptionPane() {
        return jOptionPane;
    }

    public void setjOptionPane(JOptionPane jOptionPane) {
        this.jOptionPane = jOptionPane;
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}
