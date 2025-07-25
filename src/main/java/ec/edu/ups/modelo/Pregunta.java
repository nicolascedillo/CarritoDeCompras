package ec.edu.ups.modelo;

public class Pregunta {
    private int codigo;
    private String enunciado;

    /**
     * Constructor que recibe el código y el enunciado de la pregunta.
     * @param codigo Código de la pregunta.
     * @param enunciado Texto de la pregunta.
     */
    public Pregunta(int codigo, String enunciado) {
        this.codigo = codigo;
        this.enunciado = enunciado;
    }

    /**
     * Obtiene el código de la pregunta.
     * @return Código de la pregunta.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la pregunta.
     * @param codigo Código a asignar.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el enunciado de la pregunta.
     * @return Texto de la pregunta.
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Establece el enunciado de la pregunta.
     * @param enunciado Texto a asignar.
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    /**
     * Retorna una representación en texto de la pregunta.
     * @return String representando la pregunta.
     */
    @Override
    public String toString() {
        return "Pregunta{" +
                "codigo=" + codigo +
                ", enunciado='" + enunciado + '\'' +
                '}';
    }
}
