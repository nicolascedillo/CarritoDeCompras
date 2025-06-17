package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {
    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ProductoDAO productoDAO;
    private final ProductoEliminarView productoEliminarView;
    private final ProductoModificarView productoModificarView;
    private final CarritoAnadirView carritoAnadirView;

    public ProductoController(ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoDAO productoDAO,
                              ProductoEliminarView productoEliminarView,
                              ProductoModificarView productoModificarView,
                              CarritoAnadirView carritoAnadirView) {
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoDAO = productoDAO;
        this.productoEliminarView = productoEliminarView;
        this.productoModificarView = productoModificarView;
        this.carritoAnadirView = carritoAnadirView;
        configurarEventosListar();
        configurarEventosEliminar();
        configurarEventosModificar();
        configurarEventosAnadir();
        configurarEventosCarritoAnadir();

    }

    private void configurarEventosAnadir() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
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
                productoEliminarView.getTxtCodigo().setEnabled(false);
                productoEliminarView.getTxtNombre().setEnabled(false);
                productoEliminarView.getTxtPrecio().setEnabled(false);
                if (productoEncontrado != null) {
                    productoEliminarView.getTxtNombre().setText(productoEncontrado.getNombre());
                    productoEliminarView.getTxtPrecio().setText(String.valueOf(productoEncontrado.getPrecio()));

                }else{
                    productoEliminarView.mostrarMensaje("Producto no encontrado");
                }
            }
        });

        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        productoEliminarView,
                        "¿Estás seguro de que quieres eliminar este producto?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    eliminarProducto(Integer.parseInt(productoEliminarView.getTxtCodigo().getText()));
                    productoEliminarView.mostrarMensaje("Producto eliminado");
                    productoEliminarView.limpiarCampos();
                    productoEliminarView.getTxtCodigo().setEnabled(true);
                    productoEliminarView.getTxtNombre().setEnabled(true);
                    productoEliminarView.getTxtPrecio().setEnabled(true);

                } else {
                    productoEliminarView.mostrarMensaje("Accion cancelada");
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
                productoModificarView.getTxtCodigo().setEnabled(false);
                productoModificarView.getTxtNombre().setEnabled(true);
                productoModificarView.getTxtPrecio().setEnabled(true);

                if (productoEncontrado != null) {
                    productoModificarView.getTxtNombre().setText(productoEncontrado.getNombre());
                    productoModificarView.getTxtPrecio().setText(String.valueOf(productoEncontrado.getPrecio()));

                }else{
                    productoModificarView.mostrarMensaje("Producto no encontrado");
                }
            }
        });

        productoModificarView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto(Integer.parseInt(productoModificarView.getTxtCodigo().getText()));
                productoModificarView.mostrarMensaje("Producto modificado");

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
            }
        });
    }

    private void configurarEventosCarritoAnadir(){
        carritoAnadirView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoAnadirView.getCodigoTextField().getText());
                buscarProductoEnCarrito(codigo);
            }
        });
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
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
            carritoAnadirView.mostrarMensaje("No existe producto con ese codigo");
        }else{
            carritoAnadirView.getCodigoTextField().setText(String.valueOf(productoEncontrado.getCodigo()));
            carritoAnadirView.getNombreTextField().setText(productoEncontrado.getNombre());
            carritoAnadirView.getPrecioTextField().setText(String.valueOf(productoEncontrado.getPrecio()));
        }
    }
}