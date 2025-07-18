package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMeroria implements PreguntaDAO {
    private List<Pregunta> preguntas;

    /**
     * Constructor de PreguntaDAOMeroria.
     * Inicializa la lista de preguntas en memoria y agrega preguntas de ejemplo.
     */
    public PreguntaDAOMeroria() {
        preguntas = new ArrayList<>();
        crear(1,"pregunta1");
        crear(2,"pregunta2");
        crear(3,"pregunta3");
        crear(4,"pregunta4");
        crear(5,"pregunta5");
        crear(6,"pregunta6");
        crear(7,"pregunta7");
        crear(8,"pregunta8");
        crear(9,"pregunta9");
        crear(10,"pregunta10");
        crear(11,"pregunta11");
        crear(12,"pregunta12");
    }

    /**
     * Crea una nueva pregunta y la agrega a la lista en memoria.
     *
     * @param codigo Código de la pregunta.
     * @param pregunta Texto de la pregunta.
     */
    @Override
    public void crear(int codigo, String pregunta) {
        preguntas.add(new Pregunta(codigo, pregunta));
    }

    /**
     * Busca una pregunta por su código.
     *
     * @param codigo Código de la pregunta a buscar.
     * @return La pregunta encontrada o null si no existe.
     */
    @Override
    public Pregunta buscarPorCodigo(int codigo) {
        for (Pregunta pregunta : preguntas) {
            if (pregunta.getCodigo() == codigo) {
                return pregunta;
            }
        }
        return null;
    }

    /**
     * Lista todas las preguntas almacenadas en memoria.
     *
     * @return Lista de todas las preguntas.
     */
    @Override
    public List<Pregunta> listar() {
        return preguntas;
    }

}
