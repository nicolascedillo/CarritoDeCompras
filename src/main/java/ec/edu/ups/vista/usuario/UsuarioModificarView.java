package ec.edu.ups.vista.usuario;

import javax.swing.*;

public class UsuarioModificarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JTextField contrasenaTextField;
    private JButton buscarButton;
    private JButton modificarButton;
    private JButton salirButton;

    public UsuarioModificarView() {
        super("Modificar Usuario", true, true, true, true);
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

    public JTextField getContrasenaTextField() {
        return contrasenaTextField;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JButton getModificarButton() {
        return modificarButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }
}
