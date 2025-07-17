package ec.edu.ups.vista.login;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField usernameTextField;
    private JPasswordField contrasenaPasswordField;
    private JButton iniciarSesionButton;
    private JButton registrarseButton;
    private JButton salirButton;
    private JLabel tituloLbl;
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JButton olvidadaButton;
    private MensajeInternacionalizacionHandler mIH;
    private JMenu idiomaMenu;
    private JMenuBar menuBar;
    private JMenuItem inglesMenuItem;
    private JMenuItem espanolMenuItem;
    private JMenuItem francesMenuItem;
    private JMenuItem alemanMenuItem;
    private JMenuItem italianoMenuItem;


    /**
     * Constructor de la ventana de inicio de sesión.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public LogInView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        setTitle(mIH.get("login.boton.iniciar"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cambiarTamanio(mIH);
        setLocationRelativeTo(null);
        setResizable(false);
        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());

        registrarseButton.setIcon(Icono.icono(Url.CREAR));
        iniciarSesionButton.setIcon(Icono.icono(Url.INICIAR));
        salirButton.setIcon(Icono.icono(Url.SALIR));

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

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        cambiarTamanio(mIH);
        setTitle(mIH.get("login.boton.iniciar"));
        tituloLbl.setText(mIH.get("login.titulo"));
        usernameLbl.setText(mIH.get("login.label.username"));
        passwordLbl.setText(mIH.get("login.label.password"));
        iniciarSesionButton.setText(mIH.get("login.boton.iniciar"));
        registrarseButton.setText(mIH.get("login.boton.registrarse"));
        salirButton.setText(mIH.get("login.boton.salir"));
        olvidadaButton.setText(mIH.get("login.boton.olvidar"));
    }

    /**
     * Cambia el tamaño de la ventana según el idioma seleccionado.
     * @param idioma Handler de internacionalización.
     */
    public void cambiarTamanio(MensajeInternacionalizacionHandler idioma) {
        if (idioma.getLocale().getLanguage().equals("en")) {
            setSize(470, 275);
        } else if (idioma.getLocale().getLanguage().equals("es")) {
            setSize(520, 275);
        } else if (idioma.getLocale().getLanguage().equals("fr")) {
            setSize(570, 275);
        } else if (idioma.getLocale().getLanguage().equals("de")) {
            setSize(570, 275);
        } else if (idioma.getLocale().getLanguage().equals("it")) {
            setSize(570, 275);
        } else {
            setSize(470, 275);
        }
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Obtiene el panel principal de la ventana.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene el campo de texto para el nombre de usuario.
     * @return JTextField del nombre de usuario.
     */
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    /**
     * Obtiene el campo de contraseña.
     * @return JPasswordField de la contraseña.
     */
    public JPasswordField getContrasenaPasswordField() {
        return contrasenaPasswordField;
    }

    /**
     * Obtiene el botón para iniciar sesión.
     * @return JButton de iniciar sesión.
     */
    public JButton getIniciarSesionButton() {
        return iniciarSesionButton;
    }

    /**
     * Obtiene el botón para registrarse.
     * @return JButton de registrarse.
     */
    public JButton getRegistrarseButton() {
        return registrarseButton;
    }

    /**
     * Obtiene el botón para salir.
     * @return JButton de salir.
     */
    public JButton getSalirButton() {
        return salirButton;
    }

    /**
     * Obtiene el handler de internacionalización.
     * @return Handler de internacionalización.
     */
    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mIH;
    }

    /**
     * Obtiene el botón para recuperar contraseña olvidada.
     * @return JButton de recuperación de contraseña.
     */
    public JButton getOlvidadaButton() {
        return olvidadaButton;
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
}
