package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
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

public class ProductoController {
    private final ProductoCrearView productoCrearView;
    private final ProductoListaView productoListaView;
    private final ProductoDAO productoDAO;
    private final ProductoEliminarView productoEliminarView;
    private final ProductoModificarView productoModificarView;
    private final CarritoCrearView carritoCrearView;
    private MensajeInternacionalizacionHandler mIH;

    public ProductoController(ProductoCrearView productoCrearView,
                              ProductoListaView productoListaView,
                              ProductoDAO productoDAO,
                              ProductoEliminarView productoEliminarView,
                              ProductoModificarView productoModificarView,
                              CarritoCrearView carritoCrearView,
                              MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        this.productoCrearView = productoCrearView;
        this.productoListaView = productoListaView;
        this.productoDAO = productoDAO;
        this.productoEliminarView = productoEliminarView;
        this.productoModificarView = productoModificarView;
        this.carritoCrearView = carritoCrearView;
        configurarEventosListar();
        configurarEventosEliminar();
        configurarEventosModificar();
        configurarEventosAnadir();
        configurarEventosCarritoAnadir();

    }

    private void configurarEventosAnadir() {
        productoCrearView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    private void configurarEventosEliminar() {

        productoEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
                Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                if (productoEncontrado != null) {
                    productoEliminarView.getTxtNombre().setText(productoEncontrado.getNombre());
                    productoEliminarView.getTxtPrecio().setText(String.valueOf(productoEncontrado.getPrecio()));
                    productoEliminarView.getTxtNombre().setEnabled(false);
                    productoEliminarView.getTxtPrecio().setEnabled(false);
                }else{
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

                if (respuesta == JOptionPane.YES_OPTION) {
                    eliminarProducto(Integer.parseInt(productoEliminarView.getTxtCodigo().getText()));
                    productoEliminarView.mostrarMensaje(mIH.get("mensaje.producto.eliminado"));
                    productoEliminarView.limpiarCampos();

                } else {
                    productoEliminarView.mostrarMensaje(mIH.get("mensaje.cancelar"));
                }

            }
        });
    }

    private void configurarEventosModificar() {

        productoModificarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
                Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                productoModificarView.getTxtCodigo().setEnabled(true);
                productoModificarView.getTxtNombre().setEnabled(true);
                productoModificarView.getTxtPrecio().setEnabled(true);

                if (productoEncontrado != null) {
                    productoModificarView.getTxtNombre().setText(productoEncontrado.getNombre());
                    productoModificarView.getTxtPrecio().setText(String.valueOf(productoEncontrado.getPrecio()));

                }else{
                    productoModificarView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
                }
            }
        });

        productoModificarView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto(Integer.parseInt(productoModificarView.getTxtCodigo().getText()));
                productoModificarView.limpiarCampos();
                productoModificarView.mostrarMensaje(mIH.get("mensaje.producto.modificado"));

            }
        });
    }

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

    private void configurarEventosCarritoAnadir(){

        carritoCrearView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoCrearView.getCodigoTextField().getText());
                buscarProductoEnCarrito(codigo);
            }
        });

    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoCrearView.getTxtCodigo().getText());
        String nombre = productoCrearView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoCrearView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoCrearView.mostrarMensaje(mIH.get("mensaje.producto.creado"));
        productoCrearView.limpiarCampos();
        productoCrearView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        if( productosEncontrados.isEmpty() ) {
            productoListaView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
        } else {
            productoListaView.cargarDatos(productosEncontrados);
        }
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        if( productos.isEmpty() ) {
            productoListaView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
        } else {
            productoListaView.cargarDatos(productos);
        }
    }

    private void eliminarProducto(int codigo) {
        productoDAO.eliminar(codigo);
    }

    private void actualizarProducto(int codigo){
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        producto.setNombre(productoModificarView.getTxtNombre().getText());
        producto.setPrecio(Double.parseDouble(productoModificarView.getTxtPrecio().getText()));
        productoDAO.actualizar(producto);
    }

    private void buscarProductoEnCarrito(int codigo){
        Producto productoEncontrado =  productoDAO.buscarPorCodigo(codigo);

        if(productoEncontrado == null){
            carritoCrearView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
        }else{
            carritoCrearView.getCodigoTextField().setText(String.valueOf(productoEncontrado.getCodigo()));
            carritoCrearView.getNombreTextField().setText(productoEncontrado.getNombre());
            carritoCrearView.getPrecioTextField().setText(String.valueOf(productoEncontrado.getPrecio()));
        }
    }

}