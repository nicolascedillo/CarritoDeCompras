package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.CarritoCrearView;
import ec.edu.ups.vista.producto.ProductoCrearView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

public class ProductoController {
    private final ProductoCrearView productoCrearView;
    private final ProductoListaView productoListaView;
    private final ProductoDAO productoDAO;
    private final ProductoEliminarView productoEliminarView;
    private final ProductoModificarView productoModificarView;
    private Producto producto;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de ProductoController.
     * Inicializa las vistas, el DAO de productos y el handler de internacionalización.
     * Configura los eventos de las vistas y establece el código del producto.
     *
     * @param productoCrearView Vista para crear productos.
     * @param productoListaView Vista para listar productos.
     * @param productoDAO DAO para operaciones de productos.
     * @param productoEliminarView Vista para eliminar productos.
     * @param productoModificarView Vista para modificar productos.
     * @param mIH Handler de internacionalización.
     */
    public ProductoController(ProductoCrearView productoCrearView,
                              ProductoListaView productoListaView,
                              ProductoDAO productoDAO,
                              ProductoEliminarView productoEliminarView,
                              ProductoModificarView productoModificarView,
                              MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        this.productoCrearView = productoCrearView;
        this.productoListaView = productoListaView;
        this.productoDAO = productoDAO;
        this.productoEliminarView = productoEliminarView;
        this.productoModificarView = productoModificarView;
        this.producto = new Producto();
        configurarEventosListar();
        configurarEventosEliminar();
        configurarEventosModificar();
        configurarEventosAnadir();
        setProductoCodigo();

    }

