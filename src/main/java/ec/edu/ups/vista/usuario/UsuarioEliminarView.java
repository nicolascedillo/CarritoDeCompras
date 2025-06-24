package ec.edu.ups.vista.usuario;

import javax.swing.*;

public class UsuarioEliminarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JButton buscarButton;
    private JTextField contrasenaTextField;
    private JButton eliminarButton;
    private JButton salirButton;

    public UsuarioEliminarView() {
        super("Eliminar Usuario", true, true, true, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        usernameTextField.setText("");
        contrasenaTextField.setText("");
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTextField getContrasenaTextField() {
        return contrasenaTextField;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }
}
