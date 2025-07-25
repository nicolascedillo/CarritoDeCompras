package ec.edu.ups.util;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Handler para la internacionalización de mensajes en la aplicación.
 * Permite obtener textos traducidos según el locale configurado.
 */
public class MensajeInternacionalizacionHandler {
    private ResourceBundle bundle;
    private Locale locale;

    /**
     * Constructor que inicializa el handler con el lenguaje y país especificados.
     * @param lenguaje Código de lenguaje (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el mensaje internacionalizado correspondiente a la clave dada.
     * @param key Clave del mensaje.
     * @return Texto traducido según el locale actual.
     */
    public String get(String key) {
        return bundle.getString(key);
    }

    /**
     * Cambia el lenguaje y país del handler, actualizando el locale y el bundle.
     * @param lenguaje Nuevo código de lenguaje.
     * @param pais Nuevo código de país.
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el locale actual del handler.
     * @return Locale configurado.
     */
    public Locale getLocale() {
        return locale;
    }
}
