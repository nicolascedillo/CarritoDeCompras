package ec.edu.ups.vista.login;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class TipoAlmaceamientoView extends JFrame{
    private JLabel tituloLabel;
    private JButton memoriaButton;
    private JButton archivoButton;
    private JPanel panelPrincipal;
    private JLabel oLabel;
    private JLabel bienvenidaLabel;
    private JLabel enLabel;
    private JFileChooser fileChooser;
    private MensajeInternacionalizacionHandler mIH;
    private JMenu idiomaMenu;
    private JMenuBar menuBar;
    private JMenuItem inglesMenuItem;
    private JMenuItem espanolMenuItem;
    private JMenuItem francesMenuItem;
    private JMenuItem alemanMenuItem;
    private JMenuItem italianoMenuItem;

    public TipoAlmaceamientoView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 330);
        setContentPane(panelPrincipal);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

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

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());

    }

    public void cambiarIdioma(String idioma, String pais) {
        mIH.setLenguaje(idioma, pais);
        setTitle(mIH.get("tipoalmacenamiento.titulo"));
        tituloLabel.setText(mIH.get("tipoalmacenamiento.frase"));
        memoriaButton.setText(mIH.get("tipoalmacenamiento.memoria"));
        archivoButton.setText(mIH.get("tipoalmacenamiento.archivo"));
        oLabel.setText(mIH.get("tipoalmacenamiento.o"));
        bienvenidaLabel.setText(mIH.get("login.titulo"));
        idiomaMenu.setText(mIH.get("menu.idiomas"));
        enLabel.setText(mIH.get("tipoalmacenamiento.en"));

    }

    public JButton getMemoriaButton() {
        return memoriaButton;
    }

    public JButton getArchivoButton() {
        return archivoButton;
    }

    public JLabel getTituloLabel() {
        return tituloLabel;
    }

    public String obetenerRutaArchivo() {
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
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
