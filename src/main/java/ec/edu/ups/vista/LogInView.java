package ec.edu.ups.vista;

import javax.swing.*;

public class LogInView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JPasswordField contrasenaPasswordField;
    private JButton iniciarSesionButton;
    private JButton registrarseButton;

    public LogInView() {
        setTitle("Iniciar Sesión");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public JPasswordField getContrasenaPasswordField() {
        return contrasenaPasswordField;
    }

    public void setContrasenaPasswordField(JPasswordField contrasenaPasswordField) {
        this.contrasenaPasswordField = contrasenaPasswordField;
    }

    public JButton getIniciarSesionButton() {
        return iniciarSesionButton;
    }

    public void setIniciarSesionButton(JButton iniciarSesionButton) {
        this.iniciarSesionButton = iniciarSesionButton;
    }

    public JButton getRegistrarseButton() {
        return registrarseButton;
    }

    public void setRegistrarseButton(JButton registrarseButton) {
        this.registrarseButton = registrarseButton;
    }
}
