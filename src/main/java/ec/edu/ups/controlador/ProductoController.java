package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoEliminarView;
import ec.edu.ups.vista.ProductoListaView;
import ec.edu.ups.vista.ProductoModificarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {
    private ProductoAnadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoDAO productoDAO;
    private ProductoEliminarView productoEliminarView;
    private ProductoModificarView productoModificarView;

    public ProductoController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void configurarEventosAnadir() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    public void configurarEventosEliminar() {

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
    public void configurarEventosModificar() {

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

    public void configurarEventosListar() {
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

    //Getters y setters
    public ProductoAnadirView getProductoAnadirView() {
        return productoAnadirView;
    }

    public void setProductoAnadirView(ProductoAnadirView productoAnadirView) {
        this.productoAnadirView = productoAnadirView;
    }

    public ProductoListaView getProductoListaView() {
        return productoListaView;
    }

    public void setProductoListaView(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
    }

    public ProductoModificarView getProductoModificarView() {
        return productoModificarView;
    }

    public void setProductoModificarView(ProductoModificarView productoModificarView) {
        this.productoModificarView = productoModificarView;
    }

    public ProductoEliminarView getProductoEliminarView() {
        return productoEliminarView;
    }

    public void setProductoEliminarView(ProductoEliminarView productoEliminarView) {
        this.productoEliminarView = productoEliminarView;
    }
}