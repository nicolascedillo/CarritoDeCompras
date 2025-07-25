package ec.edu.ups.dao.implArchivos;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOArchivoBinario implements PreguntaDAO {
    private final String rutaArchivo;
    private RandomAccessFile lectura;
    private RandomAccessFile escritura;
    private int longitudPregunta = 11;
    private int bytesPorPregunta = 4 + (longitudPregunta * 2); // 26

    /**
     * Constructor de PreguntaDAOArchivoBinario.
     * Inicializa la ruta del archivo binario y crea preguntas de ejemplo si el archivo no existe.
     *
     * @param rutaArchivo Ruta base para almacenar el archivo binario de preguntas.
     */
    public PreguntaDAOArchivoBinario(String rutaArchivo) {
        File directorio = new File(rutaArchivo + "\\CarritoCompras");
        if (!directorio.exists()){
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + directorio.getAbsolutePath());
            } else {
                System.out.println("Error al crear el directorio: " + directorio.getAbsolutePath());
            }
        }
        this.rutaArchivo = directorio.getAbsolutePath() + "\\preguntas.dat";
        File archivo = new File(this.rutaArchivo);
        if (!archivo.exists()) {
            try{
                archivo.createNewFile();
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
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de preguntas: " + e.getMessage());
            }
        }
    }

    /**
     * Crea una nueva pregunta y la agrega al archivo binario.
     * Escribe la pregunta al final del archivo.
     *
     * @param codigo Código de la pregunta.
     * @param pregunta Texto de la pregunta.
     */
    @Override
    public void crear(int codigo, String pregunta) {
        try {
            abrirEscritura();
            escritura.seek(escritura.length());
            escritura.writeInt(codigo);
            if (pregunta.length() > longitudPregunta) {
                pregunta = pregunta.substring(0, longitudPregunta);
            }
            while (pregunta.length() < longitudPregunta) {
                pregunta += " ";
            }
            escritura.writeChars(pregunta);
        } catch (IOException e) {
            System.out.println("Error al crear pregunta: " + e.getMessage());
        } finally {
            cerrarEscritura();
        }
    }

    /**
     * Busca una pregunta por su código en el archivo binario.
     * Retorna la pregunta si existe, o null si no se encuentra.
     *
     * @param codigo Código de la pregunta a buscar.
     * @return La pregunta encontrada o null si no existe.
     */
    @Override
    public Pregunta buscarPorCodigo(int codigo) {
        Pregunta pregunta = null;
        try {
            abrirLectura();
            long numRegistros = lectura.length() / bytesPorPregunta;
            if (codigo > 0 && codigo <= numRegistros) {
                lectura.seek((codigo - 1) * bytesPorPregunta);
                int codigoLeido = lectura.readInt();
                StringBuilder preguntaBuilder = new StringBuilder();
                for (int i = 0; i < longitudPregunta; i++) {
                    preguntaBuilder.append(lectura.readChar());
                }
                String texto = preguntaBuilder.toString().trim();
                if (codigoLeido != 0) {
                    pregunta = new Pregunta(codigoLeido, texto);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar pregunta: " + e.getMessage());
        } finally {
            cerrarLectura();
        }
        return pregunta;
    }

    /**
     * Lista todas las preguntas almacenadas en el archivo binario.
     * Retorna la lista completa de preguntas no eliminadas.
     *
     * @return Lista de todas las preguntas.
     */
    @Override
    public List<Pregunta> listar() {
        List<Pregunta> preguntas = new ArrayList<>();
        try {
            abrirLectura();
            long numRegistros = lectura.length() / bytesPorPregunta;
            for (int i = 0; i < numRegistros; i++) {
                lectura.seek(i * bytesPorPregunta);
                int codigo = lectura.readInt();
                System.out.println(codigo);
                StringBuilder preguntaBuilder = new StringBuilder();
                for (int j = 0; j < longitudPregunta; j++) {
                    preguntaBuilder.append(lectura.readChar());
                }
                String texto = preguntaBuilder.toString().trim();
                if (codigo != 0) {
                    preguntas.add(new Pregunta(codigo, texto));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar preguntas: " + e.getMessage());
        } finally {
            cerrarLectura();
        }
        return preguntas;
    }

    /**
     * Abre el archivo binario para lectura.
     * Inicializa el objeto RandomAccessFile en modo lectura.
     */
    private void abrirLectura() {
        try {
            lectura = new RandomAccessFile(rutaArchivo, "r");
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo para lectura: " + e.getMessage());
        }
    }

    /**
     * Abre el archivo binario para escritura.
     * Inicializa el objeto RandomAccessFile en modo lectura/escritura.
     */
    private void abrirEscritura() {
        try {
            escritura = new RandomAccessFile(rutaArchivo, "rw");
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo para escritura: " + e.getMessage());
        }
    }

    /**
     * Cierra el archivo binario de lectura si está abierto.
     */
    private void cerrarLectura() {
        try {
            if (lectura != null) {
                lectura.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo de lectura: " + e.getMessage());
        }
    }

    /**
     * Cierra el archivo binario de escritura si está abierto.
     */
    private void cerrarEscritura() {
        try {
            if (escritura != null) {
                escritura.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo de escritura: " + e.getMessage());
        }
    }

}
