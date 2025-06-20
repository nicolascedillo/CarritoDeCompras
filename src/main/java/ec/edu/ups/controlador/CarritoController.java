package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoEliminarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarritoController {

    private final CarritoAnadirView carritoAnadirView;
    private final CarritoDAO carritoDao;
    private final ProductoDAO productoDao;
    private final CarritoEliminarView carritoEliminarView;
    private Carrito carrito;

    public CarritoController(ProductoDAO productoDAO,CarritoDAO carritoDao, CarritoAnadirView carritoAnadirView, CarritoEliminarView carritoEliminarView)  {
        this.carritoDao = carritoDao;
        this.carritoAnadirView = carritoAnadirView;
        this.productoDao = productoDAO;
        this.carritoEliminarView = carritoEliminarView;
        this.carrito = new Carrito();
        configurarEventosAnadir();
        configurarEventosEliminar();
    }


    private void configurarEventosAnadir(){

        carritoAnadirView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(carrito);
                carritoDao.crear(carrito);

            }
        });

        carritoAnadirView.getAnadirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoAnadirView.getCodigoTextField().getText());
                anadirProductoEnCarrito(codigo);
            }
        });
    }

    private void configurarEventosEliminar() {
        carritoEliminarView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        carritoEliminarView,
                        "¿Estás seguro de que quieres eliminar este producto?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    carritoDao.eliminar(Integer.parseInt(carritoEliminarView.getCodigoTextField().getText()));
                    carritoEliminarView.mostrarMensaje("Producto eliminado");
                    carritoEliminarView.getCodigoTextField().setEnabled(true);
                } else {
                    carritoEliminarView.mostrarMensaje("Accion cancelada");
                }
            }
        });

        carritoEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoEliminarView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                if (carritoEncontrado != null) {
                    carritoEliminarView.getNombreTextField().setText(carritoEncontrado.getFechaCreacion());
                    carritoEliminarView.getEliminarButton().setEnabled(true);
                    carritoEliminarView.cargarDatos(carritoEncontrado);
                    carritoEliminarView.getSubtotalTextField().setText(String.valueOf(carritoEncontrado.calcularSubtotal()));
                    carritoEliminarView.getIvaTextField().setText(String.valueOf(carritoEncontrado.calcularIva()));
                    carritoEliminarView.getTotalTextField().setText(String.valueOf(carritoEncontrado.calcularTotal()));
                } else {
                    carritoEliminarView.mostrarMensaje("Producto no encontrado");
                }
            }
        });
    }

    private void anadirProductoEnCarrito(int codigo){
        Producto productoEncontrado =  productoDao.buscarPorCodigo(codigo);
        int cantidad = (Integer) carritoAnadirView.getCantidadComboBox().getSelectedItem();
        carritoAnadirView.cargarDatos(productoEncontrado);
        carrito.agregarProducto(productoEncontrado, cantidad);

    }


}
