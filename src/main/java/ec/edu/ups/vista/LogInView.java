package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JPasswordField contrasenaPasswordField;
    private JButton iniciarSesionButton;
    private JButton registrarseButton;
    private JButton salirButton;

    public LogInView() {
        setTitle("Iniciar Sesión");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getContrasenaPasswordField() {
        return contrasenaPasswordField;
    }

    public JButton getIniciarSesionButton() {
        return iniciarSesionButton;
    }

    public JButton getRegistrarseButton() {
        return registrarseButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }

}
