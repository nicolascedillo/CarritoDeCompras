package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class UsuarioCrearView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblContrasena;
    private JLabel lblUsuario;
    private JLabel lblNombre;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblNacimiento;
    private JLabel lblDia;
    private JLabel lblMes;
    private JLabel lblAnio;
    private JButton crearButton;
    private JButton salirButton;
    private JTextField usernameTextField;
    private JTextField nombreTextField;
    private JTextField telefonoTextField;
    private JTextField correoTextField;
    private JTextField anioTextField;
    private JPasswordField passwordField;
    private JComboBox diaComboBox;
    private JComboBox mesComboBox;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioCrearView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.crear"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 430);
        setFrameIcon(Icono.icono(Url.CREAR));

        crearButton.setIcon(Icono.icono(Url.CREAR));
        salirButton.setIcon(Icono.icono(Url.CERRAR));

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        usernameTextField.setText("");
        passwordField.setText("");
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.usuario.crear"));
        lblTitulo.setText(mIH.get("ventana.usuario.crear.titulo"));
        lblUsuario.setText(mIH.get("ventana.usuario.usuario"));
        lblContrasena.setText(mIH.get("ventana.usuario.contrasena"));
        crearButton.setText(mIH.get("ventana.aceptar"));
        salirButton.setText(mIH.get("ventana.salir"));
    }

    //GETTERS Y SETTERS


    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getCrearButton() {
        return crearButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }
}
