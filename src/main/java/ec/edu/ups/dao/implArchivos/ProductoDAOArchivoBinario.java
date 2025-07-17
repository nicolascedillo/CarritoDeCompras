package ec.edu.ups.dao.implArchivos;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOArchivoBinario implements ProductoDAO {
    private final String rutaArchivo;
    private RandomAccessFile lectura;
    private RandomAccessFile escritura;
    private int longitudNombre = 20;
    private int bytesPorProducto = 4 + ( longitudNombre *2) + 8; // 52


    public ProductoDAOArchivoBinario(String rutaArchivo) {
        File directorio = new File(rutaArchivo + "\\CarritoCompras");
        if (!directorio.exists()){
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + directorio.getAbsolutePath());
            } else {
                System.out.println("Error al crear el directorio: " + directorio.getAbsolutePath());
            }
        }
        this.rutaArchivo = directorio.getAbsolutePath() + "\\productos.dat";
        File archivo = new File(this.rutaArchivo);
        if (!archivo.exists()) {
            crear(new Producto( "Computadora", 500.0));
            crear(new Producto( "TV", 300.0));
            crear(new Producto( "Celular", 700.0));
            crear(new Producto( "Microfono", 40.0));
            crear(new Producto( "Teclado", 50.0));
            crear(new Producto( "Mouse", 20.0));
            crear(new Producto( "Monitor", 150.0));
            crear(new Producto( "Impresora", 120.0));
            crear(new Producto( "Tablet", 250.0));
            crear(new Producto( "Auriculares", 80.0));
        }
    }

    @Override
    public void crear(Producto producto){
        try{
            abrirEscritura();
            escritura.seek(escritura.length());
            escritura.writeInt(producto.getCodigo());
            String nombre = producto.getNombre();
            if (nombre.length() > longitudNombre) {
                nombre = nombre.substring(0, longitudNombre);
            }

            while (nombre.length() < longitudNombre) {
                nombre += " ";
            }
            escritura.writeChars(nombre);
            escritura.writeDouble(producto.getPrecio());
        }catch (IOException ex){
            System.out.println("Error al escribir el código del producto: " + ex.getMessage());
        }finally {
            cerrarEscritura();
        }
    }

    @Override
    public Producto buscarPorCodigo(int codigo){
        Producto producto = null;
        try {
            abrirLectura();
            long numRegistros = lectura.length() / bytesPorProducto;
            if (codigo > 0 && codigo <= numRegistros) {
                lectura.seek((codigo - 1) * bytesPorProducto);
                int codigoLeido = lectura.readInt();
                StringBuilder nombreBuilder = new StringBuilder();
                for (int j = 0; j < longitudNombre; j++) {
                    nombreBuilder.append(lectura.readChar());
                }
                String nombre = nombreBuilder.toString().trim();
                double precio = lectura.readDouble();
                producto = new Producto(codigoLeido, nombre, precio);
            }
        }catch (IOException ex){
            System.out.println("Error al buscar el producto por código: " + ex.getMessage());
        } finally {
            cerrarLectura();
        }
        return producto;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre){
        List<Producto> productos = new ArrayList<>();
        try {
            abrirLectura();
            long numRegistros = lectura.length() / bytesPorProducto;
            for (int i = 0; i < numRegistros; i++) {
                lectura.seek(i * bytesPorProducto);
                int codigo = lectura.readInt();
                StringBuilder nombreBuilder = new StringBuilder();
                for (int j = 0; j < longitudNombre; j++) {
                    nombreBuilder.append(lectura.readChar());
                }
                String nombreLeido = nombreBuilder.toString().trim();
                double precio = lectura.readDouble();
                if (codigo != 0 && nombreLeido.equalsIgnoreCase(nombre.trim())) {
                    productos.add(new Producto(codigo, nombreLeido, precio));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar productos por nombre: " + e.getMessage());
        } finally {
            cerrarLectura();
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        try {
            abrirEscritura();
            long numRegistros = escritura.length() / bytesPorProducto;
            if (producto.getCodigo() > 0 && producto.getCodigo() <= numRegistros) {
                escritura.seek((producto.getCodigo() - 1) * bytesPorProducto);
                escritura.writeInt(producto.getCodigo());
                String nombre = producto.getNombre();
                if (nombre.length() > longitudNombre) {
                    nombre = nombre.substring(0, longitudNombre);
                }
                while (nombre.length() < longitudNombre) {
                    nombre += " ";
                }
                escritura.writeChars(nombre);
                escritura.writeDouble(producto.getPrecio());
            }
        } catch (IOException ex) {
            System.out.println("Error al actualizar el producto: " + ex.getMessage());
        } finally {
            cerrarEscritura();
        }
    }

    @Override
    public void eliminar(int codigo) {
        try {
            abrirEscritura();
            long numRegistros = escritura.length() / bytesPorProducto;
            if (codigo > 0 && codigo <= numRegistros) {
                escritura.seek((codigo - 1) * bytesPorProducto);
                escritura.writeInt(0); // 0 indica registro eliminado
                String nombre = "";
                while (nombre.length() < longitudNombre) {
                    nombre += " ";
                }
                escritura.writeChars(nombre);
                escritura.writeDouble(0.0);
            }
        } catch (IOException ex) {
            System.out.println("Error al eliminar el producto: " + ex.getMessage());
        } finally {
            cerrarEscritura();
        }
    }

    @Override
    public List<Producto> listarTodos(){
        List<Producto> productos = new ArrayList<>();
        try {
            abrirLectura();
            long numRegistros = lectura.length() / bytesPorProducto;
            for (int i = 0; i < numRegistros; i++) {
                lectura.seek(i * bytesPorProducto);
                int codigo = lectura.readInt();
                StringBuilder nombreBuilder = new StringBuilder();
                for (int j = 0; j < longitudNombre; j++) {
                    nombreBuilder.append(lectura.readChar());
                }
                String nombre = nombreBuilder.toString().trim();
                double precio = lectura.readDouble();
                if (codigo != 0) { // Solo productos no eliminados
                    productos.add(new Producto(codigo, nombre, precio));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            cerrarLectura();
        }
        return productos;
    }

    private void abrirLectura() {
        try {
            lectura = new RandomAccessFile(rutaArchivo, "r");
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo para lectura: " + e.getMessage());
        }
    }

    private void abrirEscritura() {
        try {
            escritura = new RandomAccessFile(rutaArchivo, "rw");
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo para escritura: " + e.getMessage());
        }
    }

    private void cerrarLectura() {
        try {
            if (lectura != null) {
                lectura.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar el archivo de lectura: " + e.getMessage());
        }
    }

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
