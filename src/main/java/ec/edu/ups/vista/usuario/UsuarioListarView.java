package ec.edu.ups.vista.usuario;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Clase que representa la vista para listar y buscar usuarios en la aplicación.
 */
public class UsuarioListarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField nombreTextField;
    private JButton lisarButton;
    private JTable table1;
    private JButton buscarButton;
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para listar y buscar usuarios.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public UsuarioListarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.buscar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setFrameIcon(Icono.icono(Url.BUSCAR));

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        lisarButton.setIcon(Icono.icono(Url.LISTAR));

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Carga los datos de una lista de usuarios en la tabla.
     * @param usuarios Lista de usuarios a mostrar.
     */
    public void cargarDatosListar(List<Usuario> usuarios){
        modelo.setRowCount(0);
        String fecha = "sin fecha";
        for (Usuario usuario : usuarios) {
            if(usuario.getFechaNacimiento() != null){
                fecha = FormateadorUtils.formatearFecha(usuario.getFechaNacimiento().getTime()
                        ,mIH.getLocale());
            }
            Object[] fila = {
                usuario.getUsername(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getTelefono(),
                fecha
            };
            modelo.addRow(fila);
        }
    }

    /**
     * Carga los datos de un usuario buscado en la tabla.
     * @param usuario Usuario a mostrar.
     */
    public void cargarDatosBusqueda(Usuario usuario){
        modelo.setRowCount(0);
        String fecha = "sin fecha";
        if(usuario.getFechaNacimiento() != null){
            fecha = FormateadorUtils.formatearFecha(usuario.getFechaNacimiento().getTime()
                    ,mIH.getLocale());
        }
        Object[] fila = {
            usuario.getUsername(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getTelefono(),
                fecha

        };
        modelo.addRow(fila);
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais){
        mIH.setLenguaje(lenguaje, pais);
        Object[] columnas = {mIH.get("ventana.usuario.usuario"),
        mIH.get("ventana.usuario.nombre"),mIH.get("ventana.usuario.email"),
                mIH.get("ventana.usuario.telefono"), mIH.get("ventana.usuario.fecha")};
        modelo.setColumnIdentifiers(columnas);
        setTitle(mIH.get("menu.usuario.buscar"));
        lblTitulo.setText(mIH.get("ventana.usuario.buscar.titulo"));
        lblUsuario.setText(mIH.get("ventana.usuario.usuario"));
    }

    //GETTERS Y SETTERS

    /**
     * Obtiene el campo de texto para el nombre.
     * @return JTextField del nombre.
     */
    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    /**
     * Obtiene el botón para listar usuarios.
     * @return JButton de listar.
     */
    public JButton getLisarButton() {
        return lisarButton;
    }

    /**
     * Obtiene la tabla de usuarios.
     * @return JTable de usuarios.
     */
    public JTable getTable1() {
        return table1;
    }

    /**
     * Obtiene el botón para buscar usuarios.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

}
