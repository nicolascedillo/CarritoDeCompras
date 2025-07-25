package ec.edu.ups.modelo;

/**
 * Clase que representa una pregunta respondida por un usuario.
 */
public class PreguntaRespondida {
    private Pregunta pregunta;
    private String respuesta;

    /**
     * Constructor que recibe la pregunta y la respuesta.
     * @param pregunta Pregunta respondida.
     * @param respuesta Respuesta dada.
     */
    public PreguntaRespondida(Pregunta pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la pregunta asociada.
     * @return Pregunta respondida.
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Establece la pregunta asociada.
     * @param pregunta Pregunta a asignar.
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Obtiene la respuesta dada a la pregunta.
     * @return Respuesta como String.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta dada a la pregunta.
     * @param respuesta Respuesta a asignar.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Retorna una representación en texto de la pregunta respondida.
     * @return String representando la pregunta y la respuesta.
     */
    @Override
    public String toString() {
        return pregunta.getCodigo() + ":" + pregunta.getEnunciado() + ":" + respuesta;
    }
}
