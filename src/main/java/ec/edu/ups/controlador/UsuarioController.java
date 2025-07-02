package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
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
    private final UsuarioCrearView usuarioCrearView;
    private final UsuarioEliminarView usuarioEliminarView;
    private final UsuarioListarView usuarioListarView;
    private final UsuarioModificarView usuarioModificarView;
    private MensajeInternacionalizacionHandler mIH;
    private Usuario usuario;

    public UsuarioController(UsuarioDAO usuarioDAO,
                             UsuarioCrearView usuarioCrearView,
                             UsuarioEliminarView usuarioEliminarView,
                             UsuarioListarView usuarioListarView,
                             UsuarioModificarView usuarioModificarView,
                             Usuario usuario,
                             MensajeInternacionalizacionHandler mIH) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioCrearView = usuarioCrearView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioModificarView = usuarioModificarView;
        this.usuario = usuario;
        this.mIH = mIH;

        configurarEventosUsuarioCrear();
        configurarEventosUsuarioEliminar();
        configurarEventosUsuarioListar();
        configurarEventosUsuarioModificar();
    }

    public void configurarEventosUsuarioCrear() {

        usuarioCrearView.getCrearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usuarioCrearView.getUsernameTextField().getText();
                String password = usuarioCrearView.getPasswordField().getText();
                Rol rol = Rol.USUARIO;

                if (username.isEmpty() || password.isEmpty()) {
                    usuarioCrearView.mostrarMensaje(mIH.get("mensaje.completar.campos"));
                    return;
                }

                if (usuarioDAO.buscarPorUsername(username) != null) {
                    usuarioCrearView.mostrarMensaje(mIH.get("mensaje.usuario.existe"));
                    return;
                }

                Usuario nuevoUsuario = new Usuario(username, password, rol);
                usuarioDAO.crear(nuevoUsuario);
                usuarioCrearView.mostrarMensaje(mIH.get("mensaje.usuario.creado"));
                usuarioCrearView.limpiarCampos();

            }
        });

        usuarioCrearView.getSalirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioCrearView.setVisible(false);
            }
        });
    }

    public void configurarEventosUsuarioEliminar() {

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
        }

        usuarioEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                String username = usuarioEliminarView.getUsernameTextField().getText();
                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (usuario != null) {
                    usuarioEliminarView.getContrasenaTextField().setText(usuario.getPassword());
                } else {
                    usuarioEliminarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }

            }
        });

        usuarioEliminarView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int respuesta = JOptionPane.showConfirmDialog(
                        usuarioEliminarView,
                        mIH.get("mensaje.usuario.confirmacion"),
                        mIH.get("mensaje.confirmacion.titulo"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                String username = usuarioEliminarView.getUsernameTextField().getText();
                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (respuesta == JOptionPane.YES_OPTION) {
                    usuarioDAO.eliminar(usuario.getUsername());
                    usuarioEliminarView.mostrarMensaje(mIH.get("mensaje.usuario.eliminado"));
                    if(usuario.getRol().equals(Rol.USUARIO)){
                        usuarioEliminarView.dispose();
                    } else {
                        usuarioEliminarView.limpiarCampos();
                    }
                } else {
                    usuarioEliminarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }

            }
        });

        usuarioEliminarView.getSalirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioEliminarView.setVisible(false);
            }
        });
    }

    public void configurarEventosUsuarioListar() {

        usuarioListarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if( usuarioListarView.getNombreTextField().getText().isEmpty() ) {
                    return;
                }
                String username = usuarioListarView.getNombreTextField().getText();
                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (usuario != null) {
                    usuarioListarView.cargarDatosBusqueda(usuario);
                } else {
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

        usuarioListarView.getSalirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioListarView.setVisible(false);
            }
        });
    }

    public void configurarEventosUsuarioModificar() {

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
                String username = usuarioModificarView.getUsernameTextField().getText();
                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (usuario != null) {
                    usuarioModificarView.getContrasenaTextField().setText(usuario.getPassword());
                } else {
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }

            }
        });

        usuarioModificarView.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usuarioModificarView.getUsernameTextField().getText();
                String password = usuarioModificarView.getContrasenaTextField().getText();
                String nombre = usuarioModificarView.getNombreTextField().getText();
                String telefono = usuarioModificarView.getTelefonoTextField().getText();
                String email = usuarioModificarView.getCorreoTextField().getText();
                int anio = Integer.parseInt(usuarioModificarView.getAnioTextField().getText());
                int dia = Integer.parseInt(usuarioModificarView.getDiaComboBox().getSelectedItem().toString());
                int mes = usuarioModificarView.getMesComboBox().getSelectedIndex();
                usuario.setFechaNacimiento(new GregorianCalendar(anio, mes, dia));

                if (username.isEmpty() || password.isEmpty() || nombre.isEmpty() ||
                        telefono.isEmpty() || email.isEmpty() || anio <= 1900) {
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.completar.campos"));
                    return;
                }

                Usuario usuarioencontrado = usuarioDAO.buscarPorUsername(username);

                if (usuarioencontrado != null) {
                    usuarioencontrado.setPassword(password);
                    usuarioencontrado.setNombreCompleto(nombre);
                    usuarioencontrado.setTelefono(telefono);
                    usuarioencontrado.setEmail(email);
                    usuarioencontrado.setFechaNacimiento(new GregorianCalendar(anio, mes, dia));
                    usuarioDAO.actualizar(usuarioencontrado);
                    usuario = usuarioencontrado;
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.usuario.modificado"));
                    if(!usuario.getRol().equals(Rol.USUARIO)){
                        usuarioModificarView.limpiarCampos();
                    }
                } else {
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }

            }
        });

        usuarioModificarView.getSalirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioModificarView.dispose();
            }
        });
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        usuarioCrearView.cambiarIdioma(lenguaje, pais);
        usuarioEliminarView.cambiarIdioma(lenguaje, pais);
        usuarioListarView.cambiarIdioma(lenguaje, pais);
        usuarioModificarView.cambiarIdioma(lenguaje, pais);
    }
}
