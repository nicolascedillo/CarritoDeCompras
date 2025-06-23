package ec.edu.ups.vista.producto;

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
    private JButton buscarButton;

    public  ProductoModificarView(){
        setContentPane(panelPrincipal);
        setTitle("Modificar Producto");
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

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

}
