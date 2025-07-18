package ec.edu.ups.dao.implArchivos;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class UsuarioDAOArchivoTexto implements UsuarioDAO {
    private String rutaArchivo;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    /**
     * Constructor de UsuarioDAOArchivoTexto.
     * Inicializa la ruta del archivo de texto para usuarios y crea un usuario de ejemplo si el archivo no existe.
     *
     * @param rutaArchivo Ruta base para almacenar el archivo de usuarios.
     */
    public UsuarioDAOArchivoTexto(String rutaArchivo) {
        File directorio = new File(rutaArchivo + "\\CarritoCompras");
        if (!directorio.exists()){
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + directorio.getAbsolutePath());
            } else {
                System.out.println("Error al crear el directorio: " + directorio.getAbsolutePath());
            }
        }
        this.rutaArchivo = directorio.getAbsolutePath() + "\\usuarios.txt";
        File archivo = new File(this.rutaArchivo);
        if (!archivo.exists()) {
            try{
                archivo.createNewFile();
                crear(new Usuario("0107456022", "Nicol@", Rol.ADMINISTRADOR,"Nicolas Cedillo Admin", "nicocedillo15@gmail.com", "0991819287", new GregorianCalendar(2006, GregorianCalendar.JULY, 18)));
            }catch (IOException e) {
                System.out.println("Error al crear el archivo de usuarios: " + e.getMessage());
            }
        }
    }


    /**
     * Autentica un usuario verificando su username y password en el archivo de texto.
     * Retorna el usuario si existe y las credenciales coinciden.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return El usuario autenticado o null si no existe.
     */
    @Override
    public Usuario autenticar(String username, String password) {
        Usuario usuario = null;
        try {
            abrirReader();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                usuario = aUsuarioDeString(separarUsuarioLinea(linea));
                if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                    return usuario;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        finally {
            cerrarReader();
        }
        return null;
    }

    /**
     * Crea un nuevo usuario y lo agrega al archivo de texto.
     * Escribe el usuario al final del archivo.
     *
     * @param usuario Usuario a agregar.
     */
    @Override
    public void crear(Usuario usuario) {
        try {
            abrirWriter(true);
            bufferedWriter.write(usuario.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        } finally {
            cerrarWriter();
        }
    }

    /**
     * Busca un usuario por su username en el archivo de texto.
     * Retorna el usuario si existe, o null si no se encuentra.
     *
     * @param username Nombre de usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        Usuario usuario;
        try {
            abrirReader();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                usuario = aUsuarioDeString(separarUsuarioLinea(linea));
                if (usuario.getUsername().equals(username)) {
                    return usuario;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        } finally {
            cerrarReader();
        }
        return null;
    }

    /**
     * Lista todos los usuarios almacenados en el archivo de texto.
     * Retorna la lista completa de usuarios registrados.
     *
     * @return Lista de todos los usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            abrirReader();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                usuarios.add(aUsuarioDeString(separarUsuarioLinea(linea)));
            }
        } catch (IOException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        } finally {
            cerrarReader();
        }
        return usuarios;
    }

    /**
     * Lista todos los usuarios que tienen un rol específico.
     * Actualmente no implementado.
     *
     * @param rol Rol de los usuarios a listar.
     * @return Lista de usuarios con el rol especificado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        return null;
    }

    /**
     * Actualiza los datos de un usuario existente en el archivo de texto.
     * Sobrescribe los datos del usuario en la posición correspondiente.
     *
     * @param usuario Usuario con los datos actualizados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        try {
            abrirWriter(false);
            for (Usuario u : usuarios) {
                if (u.getUsername().equals(usuario.getUsername())) {
                    u = usuario;
                }
                bufferedWriter.write(u.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        } finally {
            cerrarWriter();
        }
    }

    /**
     * Elimina un usuario del archivo de texto por su username.
     * Elimina la línea correspondiente al usuario.
     *
     * @param username Nombre de usuario a eliminar.
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = listarTodos();
        try {
            abrirWriter(false);
            for (Usuario u : usuarios) {
                if (!u.getUsername().equals(username)) {
                    bufferedWriter.write(u.toString());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        } finally {
            cerrarWriter();
        }
    }

    /**
     * Abre el archivo de texto para escritura.
     * Inicializa el objeto BufferedWriter en modo append o sobrescritura.
     *
     * @param append Si es true, agrega al final; si es false, sobrescribe el archivo.
     */
    private void abrirWriter(boolean append) {
        try{
            fileWriter = new FileWriter(rutaArchivo, append);
            bufferedWriter = new BufferedWriter(fileWriter);
        }catch(IOException e){
            System.out.println("Error al abrir el archivo para escritura: " + e.getMessage());
            bufferedWriter = null;
            fileWriter = null;
        }
    }

    /**
     * Cierra el archivo de texto de escritura si está abierto.
     */
    private void cerrarWriter() {
        try{
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (fileWriter != null){
                fileWriter.close();
            }
        }catch(IOException e){
            System.out.println("Error al cerrar el archivo: " + e.getMessage());
        }
    }

    /**
     * Abre el archivo de texto para lectura.
     * Inicializa el objeto BufferedReader.
     */
    private void abrirReader(){
        try{
            fileReader = new FileReader(rutaArchivo);
            bufferedReader = new BufferedReader(fileReader);
        }catch(IOException e){
            System.out.println("Error al abrir el archivo para lectura: " + e.getMessage());
            bufferedReader = null;
            fileReader = null;
        }
    }

    /**
     * Cierra el archivo de texto de lectura si está abierto.
     */
    private void cerrarReader() {
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        }catch(IOException e){
            System.out.println("Error al cerrar el archivo: " + e.getMessage());
        }
    }

    /**
     * Convierte un arreglo de partes en un objeto Usuario.
     * Parsea los datos del usuario, sus carritos y preguntas respondidas desde las cadenas.
     *
     * @param partes Arreglo de cadenas que representa los datos del usuario.
     * @return El objeto Usuario construido a partir de las partes.
     */
    private Usuario aUsuarioDeString(String[] partes) {
        String[] atributos = partes[0].split(",");
        String username = atributos[0];
        String password = atributos[1];
        Rol rol = Rol.valueOf(atributos[2]);
        String nombre = atributos[3];
        String telefono = atributos[4];
        String correo = atributos[5];
        GregorianCalendar fechaNacimiento = new GregorianCalendar();
        fechaNacimiento.setTimeInMillis(Long.parseLong(atributos[6]));
        Usuario usuario = new Usuario(username, password, rol, nombre, telefono, correo, fechaNacimiento);
        // Carritos
        String carritosRaw = partes[1];
        if (carritosRaw != null && !carritosRaw.equals("[]")) {
            carritosRaw = carritosRaw.trim();
            if (carritosRaw.startsWith("[")) carritosRaw = carritosRaw.substring(1);
            if (carritosRaw.endsWith("]")) carritosRaw = carritosRaw.substring(0, carritosRaw.length()-1);

            if (!carritosRaw.isEmpty()) {
                String[] carritosArr = carritosRaw.split(",(?=\\s*\\d+_)");
                for (String carritoStr : carritosArr) {
                    carritoStr = carritoStr.trim();
                    if (carritoStr.isEmpty()) continue;
                    Carrito carrito = aCarritoDeString(carritoStr, usuario);
                    if (carrito != null) usuario.getCarritos().add(carrito);
                }
            }
        }
        // Preguntas respondidas
        String preguntasRaw = partes[2];
        if (preguntasRaw != null && !preguntasRaw.equals("[]")) {
            preguntasRaw = preguntasRaw.trim();
            if (preguntasRaw.startsWith("[")) preguntasRaw = preguntasRaw.substring(1);
            if (preguntasRaw.endsWith("]")) preguntasRaw = preguntasRaw.substring(0, preguntasRaw.length()-1);

            if (!preguntasRaw.isEmpty()) {
                String[] preguntasArr = preguntasRaw.split(",");
                for (String preguntaStr : preguntasArr) {
                    preguntaStr = preguntaStr.trim();
                    if (!preguntaStr.isEmpty()) {
                        String[] partesPregunta = preguntaStr.split(":");
                        if (partesPregunta.length >= 3) {
                            int codigoPregunta = Integer.parseInt(partesPregunta[0].trim());
                            String textoPregunta = partesPregunta[1].trim();
                            String respuesta = partesPregunta[2].trim();
                            Pregunta pregunta = new Pregunta(codigoPregunta, textoPregunta);
                            PreguntaRespondida preguntaRespondida = new PreguntaRespondida(pregunta, respuesta);
                            usuario.getPreguntasVerificacion().add(preguntaRespondida);
                        }
                    }
                }
            }
        }
        return usuario;
    }

    /**
     * Convierte una cadena de texto en un objeto Carrito.
     * Parsea los datos del carrito y sus productos desde la cadena.
     *
     * @param s Cadena de texto que representa el carrito.
     * @param usuario Usuario asociado al carrito (puede ser null).
     * @return El objeto Carrito construido a partir de la cadena.
     */
    private Carrito aCarritoDeString(String s, Usuario usuario) {
        String[] partes = s.split("_");
        if (partes.length < 4) {
            return null;
        }
        Carrito carrito;
        if (usuario != null) {
            carrito = new Carrito(usuario);
        } else {
            carrito = new Carrito();
            carrito.setUsuario(new Usuario(partes[1]));
        }
        int codigo = Integer.parseInt(partes[0]);
        carrito.setUsuario(new Usuario(partes[1]));
        long fechaMillis = Long.parseLong(partes[2]);
        String itemsStr = partes[3];
        if (itemsStr.trim().startsWith("[") && itemsStr.endsWith("]")) {
            itemsStr = itemsStr.substring(1, itemsStr.length()-1);
        }
        if (!itemsStr.isEmpty()) {
            if (itemsStr.trim().startsWith("[") && itemsStr.endsWith("]")) {
                itemsStr = itemsStr.substring(1, itemsStr.length()-1);
            }
            String[] itemArr = itemsStr.split(",");
            for (String item : itemArr) {
                if (!item.isEmpty()) {
                    String[] itemPartes = item.split(";");
                    if (itemPartes.length == 4) {
                        // Limpieza de corchetes
                        for (int i = 0; i < itemPartes.length; i++) {
                            itemPartes[i] = itemPartes[i].replace("[", "").replace("]", "").trim();
                        }
                        int prodCodigo = Integer.parseInt(itemPartes[0]);
                        String prodNombre = itemPartes[1];
                        double prodPrecio = Double.parseDouble(itemPartes[2]);
                        int cantidad = Integer.parseInt(itemPartes[3]);
                        Producto producto = new Producto(prodCodigo, prodNombre, prodPrecio);
                        carrito.agregarProducto(producto, cantidad);
                    }
                }
            }
        }
        carrito.setCodigo(codigo);
        GregorianCalendar fechaCreacion = new GregorianCalendar();
        fechaCreacion.setTimeInMillis(fechaMillis);
        carrito.setFechaCreacion(fechaCreacion);
        return carrito;
    }

    /**
     * Separa una línea de texto en partes correspondientes a los campos de usuario, carritos y preguntas.
     *
     * @param linea Línea de texto que representa un usuario.
     * @return Arreglo de cadenas con los campos separados.
     */
    private String[] separarUsuarioLinea(String linea) {

        String[] campos = new String[7];
        int campoActual = 0;
        int start = 0;
        for (int i = 0; i < linea.length(); i++) {
            if (linea.charAt(i) == ',' && campoActual < 6) {
                campos[campoActual++] = linea.substring(start, i);
                start = i + 1;
            }
        }

        int idxCorchete = linea.indexOf('[', start);
        if (idxCorchete == -1) {
            campos[6] = linea.substring(start).trim();
        } else {
            campos[6] = linea.substring(start, idxCorchete).trim();
        }

        String carritosStr = "[]";
        String preguntasStr = "[]";

        // Bloque carritos
        int openCarrito = -1;
        int closeCarrito = -1;
        int level = 0;
        for (int i = 0; i < linea.length(); i++) {
            if (linea.charAt(i) == '[') {
                if (level == 0) openCarrito = i;
                level++;
            }
            if (linea.charAt(i) == ']') {
                level--;
                if (level == 0 && openCarrito != -1) {
                    closeCarrito = i;
                    break;
                }
            }
        }
        if (openCarrito != -1 && closeCarrito != -1) {
            carritosStr = linea.substring(openCarrito, closeCarrito + 1);
        }

        // Bloque preguntas
        int openPreg = -1, closePreg = -1, levelPreg = 0;
        for (int i = closeCarrito + 1; i < linea.length(); i++) {
            if (linea.charAt(i) == '[') {
                if (levelPreg == 0) openPreg = i;
                levelPreg++;
            }
            if (linea.charAt(i) == ']') {
                levelPreg--;
                if (levelPreg == 0 && openPreg != -1) {
                    closePreg = i;
                    break;
                }
            }
        }
        if (openPreg != -1 && closePreg != -1) {
            preguntasStr = linea.substring(openPreg, closePreg + 1);
        }

        // Une los campos del usuario
        StringBuilder usuarioStr = new StringBuilder();
        usuarioStr.append(campos[0] != null ? campos[0].trim() : "");
        for (int i = 1; i < 7; i++) {
            usuarioStr.append(",");
            usuarioStr.append(campos[i] != null ? campos[i].trim() : "");
        }

        return new String[]{usuarioStr.toString(), carritosStr, preguntasStr};
    }

}
