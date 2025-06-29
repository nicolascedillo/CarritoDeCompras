package ec.edu.ups.vista.login;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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
    private MensajeInternacionalizacionHandler mIH;

    public RegistraseView(MensajeInternacionalizacionHandler mIH) {
        setTitle(mIH.get("registro.titulo"));
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

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
}
