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


    public CarritoDAOArchivoTexto(String rutaBase) {
        File directorio = new File(rutaBase + "\\CarritoCompras");
        if (!directorio.exists()){
            directorio.mkdirs();
        }
        this.rutaArchivo = directorio.getAbsolutePath() + "\\carritos.txt";
    }

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
