package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class UsuarioModificarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JTextField contrasenaTextField;
    private JButton buscarButton;
    private JButton modificarButton;
    private JButton salirButton;
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JLabel lblNombre;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JTextField nombreTextField;
    private JTextField telefonoTextField;
    private JTextField correoTextField;
    private JLabel lblNacimiento;
    private JLabel lblDia;
    private JLabel lblMes;
    private JLabel lblAnio;
    private JTextField anioTextField;
    private JComboBox diaComboBox;
    private JComboBox mesComboBox;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioModificarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.modificar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 450);
        setFrameIcon(Icono.icono(Url.MODIFICAR));

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        modificarButton.setIcon(Icono.icono(Url.MODIFICAR));
        salirButton.setIcon(Icono.icono(Url.CERRAR));

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        usernameTextField.setText("");
        contrasenaTextField.setText("");
    }

    public void cambiarIdioma(String lenguaje,String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.usuario.modificar"));
        lblTitulo.setText(mIH.get("ventana.usuario.modificar.titulo"));
        lblUsuario.setText(mIH.get("ventana.usuario.usuario"));
        lblContrasena.setText(mIH.get("ventana.usuario.contrasena"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        modificarButton.setText(mIH.get("ventana.editar"));
        salirButton.setText(mIH.get("ventana.salir"));
    }

    //GETTERS Y SETTERS

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
