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

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioCrearView usuarioCrearView;
    private final UsuarioEliminarView usuarioEliminarView;
    private final UsuarioListarView usuarioListarView;
    private final UsuarioModificarView usuarioModificarView;
    private MensajeInternacionalizacionHandler mIH;

    public UsuarioController(UsuarioDAO usuarioDAO,
                             UsuarioCrearView usuarioCrearView,
                             UsuarioEliminarView usuarioEliminarView,
                             UsuarioListarView usuarioListarView,
                             UsuarioModificarView usuarioModificarView,
                             MensajeInternacionalizacionHandler mIH) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioCrearView = usuarioCrearView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioModificarView = usuarioModificarView;
        this.mIH = mIH;

        configurarEventosUsuarioCrear();
        configurarEventosUsuarioEliminar();
        configurarEventosUsuarioListar();
        configurarEventosUsuarioModificar();
    }

    public void configurarEventosUsuarioCrear() {
        usuarioCrearView.getCrearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String username = usuarioCrearView.getUsernameTextField().getText();
                String password = usuarioCrearView.getContrasenaPasswordField().getText();
                Rol rol = Rol.USUARIO;

                if (username.isEmpty() || password.isEmpty()) {
                    usuarioCrearView.mostrarMensaje(mIH.get("mensaje.completar.campos"));
                    return;
                }

                if (usuarioDAO.buscarPorUsername(username) != null) {
                    usuarioCrearView.mostrarMensaje(mIH.get("mensaje.usuario,existe"));
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
                usuarioCrearView.dispose();
            }
        });
    }

    public void configurarEventosUsuarioEliminar() {
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
                    usuarioEliminarView.limpiarCampos();
                } else {
                    usuarioEliminarView.mostrarMensaje(mIH.get("mensaje.usuario.noencontrado"));
                }
            }
        });

        usuarioEliminarView.getSalirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioEliminarView.dispose();
            }
        });
    }

    public void configurarEventosUsuarioListar() {
        usuarioListarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                usuarioListarView.dispose();
            }
        });
    }

    public void configurarEventosUsuarioModificar() {
        usuarioModificarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (usuario != null) {
                    usuario.setPassword(password);
                    usuarioDAO.actualizar(usuario);
                    usuarioModificarView.mostrarMensaje(mIH.get("mensaje.usuario.modificado"));
                    usuarioModificarView.limpiarCampos();
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
}
