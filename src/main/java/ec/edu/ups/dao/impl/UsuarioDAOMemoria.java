package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {

    private List<Usuario> usuarios;

    /**
     * Constructor de UsuarioDAOMemoria.
     * Inicializa la lista de usuarios en memoria y agrega usuarios de ejemplo.
     */
    public UsuarioDAOMemoria() {
        this.usuarios = new ArrayList<>();
        crear(new Usuario("0107456022", "Nico@", Rol.ADMINISTRADOR,"Admin", "admin@gmail.com", "0934134431", new GregorianCalendar(1990, 1, 1)));
    }

    /**
     * Autentica un usuario verificando su username y password.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return El usuario autenticado o null si no existe.
     */
    @Override
    public Usuario autenticar(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo usuario y lo agrega a la lista en memoria.
     *
     * @param usuario Usuario a agregar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Elimina un usuario de la lista en memoria por su username.
     *
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Busca un usuario por su username.
     *
     * @param username Nombre de usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un usuario existente en la lista en memoria.
     *
     * @param usuario Usuario con los datos actualizados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        for(int i =0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    /**
     * Lista todos los usuarios almacenados en memoria.
     *
     * @return Lista de todos los usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Lista todos los usuarios que tienen un rol específico.
     *
     * @param rol Rol de los usuarios a listar.
     * @return Lista de usuarios con el rol especificado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosPorRol = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getRol() == rol) {
                usuariosPorRol.add(usuario);
            }
        }
        return usuariosPorRol;
    }

}
