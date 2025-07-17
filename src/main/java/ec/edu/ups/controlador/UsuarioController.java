package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.CedulaValidationException;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.PasswordException;
import ec.edu.ups.vista.login.LogInView;
import ec.edu.ups.vista.usuario.UsuarioCrearView;
import ec.edu.ups.vista.usuario.UsuarioEliminarView;
import ec.edu.ups.vista.usuario.UsuarioListarView;
import ec.edu.ups.vista.usuario.UsuarioModificarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private LogInView logInView;
    private UsuarioCrearView usuarioCrearView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioListarView usuarioListarView;
    private UsuarioModificarView usuarioModificarView;
    private final MensajeInternacionalizacionHandler mIH;
    private Usuario usuario;

    /**
     * Constructor para el controlador de usuario en la vista de inicio de sesión.
     * Inicializa los DAOs, la vista y el manejador de internacionalización.
     * Configura los eventos de la vista de LogIn.
     *
     * @param usuarioDAO DAO para operaciones de usuario.
     * @param logInView Vista de inicio de sesión.
     * @param mIH Manejador de internacionalización.
     */
    public UsuarioController(UsuarioDAO usuarioDAO, LogInView logInView, MensajeInternacionalizacionHandler mIH) {
        this.usuarioDAO = usuarioDAO;
        this.logInView = logInView;
        this.usuario = null;
        this.mIH = mIH;

        configurarEventosLogIn();
    }

    /**
     * Constructor para el controlador de usuario en las vistas de gestión de usuario.
     * Inicializa los DAOs, las vistas, el usuario autenticado y el manejador de internacionalización.
     * Configura los eventos de las vistas de crear, eliminar, listar y modificar usuario.
     *
     * @param usuarioDAO DAO para operaciones de usuario.
     * @param usuarioCrearView Vista para crear usuario.
     * @param usuarioEliminarView Vista para eliminar usuario.
     * @param usuarioListarView Vista para listar usuarios.
     * @param usuarioModificarView Vista para modificar usuario.
     * @param usuarioAutenticado Usuario autenticado actual.
     * @param mIH Manejador de internacionalización.
     */
    public UsuarioController(UsuarioDAO usuarioDAO,UsuarioCrearView usuarioCrearView, UsuarioEliminarView usuarioEliminarView,
                             UsuarioListarView usuarioListarView, UsuarioModificarView usuarioModificarView, Usuario usuarioAutenticado,
                             MensajeInternacionalizacionHandler mIH) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioCrearView = usuarioCrearView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioModificarView = usuarioModificarView;
        this.usuario = usuarioAutenticado;
        this.mIH = mIH;

        configurarEventosUsuarioCrear();
        configurarEventosUsuarioEliminar();
        configurarEventosUsuarioListar();
        configurarEventosUsuarioModificar();

    }


    /**
     * Configura los eventos para la vista de creación de usuario.
     * Maneja la lógica de validación y creación de usuario, así como el cierre de la vista.
     */
    private void configurarEventosUsuarioCrear() {

        usuarioCrearView.getCrearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usuarioCrearView.getUsernameTextField().getText().isEmpty() ||
                        usuarioCrearView.getPasswordField().getText().isEmpty() ||
                        usuarioCrearView.getNombreTextField().getText().isEmpty() ||
                        usuarioCrearView.getTelefonoTextField().getText().isEmpty() ||
                        usuarioCrearView.getCorreoTextField().getText().isEmpty() ||
                        usuarioCrearView.getAnioTextField().getText().isEmpty() ||
                        usuarioCrearView.getDiaComboBox().getSelectedItem() == null ||
                        usuarioCrearView.getMesComboBox().getSelectedItem() == null ) {
                    usuarioCrearView.mostrarMensaje(mIH.get("mensaje.completar.campos"));
                    return;
                }

                try{

                    String username = usuarioCrearView.getUsernameTextField().getText();
                    String password = usuarioCrearView.getPasswordField().getText();
                    String nombre = usuarioCrearView.getNombreTextField().getText();
                    String telefono = usuarioCrearView.getTelefonoTextField().getText();
                    if (!telefono.matches("\\d{10}")) {
                        usuarioCrearView.mostrarMensaje(mIH.get("telefono.invalido"));
                        return;
                    }
                    String correo = usuarioCrearView.getCorreoTextField().getText();
                    if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        usuarioCrearView.mostrarMensaje(mIH.get("correo.invalido"));
                        return;
                    }
                    GregorianCalendar fecha = new GregorianCalendar();
                    int dia = (Integer) usuarioCrearView.getDiaComboBox().getSelectedItem();
                    int mes = -1;
                    String mesSeleccionado = (String) usuarioCrearView.getMesComboBox().getSelectedItem();
                    if (mesSeleccionado.equals(mIH.get("mes.enero"))) {
                        mes = 0;
                    } else if (mesSeleccionado.equals(mIH.get("mes.febrero"))) {
                        mes = 1;
                    } else if (mesSeleccionado.equals(mIH.get("mes.marzo"))) {
                        mes = 2;
                    } else if (mesSeleccionado.equals(mIH.get("mes.abril"))) {
                        mes = 3;
                    } else if (mesSeleccionado.equals(mIH.get("mes.mayo"))) {
                        mes = 4;
                    } else if (mesSeleccionado.equals(mIH.get("mes.junio"))) {
                        mes = 5;
                    } else if (mesSeleccionado.equals(mIH.get("mes.julio"))) {
                        mes = 6;
                    } else if (mesSeleccionado.equals(mIH.get("mes.agosto"))) {
                        mes = 7;
                    } else if (mesSeleccionado.equals(mIH.get("mes.septiembre"))) {
                        mes = 8;
                    } else if (mesSeleccionado.equals(mIH.get("mes.octubre"))) {
                        mes = 9;
                    } else if (mesSeleccionado.equals(mIH.get("mes.noviembre"))) {
                        mes = 10;
                    } else if (mesSeleccionado.equals(mIH.get("mes.diciembre"))) {
                        mes = 11;
                    } else {
                        usuarioCrearView.mostrarMensaje(mIH.get("mes.invalido"));
                        return;
                    }
                    int anio = Integer.parseInt(usuarioCrearView.getAnioTextField().getText());
                    if (anio < 1900 || anio > 2024) {
                        usuarioCrearView.mostrarMensaje(mIH.get("anio.invalido"));
                        return;
                    }
                    fecha.set(anio, mes, dia);

                    if (usuarioDAO.buscarPorUsername(username) != null) {
                        usuarioCrearView.mostrarMensaje(mIH.get("mensaje.usuario.existe"));
                        return;
                    }

                    Usuario usuario1 = new Usuario(Rol.USUARIO,
                            nombre, telefono, correo, fecha);
                    usuario1.setPassword(password);
                    usuario1.setUsername(username);
                    usuarioDAO.crear(usuario1);
                    usuarioCrearView.mostrarMensaje(mIH.get("mensaje.usuario.creado"));
                    usuarioCrearView.limpiarCampos();

                } catch (NumberFormatException ex) {
                    usuarioCrearView.mostrarMensaje(mIH.get("numberFormatException"));
                } catch (PasswordException ex) {
                    usuarioCrearView.mostrarMensaje(mIH.get("passwordException"));
                } catch (CedulaValidationException ex){
                    usuarioCrearView.mostrarMensaje(mIH.get("cedulaException"));
                }
            }
        });

    }

    /**
     * Configura los eventos para la vista de eliminación de usuario.
     * Permite buscar y eliminar usuarios.
     */
    private void configurarEventosUsuarioEliminar( ) {

        if(usuario.getRol().equals(Rol.USUARIO)){
            usuarioEliminarView.getBuscarButton().setEnabled(false);
            usuarioEliminarView.getUsernameTextField().setEnabled(false);
            usuarioEliminarView.getUsernameTextField().setText(usuario.getUsername());
            usuarioEliminarView.getContrasenaTextField().setText(usuario.getPassword());
            usuarioEliminarView.getNombreTextField().setText(usuario.getNombreCompleto());
            usuarioEliminarView.getTelefonoTextField().setText(usuario.getTelefono());
            usuarioEliminarView.getCorreoTextField().setText(usuario.getEmail());
            usuarioEliminarView.getAnioTextField().setText(usuario.getAnioNacimientoString());
            usuarioEliminarView.getDiaComboBox().setSelectedItem(Integer.parseInt(usuario.getDiaNacimientoString()));
            usuarioEliminarView.getMesComboBox().setSelectedIndex(Integer.parseInt(usuario.getMesNacimientoString()));
            usuarioEliminarView.getDiaComboBox().setEnabled(false);
            usuarioEliminarView.getMesComboBox().setEnabled(false);
            usuarioEliminarView.getBuscarButton().setVisible(false);
            usuarioEliminarView.getEliminarButton().setEnabled(true);
        }

        usuarioEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                try{
                    String username = usuarioEliminarView.getUsernameTextField().getText();
                    Usuario usuario = usuarioDAO.buscarPorUsername(username);
                    usuarioEliminarView.getContrasenaTextField().setText(usuario.getPassword());
                    usuarioEliminarView.getNombreTextField().setText(usuario.getNombreCompleto());
                    usuarioEliminarView.getTelefonoTextField().setText(usuario.getTelefono());
                    usuarioEliminarView.getCorreoTextField().setText(usuario.getEmail());
                    usuarioEliminarView.getAnioTextField().setText(usuario.getAnioNacimientoString());
                    usuarioEliminarView.getDiaComboBox().setSelectedItem(Integer.parseInt(usuario.getDiaNacimientoString()));
                    usuarioEliminarView.getMesComboBox().setSelectedIndex(Integer.parseInt(usuario.getMesNacimientoString()));
                    usuarioEliminarView.getEliminarButton().setEnabled(true);
                }catch(NullPointerException ex){
                    usuarioEliminarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }

            }
        });

        usuarioEliminarView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioEliminarView.getUsernameTextField().getText();
                Usuario usuarioAEliminar = usuarioDAO.buscarPorUsername(username);
                if (usuarioAEliminar == null) {
                    usuarioEliminarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                    return;
                }
                int respuesta = JOptionPane.showConfirmDialog(
                        usuarioEliminarView,
                        mIH.get("mensaje.usuario.confirmacion"),
                        mIH.get("mensaje.confirmacion.titulo"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (respuesta == JOptionPane.YES_OPTION) {
                    usuarioDAO.eliminar(usuarioAEliminar.getUsername());
                    usuarioEliminarView.mostrarMensaje(mIH.get("mensaje.usuario.eliminado"));
                    if (usuarioAEliminar.getRol().equals(Rol.USUARIO)) {
                        usuarioEliminarView.dispose();
                    } else {
                        usuarioEliminarView.limpiarCampos();
                    }
                }


            }
        });

    }

    /**
     * Configura los eventos para la vista de listado de usuarios.
     * Permite buscar usuarios por nombre y listar todos.
     */
    private void configurarEventosUsuarioListar(  ){

        usuarioListarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if( usuarioListarView.getNombreTextField().getText().isEmpty() ) {
                    return;
                }
                try{
                    String username = usuarioListarView.getNombreTextField().getText();
                    Usuario usuario = usuarioDAO.buscarPorUsername(username);
                    usuarioListarView.cargarDatosBusqueda(usuario);
                }catch (NullPointerException ex){
                    usuarioListarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));

                }

            }
        });

        usuarioListarView.getLisarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioListarView.cargarDatosListar(usuarioDAO.listarTodos());
            }
        });

    }

    /**
     * Configura los eventos para la vista de modificación de usuario.
     * Permite buscar y modificar usuarios.
     */
    private void configurarEventosUsuarioModificar( ) {

        if(usuario.getRol().equals(Rol.USUARIO)){
            usuarioModificarView.getBuscarButton().setEnabled(false);
            usuarioModificarView.getUsernameTextField().setEnabled(false);
            usuarioModificarView.getUsernameTextField().setText(usuario.getUsername());
            usuarioModificarView.getContrasenaTextField().setText(usuario.getPassword());
            usuarioModificarView.getNombreTextField().setText(usuario.getNombreCompleto());
            usuarioModificarView.getTelefonoTextField().setText(usuario.getTelefono());
            usuarioModificarView.getCorreoTextField().setText(usuario.getEmail());
            usuarioModificarView.getAnioTextField().setText(usuario.getAnioNacimientoString());
            usuarioModificarView.getDiaComboBox().setSelectedItem(Integer.parseInt(usuario.getDiaNacimientoString()));
            usuarioModificarView.getMesComboBox().setSelectedIndex(Integer.parseInt(usuario.getMesNacimientoString()));
            usuarioModificarView.getBuscarButton().setVisible(false);
        }

        usuarioModificarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if( usuarioModificarView.getUsernameTextField().getText().isEmpty() ) {
                    return;
                }
                try{
                    String username = usuarioModificarView.getUsernameTextField().getText();
                    Usuario usuario = usuarioDAO.buscarPorUsername(username);
                    usuarioModificarView.getContrasenaTextField().setText(usuario.getPassword());
                    usuarioModificarView.getNombreTextField().setText(usuario.getNombreCompleto());
                    usuarioModificarView.getTelefonoTextField().setText(usuario.getTelefono());
                    usuarioModificarView.getCorreoTextField().setText(usuario.getEmail());
                    usuarioModificarView.getAnioTextField().setText(usuario.getAnioNacimientoString());
                    usuarioModificarView.getDiaComboBox().setSelectedItem(Integer.parseInt(usuario.getDiaNacimientoString()));
                    usuarioModificarView.getMesComboBox().setSelectedIndex(Integer.parseInt(usuario.getMesNacimientoString()));


                }catch (NullPointerException ex){
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }

            }
        });

        usuarioModificarView.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String username = usuarioModificarView.getUsernameTextField().getText();
                    String password = usuarioModificarView.getContrasenaTextField().getText();
                    String nombre = usuarioModificarView.getNombreTextField().getText();
                    String telefono = usuarioModificarView.getTelefonoTextField().getText();
                    if (!telefono.matches("\\d{10}")) {
                        usuarioModificarView.mostrarMensaje(mIH.get("telefono.invalido"));
                        return;
                    }
                    String email = usuarioModificarView.getCorreoTextField().getText();
                    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        usuarioModificarView.mostrarMensaje(mIH.get("correo.invalido"));
                        return;
                    }
                    int anio = Integer.parseInt(usuarioModificarView.getAnioTextField().getText());
                    if (anio < 1900 || anio > 2024) {
                        usuarioModificarView.mostrarMensaje(mIH.get("anio.invalido"));
                        return;
                    }
                    int dia = Integer.parseInt(usuarioModificarView.getDiaComboBox().getSelectedItem().toString());
                    int mes = usuarioModificarView.getMesComboBox().getSelectedIndex();

                    if (username.isEmpty() || password.isEmpty() || nombre.isEmpty() ||
                            telefono.isEmpty() || email.isEmpty() || anio <= 1900) {
                        usuarioModificarView.mostrarMensaje(mIH.get("mensaje.completar.campos"));
                        return;
                    }

                    Usuario usuarioencontrado = usuarioDAO.buscarPorUsername(username);

                    usuarioencontrado.setPassword(password);
                    usuarioencontrado.setNombreCompleto(nombre);
                    usuarioencontrado.setTelefono(telefono);
                    usuarioencontrado.setEmail(email);
                    usuarioencontrado.setFechaNacimiento(new GregorianCalendar(anio, mes, dia));
                    usuarioDAO.actualizar(usuarioencontrado);
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.usuario.modificado"));
                    if(!usuario.getRol().equals(Rol.USUARIO)){
                        usuario = usuarioencontrado;
                        usuarioModificarView.limpiarCampos();
                    }

                }catch (NullPointerException ex){
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }catch (NumberFormatException ex){
                    usuarioModificarView.mostrarMensaje(mIH.get("numberFormatException"));
                } catch (PasswordException ex) {
                    usuarioModificarView.mostrarMensaje(mIH.get("passwordException"));
                }

            }
        });

    }

    /**
     * Configura los eventos para la vista de inicio de sesión (LogIn).
     * Permite autenticar usuarios y cerrar la aplicación.
     */
    private void configurarEventosLogIn() {
        logInView.getIniciarSesionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String username = logInView.getUsernameTextField().getText();
                    String password = logInView.getContrasenaPasswordField().getText();

                    usuario = usuarioDAO.autenticar(username, password);
                    if(usuario == null){
                        logInView.mostrarMensaje(mIH.get("mensaje.login.incorrecto"));
                    } else {
                        logInView.dispose();
                    }

                    logInView.getUsernameTextField().setText("");
                    logInView.getContrasenaPasswordField().setText("");


            }
        });

        logInView.getSalirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Cambia el idioma de todas las vistas de gestión de usuario.
     *
     * @param lenguaje Código de lenguaje (ej. "es", "en").
     * @param pais Código de país (ej. "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        usuarioCrearView.cambiarIdioma(lenguaje, pais);
        usuarioEliminarView.cambiarIdioma(lenguaje, pais);
        usuarioListarView.cambiarIdioma(lenguaje, pais);
        usuarioModificarView.cambiarIdioma(lenguaje, pais);
    }

    /**
     * Cambia el idioma de la vista de inicio de sesión.
     *
     * @param lenguaje Código de lenguaje (ej. "es", "en").
     * @param pais Código de país (ej. "EC", "US").
     */
    public void cambiarIdiomaLogIn(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        logInView.cambiarIdioma(lenguaje, pais);
    }

    /**
     * Obtiene el usuario autenticado actualmente.
     *
     * @return El usuario autenticado.
     */
    public Usuario getUsuarioAutenticado() {
        return usuario;
    }
}
