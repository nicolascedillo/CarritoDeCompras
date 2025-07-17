package ec.edu.ups.modelo;

public class PreguntaRespondida {
    private Pregunta pregunta;
    private String respuesta;

    public PreguntaRespondida(Pregunta pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return pregunta.getCodigo() + ":" + pregunta.getEnunciado() + ":" + respuesta;
    }
}