    /**
     * Configura el evento para añadir un producto.
     * Asocia el botón de aceptar con la acción de guardar el producto.
     */
    private void configurarEventosAnadir() {
        productoCrearView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    /**
     * Configura los eventos para eliminar productos.
     * Permite buscar un producto por código y eliminarlo si existe.
     */
    private void configurarEventosEliminar() {

        productoEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productoEliminarView.getTxtCodigo().getText().isEmpty()) {
                    return;
                }
                try{
                    int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
                    Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                    productoEliminarView.getTxtNombre().setText(productoEncontrado.getNombre());
                    productoEliminarView.getTxtPrecio().setText(FormateadorUtils.formatearMoneda(productoEncontrado.getPrecio(), mIH.getLocale()));
                    productoEliminarView.getTxtNombre().setEnabled(false);
                    productoEliminarView.getTxtPrecio().setEnabled(false);
                    productoEliminarView.getBtnEliminar().setEnabled(true);

                }catch(NumberFormatException ex){
                    productoEliminarView.mostrarMensaje(mIH.get("numberFormatException"));
                }catch (NullPointerException ex){
                    productoEliminarView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));

                }

            }
        });

        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        productoEliminarView,
                        mIH.get("mensaje.producto.confirmacion"),
                        mIH.get("mensaje.confirmacion.titulo"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                try{
                    if (respuesta == JOptionPane.YES_OPTION) {
                        eliminarProducto(Integer.parseInt(productoEliminarView.getTxtCodigo().getText()));
                        productoEliminarView.mostrarMensaje(mIH.get("mensaje.producto.eliminado"));
                        productoEliminarView.limpiarCampos();

                    } else {
                        productoEliminarView.mostrarMensaje(mIH.get("mensaje.cancelar"));
                    }
                }catch (NumberFormatException ex) {
                    productoEliminarView.mostrarMensaje(mIH.get("numberFormatException"));
                }

            }
        });
    }

    /**
     * Configura los eventos para modificar productos.
     * Permite buscar un producto por código y actualizar sus datos.
     */
    private void configurarEventosModificar() {

        productoModificarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
                    Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                    productoModificarView.getTxtCodigo().setEnabled(true);
                    productoModificarView.getTxtNombre().setEnabled(true);
                    productoModificarView.getTxtPrecio().setEnabled(true);

                    productoModificarView.getTxtNombre().setText(productoEncontrado.getNombre());
                    productoModificarView.getTxtPrecio().setText(String.valueOf(productoEncontrado.getPrecio()));

                }catch (NumberFormatException ex){
                    productoModificarView.mostrarMensaje(mIH.get("numberFormatException"));
                }catch (NullPointerException ex){
                    productoModificarView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
                }
            }
        });

        productoModificarView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    actualizarProducto(Integer.parseInt(productoModificarView.getTxtCodigo().getText()));
                    productoModificarView.limpiarCampos();
                    productoModificarView.mostrarMensaje(mIH.get("mensaje.producto.modificado"));
                } catch (NumberFormatException ex) {
                    productoModificarView.mostrarMensaje(mIH.get("numberFormatException"));
                }

            }
        });
    }

    /**
     * Configura los eventos para listar y buscar productos.
     * Permite buscar productos por nombre y listar todos los productos.
     */
    private void configurarEventosListar() {
        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
                productoListaView.getTxtBuscar().setText("");
            }
        });
    }

    /**
     * Guarda un nuevo producto en el sistema.
     * Obtiene los datos de la vista y los envía al DAO.
     */
    private void guardarProducto() {
        try{
            String nombre = productoCrearView.getTxtNombre().getText();
            double precio = Double.parseDouble(productoCrearView.getTxtPrecio().getText());

            productoDAO.crear(new Producto(nombre, precio));
            productoCrearView.mostrarMensaje(mIH.get("mensaje.producto.creado"));
            productoCrearView.limpiarCampos();
        } catch (NumberFormatException e) {
            productoCrearView.mostrarMensaje(mIH.get("numberFormatException"));
        }
    }

    /**
     * Busca productos por nombre y los muestra en la vista de lista.
     */
    private void buscarProducto() {
        try{
            String nombre = productoListaView.getTxtBuscar().getText();

            List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
            productoListaView.cargarDatos(productosEncontrados);
        }catch (NumberFormatException e) {
            productoListaView.mostrarMensaje(mIH.get("numberFormatException"));
        }catch (NullPointerException e) {
            productoListaView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
        }
    }

    /**
     * Lista todos los productos y los muestra en la vista de lista.
     */
    private void listarProductos() {
        try{
            List<Producto> productos = productoDAO.listarTodos();
            productoListaView.cargarDatos(productos);
        }catch (NullPointerException e) {
            productoListaView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
        } catch (NumberFormatException e) {
            productoListaView.mostrarMensaje(mIH.get("numberFormatException"));
        }
    }

    /**
     * Elimina un producto dado su código.
     *
     * @param codigo Código del producto a eliminar.
     */
    private void eliminarProducto(int codigo) {
        productoDAO.eliminar(codigo);
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param codigo Código del producto a actualizar.
     */
    private void actualizarProducto(int codigo){
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        producto.setNombre(productoModificarView.getTxtNombre().getText());
        producto.setPrecio(Double.parseDouble(productoModificarView.getTxtPrecio().getText()));
        productoDAO.actualizar(producto);
    }

    /**
     * Refresca la tabla de productos en la vista de lista,
     * actualizando el formato de los precios según el locale.
     *
     * @param locale Locale para formatear los precios.
     */
    private void refrescarTabla(Locale locale) {
        int rowCount = productoListaView.getModelo().getRowCount();

        for (int i = 0; i < rowCount; i++) {

            int codigo = (Integer) productoListaView.getModelo().getValueAt(i, 0);

            Producto producto = productoDAO.buscarPorCodigo(codigo);
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);

            productoListaView.getModelo().setValueAt(nuevoPrecioFormateado, i, 2);
        }
    }

    /**
     * Refresca el precio mostrado en la vista de eliminar producto,
     * usando el locale actual para el formato.
     *
     * @param locale Locale para formatear el precio.
     */
    private void refrescarPrecioEliminar(Locale locale) {
        if(productoEliminarView.getTxtCodigo().getText().isEmpty()) {
            return;
        }
        int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);
            productoEliminarView.getTxtPrecio().setText(nuevoPrecioFormateado);
        }
    }

    /**
     * Cambia el idioma de todas las vistas relacionadas con productos,
     * usando el handler de internacionalización.
     *
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        productoCrearView.cambiarIdioma(lenguaje, pais);
        productoListaView.cambiarIdioma(lenguaje, pais);
        productoEliminarView.cambiarIdioma(lenguaje, pais);
        productoModificarView.cambiarIdioma(lenguaje, pais);
        refrescarTabla(mIH.getLocale());
        refrescarPrecioEliminar(mIH.getLocale());
    }

    /**
     * Establece el código del producto automáticamente,
     * incrementando respecto al último producto registrado.
     */
    private void setProductoCodigo(){
        if(!productoDAO.listarTodos().isEmpty()){
            producto.setCodigo(productoDAO.listarTodos().getLast().getCodigo()+1);
        }
    }
}