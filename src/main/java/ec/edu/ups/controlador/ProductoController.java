package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.ProductoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoController {

    private final ProductoView productoView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO, ProductoView productoView) {
        this.productoDAO = productoDAO;
        this.productoView = productoView;
        configurarEventos();
    }

    private void configurarEventos() {
        productoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoView.getTxtCodigo().getText());
        String nombre = productoView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoView.mostrarMensaje("Producto guardado correctamente");
        productoView.limpiarCampos();
        productoView.mostrarProductos(productoDAO.listarTodos());
    }

}
