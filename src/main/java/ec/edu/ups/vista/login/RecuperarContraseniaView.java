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

    /**
     * Constructor de la ventana para recuperar contraseña.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
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
        setTitle(mIH.get("recuperacion.titulo"));
        lblTituloPreguntas.setText(mIH.get("registro.titulo.preguntas"));
        siguienteButton.setText(mIH.get("ventana.siguiente"));
        restablecerButton.setText(mIH.get("ventana.restablecer"));
    }

    /**
     * Obtiene la etiqueta del título de preguntas.
     * @return JLabel del título de preguntas.
     */
    public JLabel getLblTituloPreguntas() {
        return lblTituloPreguntas;
    }

    /**
     * Obtiene la etiqueta del código de la pregunta.
     * @return JLabel del código de la pregunta.
     */
    public JLabel getLblPreguntaCodigo() {
        return lblPreguntaCodigo;
    }

    /**
     * Obtiene la etiqueta del enunciado de la pregunta.
     * @return JLabel del enunciado.
     */
    public JLabel getLblEnunciado() {
        return lblEnunciado;
    }

    /**
     * Obtiene el campo de texto para la respuesta.
     * @return JPasswordField de la respuesta.
     */
    public JPasswordField getRespuestaTextField() {
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
     * Obtiene el botón para restablecer la contraseña.
     * @return JButton de restablecer.
     */
    public JButton getRestablecerButton() {
        return restablecerButton;
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
     * Obtiene el menú item para idioma inglés.
     * @return JMenuItem de inglés.
     */
    public JMenuItem getInglesMenuItem() {
        return inglesMenuItem;
    }
}
