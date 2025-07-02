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

    public RegistraseView(MensajeInternacionalizacionHandler mIH) {
        setTitle(mIH.get("registro.titulo"));
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setResizable(false);

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

    public void setUsuarioTextField(JTextField usuarioTextField) {
        this.usuarioTextField = usuarioTextField;
    }

    public JLabel getLblContrasena() {
        return lblContrasena;
    }

    public void setLblContrasena(JLabel lblContrasena) {
        this.lblContrasena = lblContrasena;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    public JLabel getLblPreguntaCodigo() {
        return lblPreguntaCodigo;
    }

    public void setLblPreguntaCodigo(JLabel lblPreguntaCodigo) {
        this.lblPreguntaCodigo = lblPreguntaCodigo;
    }

    public JLabel getLblTituloPreguntas() {
        return lblTituloPreguntas;
    }

    public void setLblTituloPreguntas(JLabel lblTituloPreguntas) {
        this.lblTituloPreguntas = lblTituloPreguntas;
    }

    public JLabel getLblEnunciado() {
        return lblEnunciado;
    }

    public void setLblEnunciado(JLabel lblEnunciado) {
        this.lblEnunciado = lblEnunciado;
    }

    public JTextField getRespuestaTextField() {
        return respuestaTextField;
    }

    public void setRespuestaTextField(JTextField respuestaTextField) {
        this.respuestaTextField = respuestaTextField;
    }

    public JButton getSiguienteButton() {
        return siguienteButton;
    }

    public void setSiguienteButton(JButton siguienteButton) {
        this.siguienteButton = siguienteButton;
    }

    public JButton getGuardarButton() {
        return guardarButton;
    }

    public void setGuardarButton(JButton guardarButton) {
        this.guardarButton = guardarButton;
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

    public void setAnioTextField(JTextField anioTextField) {
        this.anioTextField = anioTextField;
    }

    public JComboBox getDiaComboBox() {
        return diaComboBox;
    }

    public void setDiaComboBox(JComboBox diaComboBox) {
        this.diaComboBox = diaComboBox;
    }

    public JComboBox getMesComboBox() {
        return mesComboBox;
    }

    public void setMesComboBox(JComboBox mesComboBox) {
        this.mesComboBox = mesComboBox;
    }

    public JLabel getLblAnio() {
        return lblAnio;
    }

    public void setLblAnio(JLabel lblAnio) {
        this.lblAnio = lblAnio;
    }

    public JLabel getLblMes() {
        return lblMes;
    }

    public void setLblMes(JLabel lblMes) {
        this.lblMes = lblMes;
    }

    public JLabel getLblDia() {
        return lblDia;
    }

    public void setLblDia(JLabel lblDia) {
        this.lblDia = lblDia;
    }

    public JLabel getLblNacimiento() {
        return lblNacimiento;
    }

    public void setLblNacimiento(JLabel lblNacimiento) {
        this.lblNacimiento = lblNacimiento;
    }

    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JTextField getCorreoTextField() {
        return correoTextField;
    }

    public void setCorreoTextField(JTextField correoTextField) {
        this.correoTextField = correoTextField;
    }

    public JTextField getTelefonoTextField() {
        return telefonoTextField;
    }

    public void setTelefonoTextField(JTextField telefonoTextField) {
        this.telefonoTextField = telefonoTextField;
    }

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public void setNombreTextField(JTextField nombreTextField) {
        this.nombreTextField = nombreTextField;
    }
}
