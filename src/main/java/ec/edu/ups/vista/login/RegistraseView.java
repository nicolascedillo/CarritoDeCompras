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

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

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

    private void cargarDias(JComboBox diaComboBox) {
        diaComboBox.removeAllItems();
        for (int i = 1; i <= 31; i++) {
            diaComboBox.addItem(i);
        }
    }

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

    //GETTERS Y SETTERS

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JTextField getUsuarioTextField() {
        return usuarioTextField;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JLabel getLblEnunciado() {
        return lblEnunciado;
    }

    public JTextField getRespuestaTextField() {
        return respuestaTextField;
    }

    public JButton getSiguienteButton() {
        return siguienteButton;
    }

    public JButton getGuardarButton() {
        return guardarButton;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public MensajeInternacionalizacionHandler getmIH() {
        return mIH;
    }

    public void setmIH(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
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

    public JTextField getCorreoTextField() {
        return correoTextField;
    }

    public JTextField getTelefonoTextField() {
        return telefonoTextField;
    }

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public JMenuItem getInglesMenuItem() {
        return inglesMenuItem;
    }

    public JMenuItem getEspanolMenuItem() {
        return espanolMenuItem;
    }

    public JMenuItem getFrancesMenuItem() {
        return francesMenuItem;
    }

    public JMenuItem getAlemanMenuItem() {
        return alemanMenuItem;
    }

    public JMenuItem getItalianoMenuItem() {
        return italianoMenuItem;
    }

    public JLabel getLblPreguntaCodigo() {
        return lblPreguntaCodigo;
    }
}
