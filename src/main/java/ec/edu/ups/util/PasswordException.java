package ec.edu.ups.util;

/**
 * Excepción lanzada cuando la contraseña de un usuario no cumple las reglas de seguridad.
 */
public class PasswordException extends RuntimeException {
    /**
     * Constructor que recibe el mensaje de error.
     * @param message Mensaje descriptivo del error de validación.
     */
    public PasswordException(String message) {
        super(message);
    }
}
