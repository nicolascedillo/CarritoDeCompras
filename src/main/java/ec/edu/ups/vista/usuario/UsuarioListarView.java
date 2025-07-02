package ec.edu.ups.vista.usuario;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioListarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField nombreTextField;
    private JButton lisarButton;
    private JTable table1;
    private JButton buscarButton;
    private JButton salirButton;
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioListarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.usuario.buscar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setFrameIcon(Icono.icono(Url.BUSCAR));

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        lisarButton.setIcon(Icono.icono(Url.LISTAR));
        salirButton.setIcon(Icono.icono(Url.CERRAR));

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }


    public void cargarDatosListar(List<Usuario> usuarios){
        modelo.setRowCount(0);
        String fecha = "sin fecha";
        for (Usuario usuario : usuarios) {
            if(usuario.getFechaNacimiento() != null){
                FormateadorUtils.formatearFecha(usuario.getFechaNacimiento().getTime()
                        ,mIH.getLocale());
            }
            Object[] fila = {
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRol(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getTelefono(),
                fecha
            };
            modelo.addRow(fila);
        }
    }

    public void cargarDatosBusqueda(Usuario usuario){
        modelo.setRowCount(0);
        Object[] fila = {
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getRol()
        };
        modelo.addRow(fila);
    }

    public void cambiarIdioma(String lenguaje, String pais){
        mIH.setLenguaje(lenguaje, pais);
        Object[] columnas = {mIH.get("ventana.usuario.usuario"), mIH.get("ventana.usuario.contrasena"), mIH.get("ventana.usuario.rol"),
        mIH.get("ventana.usuario.nombre"),mIH.get("ventana.usuario.email"),
                mIH.get("ventana.usuario.telefono"), mIH.get("ventana.usuario.fecha")};
        modelo.setColumnIdentifiers(columnas);
        setTitle(mIH.get("menu.usuario.buscar"));
        lblTitulo.setText(mIH.get("ventana.usuario.buscar.titulo"));
        lblUsuario.setText(mIH.get("ventana.usuario.usuario"));
    }

    //GETTERS Y SETTERS

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public JButton getLisarButton() {
        return lisarButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }
}
