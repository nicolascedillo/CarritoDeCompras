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
    private MensajeInternacionalizacionHandler mIH;

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
        configurarEventosListar();
        configurarEventosEliminar();
        configurarEventosModificar();
        configurarEventosAnadir();

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
                if(productoEliminarView.getTxtCodigo().getText().isEmpty()) {
                    return;
                }
                int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
                Producto productoEncontrado = productoDAO.buscarPorCodigo(codigo);
                if (productoEncontrado != null) {
                    productoEliminarView.getTxtNombre().setText(productoEncontrado.getNombre());
                    productoEliminarView.getTxtPrecio().setText(FormateadorUtils.formatearMoneda(productoEncontrado.getPrecio(), mIH.getLocale()));
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

    private void refrescarTabla(Locale locale) {
        int rowCount = productoListaView.getModelo().getRowCount();

        for (int i = 0; i < rowCount; i++) {

            int codigo = (Integer) productoListaView.getModelo().getValueAt(i, 0);

            Producto producto = productoDAO.buscarPorCodigo(codigo);
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);

            productoListaView.getModelo().setValueAt(nuevoPrecioFormateado, i, 2);
        }
    }

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

    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        productoCrearView.cambiarIdioma(lenguaje, pais);
        productoListaView.cambiarIdioma(lenguaje, pais);
        productoEliminarView.cambiarIdioma(lenguaje, pais);
        productoModificarView.cambiarIdioma(lenguaje, pais);
        refrescarTabla(mIH.getLocale());
        refrescarPrecioEliminar(mIH.getLocale());
    }


}