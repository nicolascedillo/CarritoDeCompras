package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {

    /**
     * Autentica un usuario verificando su username y password.
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return El usuario autenticado o null si no existe.
     */
    Usuario autenticar(String username, String password);

    /**
     * Crea un nuevo usuario y lo almacena en el sistema.
     * @param usuario Usuario a agregar.
     */
    void crear(Usuario usuario);

    /**
     * Elimina un usuario por su username.
     * @param username Nombre de usuario a eliminar.
     */
    void eliminar(String username);

    /**
     * Busca un usuario por su username.
     * @param username Nombre de usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    Usuario buscarPorUsername(String username);

    /**
     * Actualiza los datos de un usuario existente.
     * @param usuario Usuario con los datos actualizados.
     */
    void actualizar(Usuario usuario);

    /**
     * Lista todos los usuarios almacenados en el sistema.
     * @return Lista de todos los usuarios.
     */
    List<Usuario> listarTodos();

    /**
     * Lista todos los usuarios que tienen un rol específico.
     * @param rol Rol de los usuarios a listar.
     * @return Lista de usuarios con el rol especificado.
     */
    List<Usuario> listarPorRol(Rol rol);

}
