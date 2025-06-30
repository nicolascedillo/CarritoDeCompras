package ec.edu.ups.vista.login;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class RecuperarContraseniaView extends JFrame{
    private JLabel lblTituloPreguntas;
    private JLabel lblPreguntaCodigo;
    private JLabel lblEnunciado;
    private JTextField respuestaTextField;
    private JButton siguienteButton;
    private JButton restablecerButton;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mIH;

    public RecuperarContraseniaView(MensajeInternacionalizacionHandler mIH) {
        setTitle(mIH.get("recuperacion.titulo"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400, 200);
        this.mIH = mIH;

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("recuperacion.titulo"));
        lblTituloPreguntas.setText(mIH.get("registro.titulo.preguntas"));
        siguienteButton.setText(mIH.get("ventana.siguiente"));
        restablecerButton.setText(mIH.get("ventana.restablecer"));
    }

    //GETTERS Y SETTERS


    public JLabel getLblTituloPreguntas() {
        return lblTituloPreguntas;
    }

    public void setLblTituloPreguntas(JLabel lblTituloPreguntas) {
        this.lblTituloPreguntas = lblTituloPreguntas;
    }

    public JLabel getLblPreguntaCodigo() {
        return lblPreguntaCodigo;
    }

    public void setLblPreguntaCodigo(JLabel lblPreguntaCodigo) {
        this.lblPreguntaCodigo = lblPreguntaCodigo;
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

    public JButton getRestablecerButton() {
        return restablecerButton;
    }

    public void setRestablecerButton(JButton restablecerButton) {
        this.restablecerButton = restablecerButton;
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
