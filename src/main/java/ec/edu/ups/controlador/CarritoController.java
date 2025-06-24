package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.carrito.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarritoController {

    private final CarritoCrearView carritoCrearView;
    private final CarritoDAO carritoDao;
    private final ProductoDAO productoDao;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoModificarView carritoModificarView;
    private final CarritoListaView carritoListaView;
    private final ItemListaView itemListaView;

    public CarritoController(ProductoDAO productoDAO, CarritoDAO carritoDao, CarritoCrearView carritoCrearView, CarritoEliminarView carritoEliminarView, CarritoModificarView carritoModificarView, CarritoListaView carritoListaView,ItemListaView itemListaView) {
        this.carritoDao = carritoDao;
        this.carritoCrearView = carritoCrearView;
        this.productoDao = productoDAO;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoModificarView = carritoModificarView;
        this.carritoListaView = carritoListaView;
        this.itemListaView = itemListaView;
        configurarEventosAnadir();
        configurarEventosEliminar();
        configurarEventosModificar();
        configurarEventosListar();
    }


    private void configurarEventosAnadir(){

        carritoCrearView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                carritoDao.crear(carritoCrearView.getCarrito());
                carritoCrearView.mostrarMensaje("Carrito guardado correctamente");
                carritoCrearView.limpiarCampos();
                carritoCrearView.setCarrito(new Carrito());
                carritoCrearView.getGuardarButton().setEnabled(false);

            }
        });

        carritoCrearView.getAnadirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoCrearView.getCodigoTextField().getText());
                anadirProductoEnCarrito(codigo);
                carritoCrearView.getGuardarButton().setEnabled(true);
            }
        });

        carritoCrearView.getCancelarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoCrearView.limpiarCampos();
                carritoCrearView.getCodigoTextField().setEnabled(true);
                carritoCrearView.setCarrito(new ec.edu.ups.modelo.Carrito());
                carritoCrearView.mostrarMensaje("Creacion de carrito cancelada correctamente");
                carritoCrearView.getGuardarButton().setEnabled(false);
            }
        });

    }

    private void configurarEventosEliminar() {

        carritoEliminarView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                        carritoEliminarView,
                        "Estás seguro de que quieres eliminar este producto?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    carritoDao.eliminar(Integer.parseInt(carritoEliminarView.getCodigoTextField().getText()));
                    carritoEliminarView.mostrarMensaje("Carrito eliminado");
                    carritoEliminarView.limpiarCampos();
                } else {
                    carritoEliminarView.mostrarMensaje("Acción cancelada");
                }
            }
        });

        carritoEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoEliminarView.limpiarTabla();
                int codigo = Integer.parseInt(carritoEliminarView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                if (carritoEncontrado != null) {
                    carritoEliminarView.getNombreTextField().setText(carritoEncontrado.getFechaCreacion());
                    carritoEliminarView.getEliminarButton().setEnabled(true);
                    carritoEliminarView.cargarDatos(carritoEncontrado);
                    carritoEliminarView.getSubtotalTextField().setText(String.valueOf(carritoEncontrado.calcularSubtotal()));
                    carritoEliminarView.getIvaTextField().setText(String.valueOf(carritoEncontrado.calcularIva()));
                    carritoEliminarView.getTotalTextField().setText(String.valueOf(carritoEncontrado.calcularTotal()));
                    carritoEliminarView.getEliminarButton().setEnabled(true);
                } else {
                    carritoEliminarView.mostrarMensaje("Carrito no encontrado");
                    carritoEliminarView.getEliminarButton().setEnabled(false);
                }
            }
        });
    }

    private void configurarEventosModificar() {

        carritoModificarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                if (carritoEncontrado != null) {
                    carritoModificarView.getNombreTextField().setText(carritoEncontrado.getFechaCreacion());
                    carritoModificarView.cargarDatos(carritoEncontrado);
                    carritoModificarView.getSubtotalTextField().setText(String.valueOf(carritoEncontrado.calcularSubtotal()));
                    carritoModificarView.getIvaTextField().setText(String.valueOf(carritoEncontrado.calcularIva()));
                    carritoModificarView.getTotalTextField().setText(String.valueOf(carritoEncontrado.calcularTotal()));
                } else {
                    carritoModificarView.mostrarMensaje("Carrito no encontrado");
                }
            }
        });

        carritoModificarView.getEditarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoModificarView.getTable1().getSelectedRow() != -1) {
                    int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog(carritoModificarView, "Ingrese la nueva cantidad:"));

                    int codigoCarrito = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
                    Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigoCarrito);

                    for (ItemCarrito item: carritoEncontrado.obtenerItems()) {
                        if (item.getProducto().getCodigo() == (Integer) carritoModificarView.getTable1().getValueAt(carritoModificarView.getTable1().getSelectedRow(), 0)) {
                            item.setCantidad(nuevaCantidad);
                        }
                    }
                    carritoDao.actualizar(carritoEncontrado);
                    carritoModificarView.cargarDatos(carritoEncontrado);

                    carritoModificarView.getSubtotalTextField().setText(String.valueOf(carritoEncontrado.calcularSubtotal()));
                    carritoModificarView.getIvaTextField().setText(String.valueOf(carritoEncontrado.calcularIva()));
                    carritoModificarView.getTotalTextField().setText(String.valueOf(carritoEncontrado.calcularTotal()));

                } else {
                    carritoModificarView.mostrarMensaje("Seleccione un Item para editar");

                }
            }
        });


    }

    private void configurarEventosListar() {
        carritoListaView.getListarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoListaView.limpiarCampos();
                if(!carritoDao.listarTodos().isEmpty()){
                    carritoListaView.cargarDatosLista(carritoDao.listarTodos());
                } else {
                    carritoListaView.mostrarMensaje("No hay carritos registrados");
                }
            }
        });

        carritoListaView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoListaView.getCodigoTextField().getText().isEmpty()) {
                    carritoListaView.mostrarMensaje("Ingrese un código para buscar");
                    return;
                }
                int codigo = Integer.parseInt(carritoListaView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                if (carritoEncontrado != null) {
                    carritoListaView.cargarDatosBusqueda(carritoDao.buscarPorCodigo(codigo));
                } else {
                    carritoListaView.mostrarMensaje("Carrito no encontrado");
                }
            }
        });

        carritoListaView.getVerDetallesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carritoListaView.getTable1().getSelectedRow() != -1) {
                    int codigoCarrito = (Integer) carritoListaView.getTable1().getValueAt(carritoListaView.getTable1().getSelectedRow(), 0);
                    Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigoCarrito);
                    itemListaView.cargarDatos(carritoEncontrado);
                    if(!itemListaView.isVisible()){
                        itemListaView.setVisible(true);
                    }
                } else {
                    carritoListaView.mostrarMensaje("Seleccione un Carrito para ver detalles");
                }
            }
        });
    }

    private void anadirProductoEnCarrito(int codigo){
        Producto productoEncontrado =  productoDao.buscarPorCodigo(codigo);
        if(!verificarProductoEnCarrito(productoEncontrado)){
            int cantidad = (Integer) carritoCrearView.getCantidadComboBox().getSelectedItem();
            carritoCrearView.getCarrito().agregarProducto(productoEncontrado, cantidad);
            carritoCrearView.cargarDatos(carritoCrearView.getCarrito());
        }else{
            for (ItemCarrito item : carritoCrearView.getCarrito().obtenerItems()) {
                if (item.getProducto().getCodigo() == productoEncontrado.getCodigo()) {
                    item.setCantidad(item.getCantidad() + (Integer) carritoCrearView.getCantidadComboBox().getSelectedItem());
                }
            }
            carritoCrearView.cargarDatos(carritoCrearView.getCarrito());
            carritoCrearView.mostrarMensaje("Producto ya está en el carrito, cantidad actualizada.");
        }
        carritoCrearView.getSubtotalTextField().setText(String.valueOf(carritoCrearView.getCarrito().calcularSubtotal()));
        carritoCrearView.getIvaTextField().setText(String.valueOf(carritoCrearView.getCarrito().calcularIva()));
        carritoCrearView.getTotalTextField().setText(String.valueOf(carritoCrearView.getCarrito().calcularTotal()));

    }

    private boolean verificarProductoEnCarrito(Producto producto) {
        for (ItemCarrito item : carritoCrearView.getCarrito().obtenerItems()) {
            if (item.getProducto().getCodigo() == producto.getCodigo()) {
                return true;
            }
        }
        return false;

    }

}
