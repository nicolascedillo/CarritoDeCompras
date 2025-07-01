package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class UsuarioEliminarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JButton buscarButton;
    private JTextField contrasenaTextField;
    private JButton eliminarButton;
    private JButton salirButton;
    private JLabel lblTitulo;
    private JLabel lblContrasena;
    private JLabel lblUsuario;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioEliminarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.eliminar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 200);
        setFrameIcon(Icono.icono(Url.ELIMINAR));

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        eliminarButton.setIcon(Icono.icono(Url.ELIMINAR));
        salirButton.setIcon(Icono.icono(Url.CERRAR));

        cambiarIdioma(mIH.getLocale().getLanguage(),mIH.getLocale().getCountry());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        usernameTextField.setText("");
        contrasenaTextField.setText("");
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.usuario.eliminar"));
        lblTitulo.setText(mIH.get("ventana.usuario.eliminar.titulo"));
        lblUsuario.setText(mIH.get("ventana.usuario.usuario"));
        lblContrasena.setText(mIH.get("ventana.usuario.contrasena"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        eliminarButton.setText(mIH.get("ventana.eliminar"));
        salirButton.setText(mIH.get("ventana.salir"));
    }

    //GETTERS Y SETTERS

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
