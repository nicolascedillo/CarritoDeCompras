package ec.edu.ups.vista.usuario;

import javax.swing.*;

public class UsuarioCrearView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JButton crearButton;
    private JPasswordField contraseñaPasswordField;
    private JButton salirButton;

    public UsuarioCrearView() {
        super("Crear Usuario", true, true, true, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        usernameTextField.setText("");
        contraseñaPasswordField.setText("");
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getContraseñaPasswordField() {
        return contraseñaPasswordField;
    }

    public JButton getCrearButton() {
        return crearButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }
}
