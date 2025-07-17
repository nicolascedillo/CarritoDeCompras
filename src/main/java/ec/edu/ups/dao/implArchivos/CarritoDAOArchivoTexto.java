package ec.edu.ups.dao.implArchivos;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CarritoDAOArchivoTexto implements CarritoDAO {
    private String rutaArchivo;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;


    /**
     * Constructor de CarritoDAOArchivoTexto.
     * Inicializa la ruta del archivo de texto para carritos.
     *
     * @param rutaBase Ruta base para almacenar el archivo de carritos.
     */
    public CarritoDAOArchivoTexto(String rutaBase) {
        File directorio = new File(rutaBase + "\\CarritoCompras");
        if (!directorio.exists()){
            directorio.mkdirs();
        }
        this.rutaArchivo = directorio.getAbsolutePath() + "\\carritos.txt";
    }

    /**
     * Crea un nuevo carrito y lo agrega al archivo de texto.
     * Escribe el carrito al final del archivo.
     *
     * @param carrito Carrito a agregar.
     */
    @Override
    public void crear(Carrito carrito) {
        try {
            abrirWriter(true);
            bufferedWriter.write(carrito.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error al crear carrito: " + e.getMessage());
        }finally {
            cerrarWriter();
        }
    }

    /**
     * Busca un carrito por su código en el archivo de texto.
     * Retorna el carrito si existe, o null si no se encuentra.
     *
     * @param codigo Código del carrito a buscar.
     * @return El carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        try  {
            abrirReader();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                // No se puede obtener el usuario aquí, así que se pasa null
                Carrito carrito = carritoFromString(linea, null);
                if (carrito != null && carrito.getCodigo() == codigo) {
                    return carrito;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar carrito: " + e.getMessage());
        }finally {
            cerrarReader();
        }
        return null;
    }

    /**
     * Busca un carrito por su código y usuario en el archivo de texto.
     * Retorna el carrito si existe y pertenece al usuario indicado.
     *
     * @param codigo Código del carrito.
     * @param usuario Usuario propietario del carrito.
     * @return El carrito encontrado o null si no existe.
     */
    @Override
    public Carrito buscarPorCodigoYUsuario(int codigo, Usuario usuario) {
        try  {
            abrirReader();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                Carrito carrito = carritoFromString(linea, usuario);
                if (carrito != null && carrito.getCodigo() == codigo && carrito.getUsuario() != null && carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                    return carrito;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar carrito: " + e.getMessage());
        }finally {
            cerrarReader();
        }
        return null;
    }

    /**
     * Actualiza un carrito existente en el archivo de texto.
     * Sobrescribe los datos del carrito en la posición correspondiente.
     *
     * @param carrito Carrito con los datos actualizados.
     */
    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        try {
            abrirWriter(false);
            for (Carrito c : carritos) {
                if (c.getCodigo() == carrito.getCodigo()) {
                    c = carrito;
                }
                bufferedWriter.write(c.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar carrito: " + e.getMessage());
        }finally {
            cerrarWriter();
        }
    }

    /**
     * Elimina un carrito del archivo de texto por su código.
     * Elimina la línea correspondiente al carrito.
     *
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listarTodos();
        try {
            abrirWriter(false);
            for (Carrito c : carritos) {
                if (c.getCodigo() != codigo) {
                    bufferedWriter.write(c.toString());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar carrito: " + e.getMessage());
        }finally {
            cerrarWriter();
        }
    }

    /**
     * Lista todos los carritos almacenados en el archivo de texto.
     * Retorna la lista completa de carritos registrados.
     *
     * @return Lista de todos los carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> carritos = new ArrayList<>();
        try {
            abrirReader();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                Carrito carrito = carritoFromString(linea, null);
                if (carrito != null) {
                    carritos.add(carrito);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar carritos: " + e.getMessage());
        }
        finally {
            cerrarReader();
        }
        return carritos;
    }

    /**
     * Lista todos los carritos asociados a un usuario específico en el archivo de texto.
     * Filtra y retorna los carritos que pertenecen al usuario indicado.
     *
     * @param usuario Usuario propietario de los carritos.
     * @return Lista de carritos del usuario.
     */
    @Override
    public List<Carrito> listarPorUsuario(Usuario usuario) {
        List<Carrito> carritos = new ArrayList<>();
        try {
            abrirReader();
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                Carrito carrito = carritoFromString(linea, usuario);
                if (carrito != null && carrito.getUsuario() != null && carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                    carritos.add(carrito);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar carritos por usuario: " + e.getMessage());
        }finally {
            cerrarReader();
        }
        return carritos;
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
     * Convierte una cadena de texto en un objeto Carrito.
     * Parsea los datos del carrito y sus productos desde la cadena.
     *
     * @param s Cadena de texto que representa el carrito.
     * @param usuario Usuario asociado al carrito (puede ser null).
     * @return El objeto Carrito construido a partir de la cadena.
     */
    private Carrito carritoFromString(String s, Usuario usuario) {
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
        long fechaMillis = Long.parseLong(partes[2]);
        String itemsStr = partes[3];
        if (itemsStr.startsWith("[") && itemsStr.endsWith("]")) {
            itemsStr = itemsStr.substring(1, itemsStr.length()-1);
        }
        if (!itemsStr.isEmpty()) {
            String[] itemArr = itemsStr.split(",");
            for (String item : itemArr) {
                if (!item.isEmpty()) {
                    String[] itemPartes = item.split(";");
                    if (itemPartes.length == 4) {
                        int prodCodigo = Integer.parseInt(itemPartes[0].trim());
                        String prodNombre = itemPartes[1];
                        double prodPrecio = Double.parseDouble(itemPartes[2].trim());
                        int cantidad = Integer.parseInt(itemPartes[3].trim());
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

}
