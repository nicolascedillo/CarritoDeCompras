package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class UsuarioCrearView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JButton crearButton;
    private JPasswordField contrasenaPasswordField;
    private JButton salirButton;
    private JLabel lblTitulo;
    private JLabel lblUsername;
    private JLabel lblContrasena;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioCrearView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.crear"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 200);

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        usernameTextField.setText("");
        contrasenaPasswordField.setText("");
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.usuario.crear"));
        lblTitulo.setText(mIH.get("ventana.usuario.crear.titulo"));
        lblUsername.setText(mIH.get("ventana.usuario.usuario"));
        lblContrasena.setText(mIH.get("ventana.usuario.contrasena"));
        crearButton.setText(mIH.get("ventana.aceptar"));
        salirButton.setText(mIH.get("ventana.salir"));
    }

    //GETTERS Y SETTERS

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getContrasenaPasswordField() {
        return contrasenaPasswordField;
    }

    public JButton getCrearButton() {
        return crearButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }
}
