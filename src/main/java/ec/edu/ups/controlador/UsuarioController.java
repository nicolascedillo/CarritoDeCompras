package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.usuario.UsuarioCrearView;
import ec.edu.ups.vista.usuario.UsuarioEliminarView;
import ec.edu.ups.vista.usuario.UsuarioListarView;
import ec.edu.ups.vista.usuario.UsuarioModificarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioCrearView usuarioCrearView;
    private final UsuarioEliminarView usuarioEliminarView;
    private final UsuarioListarView usuarioListarView;
    private final UsuarioModificarView usuarioModificarView;

    public UsuarioController(UsuarioDAO usuarioDAO,UsuarioCrearView usuarioCrearView, UsuarioEliminarView usuarioEliminarView, UsuarioListarView usuarioListarView, UsuarioModificarView usuarioModificarView) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioCrearView = usuarioCrearView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioModificarView = usuarioModificarView;

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
                String password = usuarioCrearView.getContraseñaPasswordField().getText();
                Rol rol = Rol.USUARIO;

                if (username.isEmpty() || password.isEmpty()) {
                    usuarioCrearView.mostrarMensaje("Por favor, complete todos los campos.");
                    return;
                }

                if (usuarioDAO.buscarPorUsername(username) != null) {
                    usuarioCrearView.mostrarMensaje("El usuario ya existe");
                    return;
                }

                Usuario nuevoUsuario = new Usuario(username, password, rol);
                usuarioDAO.crear(nuevoUsuario);
                System.out.println("Usuario creado: " + usuarioDAO.listarTodos());
                usuarioCrearView.mostrarMensaje("Usuario creado exitosamente");
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
                    usuarioEliminarView.mostrarMensaje("Usuario no encontrado");
                }
            }
        });

        usuarioEliminarView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioEliminarView.getUsernameTextField().getText();
                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (usuario != null) {
                    usuarioDAO.eliminar(usuario.getUsername());
                    usuarioEliminarView.mostrarMensaje("Usuario eliminado exitosamente");
                    usuarioEliminarView.limpiarCampos();
                } else {
                    usuarioEliminarView.mostrarMensaje("Usuario no encontrado");
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
                    usuarioListarView.mostrarMensaje("Usuario no encontrado");
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
                    usuarioModificarView.mostrarMensaje("Usuario no encontrado");
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
                    usuarioModificarView.mostrarMensaje("Usuario modificado exitosamente");
                    usuarioModificarView.limpiarCampos();
                } else {
                    usuarioModificarView.mostrarMensaje("Usuario no encontrado");
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
