package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

import java.util.List;

public interface PreguntaDAO {

    /**
     * Crea una nueva pregunta y la almacena en el sistema.
     * @param codigo Código de la pregunta.
     * @param pregunta Texto de la pregunta.
     */
    void crear(int codigo,String pregunta);

    /**
     * Busca una pregunta por su código.
     * @param codigo Código de la pregunta a buscar.
     * @return La pregunta encontrada o null si no existe.
     */
    Pregunta buscarPorCodigo(int codigo);

    /**
     * Lista todas las preguntas almacenadas en el sistema.
     * @return Lista de todas las preguntas.
     */
    List<Pregunta> listar();
}
