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
    private JLabel lblUsuario;
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
    private JLabel lblContrasena;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioEliminarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.eliminar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(475, 530);
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
        nombreTextField.setText("");
        telefonoTextField.setText("");
        correoTextField.setText("");
        anioTextField.setText("");
        diaComboBox.setSelectedIndex(0);
        mesComboBox.setSelectedIndex(0);
    }

    private void cargarDiasComboBox(JComboBox diaComboBox) {
        diaComboBox.removeAllItems();
        for (int i = 1; i <= 31; i++) {
            diaComboBox.addItem(i);
        }
    }

    private void cargarMesesComboBox(JComboBox mesComboBox) {
        mesComboBox.removeAllItems();
        mesComboBox.addItem(mIH.get("mes.enero"));
        mesComboBox.addItem(mIH.get("mes.febrero"));
        mesComboBox.addItem(mIH.get("mes.marzo"));
        mesComboBox.addItem(mIH.get("mes.abril"));
        mesComboBox.addItem(mIH.get("mes.mayo"));
        mesComboBox.addItem(mIH.get("mes.junio"));
        mesComboBox.addItem(mIH.get("mes.julio"));
        mesComboBox.addItem(mIH.get("mes.agosto"));
        mesComboBox.addItem(mIH.get("mes.septiembre"));
        mesComboBox.addItem(mIH.get("mes.octubre"));
        mesComboBox.addItem(mIH.get("mes.noviembre"));
        mesComboBox.addItem(mIH.get("mes.diciembre"));
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
        lblNombre.setText(mIH.get("ventana.usuario.nombre"));
        lblTelefono.setText(mIH.get("ventana.usuario.telefono"));
        lblCorreo.setText(mIH.get("ventana.usuario.email"));
        lblNacimiento.setText(mIH.get("ventana.usuario.fecha"));
        lblAnio.setText(mIH.get("ventana.usuario.anio"));
        if(lenguaje.equals("en")){
            cargarDiasComboBox(mesComboBox);
            cargarMesesComboBox(diaComboBox);
            lblDia.setText(mIH.get("ventana.usuario.mes"));
            lblMes.setText(mIH.get("ventana.usuario.dia"));
        }else{
            cargarDiasComboBox(diaComboBox);
            cargarMesesComboBox(mesComboBox);
            lblDia.setText(mIH.get("ventana.usuario.dia"));
            lblMes.setText(mIH.get("ventana.usuario.mes"));
        }
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

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public JTextField getTelefonoTextField() {
        return telefonoTextField;
    }

    public JTextField getCorreoTextField() {
        return correoTextField;
    }

    public JTextField getAnioTextField() {
        return anioTextField;
    }

    public JComboBox getDiaComboBox() {
        return diaComboBox;
    }

    public JComboBox getMesComboBox() {
        return mesComboBox;
    }
}
