package ec.edu.ups.vista.login;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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
    private JComboBox idiomaComboBox;
    private JLabel tituloLbl;
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JLabel idiomaLbl;
    private JButton olvidadaButton;
    private MensajeInternacionalizacionHandler mIH;

    public LogInView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        setTitle(mIH.get("login.boton.iniciar"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cambiarTamanio(mIH);
        setLocationRelativeTo(null);
        setResizable(false);
        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        cambiarTamanio(mIH);
        setTitle(mIH.get("login.boton.iniciar"));
        tituloLbl.setText(mIH.get("login.titulo"));
        usernameLbl.setText(mIH.get("login.label.username"));
        passwordLbl.setText(mIH.get("login.label.password"));
        idiomaLbl.setText(mIH.get("login.label.idioma"));
        iniciarSesionButton.setText(mIH.get("login.boton.iniciar"));
        registrarseButton.setText(mIH.get("login.boton.registrarse"));
        salirButton.setText(mIH.get("login.boton.salir"));
        olvidadaButton.setText(mIH.get("login.boton.olvidar"));
    }

    public void cambiarTamanio(MensajeInternacionalizacionHandler idioma) {
        if (idioma.getLocale().getLanguage().equals("en")) {
            setSize(500, 275);
        } else if (idioma.getLocale().getLanguage().equals("es")) {
            setSize(550, 275);
        } else if (idioma.getLocale().getLanguage().equals("fr")) {
            setSize(600, 275);
        }
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

    public JComboBox getIdiomaComboBox() {
        return idiomaComboBox;
    }

    public JLabel getTituloLbl() {
        return tituloLbl;
    }

    public JLabel getUsernameLbl() {
        return usernameLbl;
    }

    public JLabel getPasswordLbl() {
        return passwordLbl;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mIH;
    }

}
