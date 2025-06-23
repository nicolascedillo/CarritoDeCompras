package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LogInView;

import java.awt.event.ActionListener;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LogInView logInView;

    public UsuarioController(UsuarioDAO usuarioDAO, LogInView logInView) {
        this.usuarioDAO = usuarioDAO;
        this.logInView = logInView;
        this.usuario = null;

        configurarEventosEnVista();
    }

    private void configurarEventosEnVista(){
        logInView.getIniciarSesionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                autenticar();
            }
        });

        logInView.getRegistrarseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                registrarse();
            }
        });
    }

    private void autenticar(){
        String username = logInView.getUsernameTextField().getText();
        String password = logInView.getContrasenaPasswordField().getText();

        usuario = usuarioDAO.autenticar(username, password);
        if(usuario == null){
            logInView.mostrarMensaje("Usuario o contraseña incorrectos");
        } else {
            logInView.dispose();
        }
    }

    private void registrarse() {
        String username = logInView.getUsernameTextField().getText();
        String password = logInView.getContrasenaPasswordField().getText();

        if (username.isEmpty() || password.isEmpty()) {
            logInView.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            logInView.mostrarMensaje("El usuario ya existe");
            limpiarCampos();
            return;
        }

        usuario = new Usuario(username, password, Rol.USUARIO);
        usuarioDAO.crear(usuario);
        logInView.mostrarMensaje("Usuario registrado exitosamente");
    }

    private void limpiarCampos() {
        logInView.getUsernameTextField().setText("");
        logInView.getContrasenaPasswordField().setText("");
    }

    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}
