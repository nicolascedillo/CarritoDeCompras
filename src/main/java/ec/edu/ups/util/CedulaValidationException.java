package ec.edu.ups.util;

/**
 * Excepción lanzada cuando la cédula de un usuario no es válida.
 */
public class CedulaValidationException extends RuntimeException {
    /**
     * Constructor que recibe el mensaje de error.
     * @param message Mensaje descriptivo del error de validación.
     */
    public CedulaValidationException(String message) {
        super(message);
    }
}
