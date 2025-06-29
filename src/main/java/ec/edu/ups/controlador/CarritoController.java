package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class CarritoController {

    private final CarritoCrearView carritoCrearView;
    private final CarritoDAO carritoDao;
    private final ProductoDAO productoDao;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoModificarView carritoModificarView;
    private final CarritoListaView carritoListaView;
    private final ItemListaView itemListaView;
    private MensajeInternacionalizacionHandler mIH;

    public CarritoController(ProductoDAO productoDAO,
                             CarritoDAO carritoDao,
                             CarritoCrearView carritoCrearView,
                             CarritoEliminarView carritoEliminarView,
                             CarritoModificarView carritoModificarView,
                             CarritoListaView carritoListaView,
                             ItemListaView itemListaView,
                             MensajeInternacionalizacionHandler mIH) {
        this.carritoDao = carritoDao;
        this.carritoCrearView = carritoCrearView;
        this.productoDao = productoDAO;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoModificarView = carritoModificarView;
        this.carritoListaView = carritoListaView;
        this.itemListaView = itemListaView;
        this.mIH = mIH;
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
                carritoCrearView.mostrarMensaje(mIH.get("mensaje.carrito.creado"));
                carritoCrearView.limpiarCampos();
                carritoCrearView.setCarrito(new Carrito(carritoCrearView.getUsuario()));
                carritoCrearView.getGuardarButton().setEnabled(false);
            }
        });

        carritoCrearView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoCrearView.getCodigoTextField().getText());
                buscarProductoEnCarrito(codigo);
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
                carritoCrearView.getCarrito().vaciarCarrito();
                carritoCrearView.mostrarMensaje(mIH.get("mensaje.cancelar"));
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
                        mIH.get("mensaje.carrito.confirmacion"),
                        mIH.get("mensaje.confirmacion.titulo"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    carritoDao.eliminar(Integer.parseInt(carritoEliminarView.getCodigoTextField().getText()));
                    carritoEliminarView.mostrarMensaje(mIH.get("mensaje.carrito.eliminado"));
                    carritoEliminarView.limpiarCampos();
                } else {
                    carritoEliminarView.mostrarMensaje(mIH.get("mensaje.cancelar"));
                }
            }
        });

        carritoEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoEliminarView.getCodigoTextField().getText().isEmpty()) {
                    carritoEliminarView.mostrarMensaje(mIH.get("mensaje.carrito.buscar.codigo"));
                    return;
                }
                carritoEliminarView.limpiarTabla();
                int codigo = Integer.parseInt(carritoEliminarView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                if (carritoEncontrado != null) {
                    carritoEliminarView.getFechaTextField().setText(FormateadorUtils.formatearFecha(carritoEncontrado.getFechaCreacionDate(), mIH.getLocale()));
                    carritoEliminarView.getEliminarButton().setEnabled(true);
                    carritoEliminarView.cargarDatos(carritoEncontrado);
                    carritoEliminarView.getUsuarioTextField().setText(carritoEncontrado.getUsuario().getUsername());
                    carritoEliminarView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularSubtotal(), mIH.getLocale()));;
                    carritoEliminarView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularIva(), mIH.getLocale()));
                    carritoEliminarView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularTotal(), mIH.getLocale()));
                    carritoEliminarView.getEliminarButton().setEnabled(true);
                } else {
                    carritoEliminarView.mostrarMensaje(mIH.get("mensaje.carrito.noencontrado"));
                    carritoEliminarView.getEliminarButton().setEnabled(false);
                    carritoEliminarView.limpiarCampos();
                }
            }
        });
    }

    private void configurarEventosModificar() {

        carritoModificarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoModificarView.getCodigoTextField().getText().isEmpty()) {
                    carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.buscar.codigo"));
                    return;
                }
                int codigo = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                if (carritoEncontrado != null) {
                    carritoModificarView.getFechaTextField().setText(FormateadorUtils.formatearFecha(carritoEncontrado.getFechaCreacionDate(), mIH.getLocale()));
                    carritoModificarView.getUsuarioTextField().setText(carritoEncontrado.getUsuario().getUsername());
                    carritoModificarView.cargarDatos(carritoEncontrado);
                    carritoModificarView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularSubtotal(), mIH.getLocale()));
                    carritoModificarView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularIva(), mIH.getLocale()));
                    carritoModificarView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularTotal(), mIH.getLocale()));
                } else {
                    carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.noencontrado"));
                }
            }
        });

        carritoModificarView.getEditarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoModificarView.getTable1().getSelectedRow() != -1) {
                    int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog(carritoModificarView, mIH.get("mensaje.carrito.ingresar.cantidad")));

                    int codigoCarrito = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
                    Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigoCarrito);

                    for (ItemCarrito item: carritoEncontrado.obtenerItems()) {
                        if (item.getProducto().getCodigo() == (Integer) carritoModificarView.getTable1().
                                getValueAt(carritoModificarView.getTable1().getSelectedRow(), 0)) {
                            item.setCantidad(nuevaCantidad);
                        }
                    }
                    carritoDao.actualizar(carritoEncontrado);
                    carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.modificado"));
                    carritoModificarView.cargarDatos(carritoEncontrado);

                    carritoModificarView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularSubtotal(), mIH.getLocale()));
                    carritoModificarView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularIva(), mIH.getLocale()));
                    carritoModificarView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularTotal(), mIH.getLocale()));

                } else {
                    carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.seleccionar.editar"));

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
                    carritoListaView.mostrarMensaje(mIH.get("mensaje.carrito.inexistentes"));
                }
            }
        });

        carritoListaView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoListaView.getCodigoTextField().getText().isEmpty()) {
                    carritoListaView.mostrarMensaje(mIH.get("mensaje.carrito.buscar.codigo"));
                    return;
                }
                int codigo = Integer.parseInt(carritoListaView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                if (carritoEncontrado != null) {
                    carritoListaView.cargarDatosBusqueda(carritoDao.buscarPorCodigo(codigo));
                } else {
                    carritoListaView.mostrarMensaje(mIH.get("mensaje.carrito.noencontrado"));
                }
            }
        });

        carritoListaView.getVerDetallesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carritoListaView.getTable1().getSelectedRow() != -1) {
                    int codigoCarrito = (Integer) carritoListaView.getTable1().getValueAt(
                            carritoListaView.getTable1().getSelectedRow(), 0);
                    Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigoCarrito);
                    itemListaView.cargarDatos(carritoEncontrado);
                    if(!itemListaView.isVisible()){
                        itemListaView.setVisible(true);
                    }
                } else {
                    carritoListaView.mostrarMensaje(mIH.get("mensaje.carrito.detalles.ver"));
                }
            }
        });
    }

    private void buscarProductoEnCarrito(int codigo){
        Producto productoEncontrado =  productoDao.buscarPorCodigo(codigo);

        if(productoEncontrado == null){
            carritoCrearView.mostrarMensaje(mIH.get("mensaje.producto.noencontrado"));
        }else{
            carritoCrearView.getCodigoTextField().setText(String.valueOf(productoEncontrado.getCodigo()));
            carritoCrearView.getNombreTextField().setText(productoEncontrado.getNombre());
            carritoCrearView.getPrecioTextField().setText(FormateadorUtils.formatearMoneda(productoEncontrado.getPrecio(), mIH.getLocale()));
        }
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
            carritoCrearView.mostrarMensaje(mIH.get("mensaje.carrito.producto.ya.registrado"));
        }
        carritoCrearView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoCrearView.getCarrito().calcularSubtotal(), mIH.getLocale()));
        carritoCrearView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoCrearView.getCarrito().calcularIva(), mIH.getLocale()));
        carritoCrearView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoCrearView.getCarrito().calcularTotal(), mIH.getLocale()));

    }

    private boolean verificarProductoEnCarrito(Producto producto) {
        for (ItemCarrito item : carritoCrearView.getCarrito().obtenerItems()) {
            if (item.getProducto().getCodigo() == producto.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    private void refrescarTablaListar(Locale locale){
        int rowCount = carritoListaView.getModelo().getRowCount();

        for (int i = 0; i < rowCount; i++) {

            int codigo = (Integer) carritoListaView.getModelo().getValueAt(i, 0);

            Carrito carrito = carritoDao.buscarPorCodigo(codigo);
            String nuevaFecha = FormateadorUtils.formatearFecha(carrito.getFechaCreacionDate(), locale);
            carritoListaView.getModelo().setValueAt(nuevaFecha, i, 1);
            String nuevoSubtotal = FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale);
            carritoListaView.getModelo().setValueAt(nuevoSubtotal, i, 3);
            String nuevoIva = FormateadorUtils.formatearMoneda(carrito.calcularIva(), locale);
            carritoListaView.getModelo().setValueAt(nuevoIva, i, 4);
            String nuevoTotal = FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale);
            carritoListaView.getModelo().setValueAt(nuevoTotal, i, 5);
        }
    }

    private void refrescarEliminar(Locale locale) {
        if(carritoEliminarView.getCodigoTextField().getText().isEmpty()) {
            return;
        }

        int rowCount = carritoEliminarView.getModelo().getRowCount();

        if(rowCount == 0) {
            return;
        }

        for (int i = 0; i < rowCount; i++) {

            int codigoP = (Integer) carritoEliminarView.getModelo().getValueAt(i, 0);

            Producto producto = productoDao.buscarPorCodigo(codigoP);
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);

            carritoEliminarView.getModelo().setValueAt(nuevoPrecioFormateado, i, 2);
        }

        int codigo = Integer.parseInt(carritoEliminarView.getCodigoTextField().getText());
        Carrito carrito = carritoDao.buscarPorCodigo(codigo);
        if (carrito != null) {
            String nuevaFecha = FormateadorUtils.formatearFecha(carrito.getFechaCreacionDate(), locale);
            carritoEliminarView.getFechaTextField().setText(nuevaFecha);
            String nuevoSubtotal = FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale);
            carritoEliminarView.getSubtotalTextField().setText(nuevoSubtotal);
            String nuevoIva = FormateadorUtils.formatearMoneda(carrito.calcularIva(), locale);
            carritoEliminarView.getIvaTextField().setText(nuevoIva);
            String nuevoTotal = FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale);
            carritoEliminarView.getTotalTextField().setText(nuevoTotal);
        }
    }

    private void refrescarModificar(Locale locale) {
        if(carritoModificarView.getCodigoTextField().getText().isEmpty()) {
            return;
        }

        int rowCount = carritoModificarView.getTable1().getRowCount();

        if(rowCount == 0) {
            return;
        }

        for (int i = 0; i < rowCount; i++) {

            int codigoP = (Integer) carritoModificarView.getTable1().getValueAt(i, 0);

            Producto producto = productoDao.buscarPorCodigo(codigoP);
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);

            carritoModificarView.getTable1().setValueAt(nuevoPrecioFormateado, i, 2);
        }

        int codigo = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
        Carrito carrito = carritoDao.buscarPorCodigo(codigo);
        if (carrito != null) {
            String nuevaFecha = FormateadorUtils.formatearFecha(carrito.getFechaCreacionDate(), locale);
            carritoModificarView.getFechaTextField().setText(nuevaFecha);
            String nuevoSubtotal = FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale);
            carritoModificarView.getSubtotalTextField().setText(nuevoSubtotal);
            String nuevoIva = FormateadorUtils.formatearMoneda(carrito.calcularIva(), locale);
            carritoModificarView.getIvaTextField().setText(nuevoIva);
            String nuevoTotal = FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale);
            carritoModificarView.getTotalTextField().setText(nuevoTotal);
        }
    }

    private void refrescarCrear(Locale locale){


        int rowCount = carritoCrearView.getTable1().getRowCount();
        for (int i = 0; i < rowCount; i++) {

            int codigoP = (Integer) carritoCrearView.getTable1().getValueAt(i, 0);

            Producto producto = productoDao.buscarPorCodigo(codigoP);
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);

            carritoCrearView.getTable1().setValueAt(nuevoPrecioFormateado, i, 2);
        }

        if(!carritoCrearView.getPrecioTextField().getText().isEmpty()) {
            Producto producto = productoDao.buscarPorCodigo(Integer.parseInt(carritoCrearView.getCodigoTextField().getText()));
            carritoCrearView.getPrecioTextField().setText(FormateadorUtils.formatearMoneda(producto.getPrecio(), locale));
        }

        carritoCrearView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoCrearView.getCarrito().calcularSubtotal(), mIH.getLocale()));
        carritoCrearView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoCrearView.getCarrito().calcularIva(), mIH.getLocale()));
        carritoCrearView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoCrearView.getCarrito().calcularTotal(), mIH.getLocale()));
    }

    private void refrescarItemLista(Locale locale) {
        int rowCount = itemListaView.getModelo().getRowCount();

        for (int i = 0; i < rowCount; i++) {

            int codigoP = (Integer) itemListaView.getModelo().getValueAt(i, 0);

            Producto producto = productoDao.buscarPorCodigo(codigoP);
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);

            itemListaView.getModelo().setValueAt(nuevoPrecioFormateado, i, 2);
        }
    }

    public void cambiarIdioma(String lenguaje,String pais) {
        mIH.setLenguaje(lenguaje, pais);
        carritoCrearView.cambiarIdioma(lenguaje, pais);
        carritoEliminarView.cambiarIdioma(lenguaje, pais);
        carritoModificarView.cambiarIdioma(lenguaje, pais);
        carritoListaView.cambiarIdioma(lenguaje, pais);
        itemListaView.cambiarIdioma(lenguaje, pais);
        refrescarTablaListar(mIH.getLocale());
        refrescarEliminar(mIH.getLocale());
        refrescarModificar(mIH.getLocale());
        refrescarCrear(mIH.getLocale());
        refrescarItemLista(mIH.getLocale());
    }
}
