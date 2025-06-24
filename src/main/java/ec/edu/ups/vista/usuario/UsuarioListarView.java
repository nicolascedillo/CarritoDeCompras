package ec.edu.ups.vista.usuario;

import ec.edu.ups.modelo.Usuario;

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
    private DefaultTableModel modelo;

    public UsuarioListarView() {
        super("Listar Usuarios", true, true, true, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Username", "Contraseña", "Rol"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        nombreTextField.setText("");
    }

    public void cargarDatosListar(List<Usuario> usuarios){
        modelo.setRowCount(0);
        for (Usuario usuario : usuarios) {
            Object[] fila = {
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRol()
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
