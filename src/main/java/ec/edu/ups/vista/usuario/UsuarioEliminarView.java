package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

/**
 * Clase que representa la ventana para eliminar usuarios en el sistema.
 * Permite buscar, mostrar y eliminar la información de un usuario.
 */
public class UsuarioEliminarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JButton buscarButton;
    private JTextField contrasenaTextField;
    private JButton eliminarButton;
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

    /**
     * Constructor de la ventana para eliminar usuarios.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public UsuarioEliminarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.eliminar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(475, 530);
        setFrameIcon(Icono.icono(Url.ELIMINAR));

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        eliminarButton.setIcon(Icono.icono(Url.ELIMINAR));


        cambiarIdioma(mIH.getLocale().getLanguage(),mIH.getLocale().getCountry());
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos del formulario de eliminación de usuario.
     */
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

    /**
     * Carga los días en el combo box correspondiente.
     * @param diaComboBox JComboBox donde se cargarán los días.
     */
    private void cargarDiasComboBox(JComboBox diaComboBox) {
        diaComboBox.removeAllItems();
        for (int i = 1; i <= 31; i++) {
            diaComboBox.addItem(i);
        }
    }

    /**
     * Carga los meses en el combo box correspondiente usando el handler de internacionalización.
     * @param mesComboBox JComboBox donde se cargarán los meses.
     */
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

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.usuario.eliminar"));
        lblTitulo.setText(mIH.get("ventana.usuario.eliminar.titulo"));
        lblUsuario.setText(mIH.get("ventana.usuario.usuario"));
        lblContrasena.setText(mIH.get("ventana.usuario.contrasena"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        eliminarButton.setText(mIH.get("ventana.eliminar"));
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

    /**
     * Obtiene el campo de texto para el nombre de usuario.
     * @return JTextField del nombre de usuario.
     */
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    /**
     * Obtiene el botón para buscar usuario.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

    /**
     * Obtiene el campo de texto para la contraseña.
     * @return JTextField de la contraseña.
     */
    public JTextField getContrasenaTextField() {
        return contrasenaTextField;
    }

    /**
     * Obtiene el botón para eliminar usuario.
     * @return JButton de eliminar.
     */
    public JButton getEliminarButton() {
        return eliminarButton;
    }

    /**
     * Obtiene el campo de texto para el nombre.
     * @return JTextField del nombre.
     */
    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    /**
     * Obtiene el campo de texto para el teléfono.
     * @return JTextField del teléfono.
     */
    public JTextField getTelefonoTextField() {
        return telefonoTextField;
    }

    /**
     * Obtiene el campo de texto para el correo electrónico.
     * @return JTextField del correo.
     */
    public JTextField getCorreoTextField() {
        return correoTextField;
    }

    /**
     * Obtiene el campo de texto para el año de nacimiento.
     * @return JTextField del año.
     */
    public JTextField getAnioTextField() {
        return anioTextField;
    }

    /**
     * Obtiene el combo box de días.
     * @return JComboBox de días.
     */
    public JComboBox getDiaComboBox() {
        return diaComboBox;
    }

    /**
     * Obtiene el combo box de meses.
     * @return JComboBox de meses.
     */
    public JComboBox getMesComboBox() {
        return mesComboBox;
    }
}
