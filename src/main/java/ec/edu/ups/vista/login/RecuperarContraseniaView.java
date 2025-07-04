package ec.edu.ups.vista.login;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class RecuperarContraseniaView extends JFrame{
    private JLabel lblTituloPreguntas;
    private JLabel lblPreguntaCodigo;
    private JLabel lblEnunciado;
    private JPasswordField respuestaTextField;
    private JButton siguienteButton;
    private JButton restablecerButton;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mIH;
    private JMenu idiomaMenu;
    private JMenuBar menuBar;
    private JMenuItem inglesMenuItem;
    private JMenuItem espanolMenuItem;
    private JMenuItem francesMenuItem;
    private JMenuItem alemanMenuItem;
    private JMenuItem italianoMenuItem;

    public RecuperarContraseniaView(MensajeInternacionalizacionHandler mIH) {
        setTitle(mIH.get("recuperacion.titulo"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400, 250);
        this.mIH = mIH;

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
        restablecerButton.setIcon(Icono.icono(Url.CREAR));

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

    public JLabel getLblPreguntaCodigo() {
        return lblPreguntaCodigo;
    }

    public JLabel getLblEnunciado() {
        return lblEnunciado;
    }

    public JPasswordField getRespuestaTextField() {
        return respuestaTextField;
    }

    public JButton getSiguienteButton() {
        return siguienteButton;
    }

    public JButton getRestablecerButton() {
        return restablecerButton;
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

    public JMenuItem getInglesMenuItem() {
        return inglesMenuItem;
    }
}
