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

    /**
     * Constructor de la ventana para seleccionar el tipo de almacenamiento.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
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

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param idioma Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
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

    /**
     * Obtiene el botón para seleccionar almacenamiento en memoria.
     * @return JButton de memoria.
     */
    public JButton getMemoriaButton() {
        return memoriaButton;
    }

    /**
     * Obtiene el botón para seleccionar almacenamiento en archivo.
     * @return JButton de archivo.
     */
    public JButton getArchivoButton() {
        return archivoButton;
    }

    /**
     * Obtiene la etiqueta del título.
     * @return JLabel del título.
     */
    public JLabel getTituloLabel() {
        return tituloLabel;
    }

    /**
     * Abre el selector de archivos y retorna la ruta seleccionada.
     * @return Ruta absoluta seleccionada o null si se cancela.
     */
    public String obetenerRutaArchivo() {
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
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
