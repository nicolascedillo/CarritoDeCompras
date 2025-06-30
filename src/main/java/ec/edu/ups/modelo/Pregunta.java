package ec.edu.ups.modelo;

public class Pregunta {
    private int codigo;
    private String enunciado;

    public Pregunta(int codigo, String enunciado) {
        this.codigo = codigo;
        this.enunciado = enunciado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "codigo=" + codigo +
                ", enunciado='" + enunciado + '\'' +
                '}';
    }
}
