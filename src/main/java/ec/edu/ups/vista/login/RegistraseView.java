package ec.edu.ups.vista.login;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class RegistraseView extends JFrame{
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JTextField usuarioTextField;
    private JLabel lblContrasena;
    private JPasswordField passwordField1;
    private JLabel lblPreguntaCodigo;
    private JLabel lblTituloPreguntas;
    private JLabel lblEnunciado;
    private JTextField respuestaTextField;
    private JButton siguienteButton;
    private JButton guardarButton;
    private JPanel panelPrincipal;
    private JTextField nombreTextField;
    private JTextField telefonoTextField;
    private JTextField correoTextField;
    private JLabel lblNombre;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblNacimiento;
    private JLabel lblDia;
    private JLabel lblMes;
    private JLabel lblAnio;
    private JTextField anioTextField;
    private JComboBox diaComboBox;
    private JComboBox mesComboBox;
    private MensajeInternacionalizacionHandler mIH;
    private JMenu idiomaMenu;
    private JMenuBar menuBar;
    private JMenuItem inglesMenuItem;
    private JMenuItem espanolMenuItem;
    private JMenuItem francesMenuItem;
    private JMenuItem alemanMenuItem;
    private JMenuItem italianoMenuItem;

    /**
     * Constructor de la ventana de registro de usuario.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public RegistraseView(MensajeInternacionalizacionHandler mIH) {
        setTitle(mIH.get("registro.titulo"));
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        menuBar = new JMenuBar();
        idiomaMenu = new JMenu(mIH.get("menu.idiomas"));
        idiomaMenu.setIcon(Icono.icono(Url.IDIOMA));
        espanolMenuItem = new JMenuItem(mIH.get("menu.idioma.es"));
        espanolMenuItem.setIcon(Icono.icono(Url.IDIOMA));
        inglesMenuItem = new JMenuItem(mIH.get("menu.idioma.en"));
        inglesMenuItem.setIcon(Icono.icono(Url.IDIOMA));
        francesMenuItem = new JMenuItem(mIH.get("menu.idioma.fr"));
        francesMenuItem.setIcon(Icono.icono(Url.IDIOMA));
        alemanMenuItem = new JMenuItem(mIH.get("menu.idioma.de"));
        alemanMenuItem.setIcon(Icono.icono(Url.IDIOMA));
        italianoMenuItem = new JMenuItem(mIH.get("menu.idioma.it"));
        italianoMenuItem.setIcon(Icono.icono(Url.IDIOMA));

        menuBar.add(idiomaMenu);

        idiomaMenu.add(espanolMenuItem);
        idiomaMenu.add(inglesMenuItem);
        idiomaMenu.add(francesMenuItem);
        idiomaMenu.add(alemanMenuItem);
        idiomaMenu.add(italianoMenuItem);

        setJMenuBar(menuBar);

        siguienteButton.setIcon(Icono.icono(Url.SIGUIENTE));
        guardarButton.setIcon(Icono.icono(Url.GUARDAR));

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());

    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("registro.titulo"));
        lblTitulo.setText(mIH.get("registro.titulo"));
        lblUsuario.setText(mIH.get("ventana.usuario.usuario"));
        lblContrasena.setText(mIH.get("ventana.usuario.contrasena"));
        lblTituloPreguntas.setText(mIH.get("registro.titulo.preguntas"));
        siguienteButton.setText(mIH.get("ventana.siguiente"));
        guardarButton.setText(mIH.get("ventana.guardar"));
        lblNombre.setText(mIH.get("ventana.usuario.nombre"));
        lblTelefono.setText(mIH.get("ventana.usuario.telefono"));
        lblCorreo.setText(mIH.get("ventana.usuario.email"));
        lblNacimiento.setText(mIH.get("ventana.usuario.fecha"));
        lblAnio.setText(mIH.get("ventana.usuario.anio"));
        if(lenguaje.equals("en")){
            cargarDias(mesComboBox);
            cargarMeses(diaComboBox);
            lblDia.setText(mIH.get("ventana.usuario.mes"));
            lblMes.setText(mIH.get("ventana.usuario.dia"));
        }else{
            cargarDias(diaComboBox);
            cargarMeses(mesComboBox);
            lblDia.setText(mIH.get("ventana.usuario.dia"));
            lblMes.setText(mIH.get("ventana.usuario.mes"));
        }
    }

    /**
     * Limpia todos los campos del formulario de registro.
     */
    public void limpiarCampos() {
        usuarioTextField.setText("");
        passwordField1.setText("");
        respuestaTextField.setText("");
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
    private void cargarDias(JComboBox diaComboBox) {
        diaComboBox.removeAllItems();
        for (int i = 1; i <= 31; i++) {
            diaComboBox.addItem(i);
        }
    }

    /**
     * Carga los meses en el combo box correspondiente.
     * @param mesComboBox JComboBox donde se cargarán los meses.
     */
    private void cargarMeses(JComboBox mesComboBox) {
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
     * Obtiene la etiqueta del título.
     * @return JLabel del título.
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta del título.
     * @param lblTitulo JLabel a asignar.
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * Obtiene la etiqueta del usuario.
     * @return JLabel del usuario.
     */
    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    /**
     * Establece la etiqueta del usuario.
     * @param lblUsuario JLabel a asignar.
     */
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    /**
     * Obtiene el campo de texto para el usuario.
     * @return JTextField del usuario.
     */
    public JTextField getUsuarioTextField() {
        return usuarioTextField;
    }

    /**
     * Obtiene el campo de contraseña.
     * @return JPasswordField de la contraseña.
     */
    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    /**
     * Obtiene la etiqueta del enunciado de la pregunta.
     * @return JLabel del enunciado.
     */
    public JLabel getLblEnunciado() {
        return lblEnunciado;
    }

    /**
     * Obtiene el campo de texto para la respuesta de la pregunta.
     * @return JTextField de la respuesta.
     */
    public JTextField getRespuestaTextField() {
        return respuestaTextField;
    }

    /**
     * Obtiene el botón para avanzar a la siguiente pregunta.
     * @return JButton de siguiente.
     */
    public JButton getSiguienteButton() {
        return siguienteButton;
    }

    /**
     * Obtiene el botón para guardar el registro.
     * @return JButton de guardar.
     */
    public JButton getGuardarButton() {
        return guardarButton;
    }

    /**
     * Obtiene el panel principal de la ventana.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Establece el panel principal de la ventana.
     * @param panelPrincipal JPanel a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Obtiene el handler de internacionalización.
     * @return Handler de internacionalización.
     */
    public MensajeInternacionalizacionHandler getmIH() {
        return mIH;
    }

    /**
     * Establece el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public void setmIH(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
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

    /**
     * Obtiene el campo de texto para el correo electrónico.
     * @return JTextField del correo.
     */
    public JTextField getCorreoTextField() {
        return correoTextField;
    }

    /**
     * Obtiene el campo de texto para el teléfono.
     * @return JTextField del teléfono.
     */
    public JTextField getTelefonoTextField() {
        return telefonoTextField;
    }

    /**
     * Obtiene el campo de texto para el nombre.
     * @return JTextField del nombre.
     */
    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    /**
     * Obtiene el menú item para idioma inglés.
     * @return JMenuItem de inglés.
     */
    public JMenuItem getInglesMenuItem() {
        return inglesMenuItem;
    }

    /**
     * Obtiene el menú item para idioma español.
     * @return JMenuItem de español.
     */
    public JMenuItem getEspanolMenuItem() {
        return espanolMenuItem;
    }

    /**
     * Obtiene el menú item para idioma francés.
     * @return JMenuItem de francés.
     */
    public JMenuItem getFrancesMenuItem() {
        return francesMenuItem;
    }

    /**
     * Obtiene el menú item para idioma alemán.
     * @return JMenuItem de alemán.
     */
    public JMenuItem getAlemanMenuItem() {
        return alemanMenuItem;
    }

    /**
     * Obtiene el menú item para idioma italiano.
     * @return JMenuItem de italiano.
     */
    public JMenuItem getItalianoMenuItem() {
        return italianoMenuItem;
    }

    /**
     * Obtiene la etiqueta del código de la pregunta.
     * @return JLabel del código de la pregunta.
     */
    public JLabel getLblPreguntaCodigo() {
        return lblPreguntaCodigo;
    }
}
