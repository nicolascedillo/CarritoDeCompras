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

    public void cambiarTamanio(MensajeInternacionalizacionHandler idioma) {
        if (idioma.getLocale().getLanguage().equals("en")) {
            setSize(470, 275);
        } else if (idioma.getLocale().getLanguage().equals("es")) {
            setSize(520, 275);
        } else if (idioma.getLocale().getLanguage().equals("fr")) {
            setSize(570, 275);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getContrasenaPasswordField() {
        return contrasenaPasswordField;
    }

    public JButton getIniciarSesionButton() {
        return iniciarSesionButton;
    }

    public JButton getRegistrarseButton() {
        return registrarseButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mIH;
    }

    public JButton getOlvidadaButton() {
        return olvidadaButton;
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
}
