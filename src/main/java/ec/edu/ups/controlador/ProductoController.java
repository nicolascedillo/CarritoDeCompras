package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoListaView;
import ec.edu.ups.vista.ProductoOpcionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ProductoDAO productoDAO;
    private final ProductoOpcionView productoOpcionView;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView, ProductoOpcionView productoOpcionView) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoOpcionView = productoOpcionView;
        configurarEventos();
    }

    private void configurarEventos() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

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

        productoOpcionView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(productoOpcionView.getTxtBuscar().getText());

                Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                if (productoEncontrado  != null) {
                    productoOpcionView.cargarDatos(productoEncontrado);
                }else{
                    productoOpcionView.mostrarMensaje("Producto no encontrado");
                }
            }
        });

        productoOpcionView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(Integer.parseInt(productoOpcionView.getTxtBuscar().getText()));
                productoOpcionView.mostrarMensaje("Producto eliminado");
                productoOpcionView.limpiarCampos();
            }
        });

        productoOpcionView.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto(Integer.parseInt(productoOpcionView.getTxtBuscar().getText()));
                productoOpcionView.mostrarMensaje("Producto modificado");
                productoOpcionView.limpiarCampos();
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
        producto.setNombre(productoOpcionView.getTxtNombre().getText());
        producto.setPrecio(Double.parseDouble(productoOpcionView.getTxtPrecio().getText()));
        productoDAO.actualizar(producto);
    }
}