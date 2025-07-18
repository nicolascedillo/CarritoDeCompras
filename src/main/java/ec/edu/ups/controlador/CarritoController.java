package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.*;
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
    private final UsuarioDAO usuarioDAO;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoModificarView carritoModificarView;
    private final CarritoListaView carritoListaView;
    private final ItemListaView itemListaView;
    private Carrito carrito;
    private Usuario usuario;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de CarritoController.
     * Inicializa los DAOs, vistas, usuario, carrito y el handler de internacionalización.
     * Configura los eventos de las vistas y establece el código del carrito.
     *
     * @param productoDAO DAO para operaciones de productos.
     * @param carritoDao DAO para operaciones de carritos.
     * @param usuarioDAO DAO para operaciones de usuarios.
     * @param carritoCrearView Vista para crear carritos.
     * @param carritoEliminarView Vista para eliminar carritos.
     * @param carritoModificarView Vista para modificar carritos.
     * @param carritoListaView Vista para listar carritos.
     * @param itemListaView Vista para ver los items de un carrito.
     * @param usuario Usuario autenticado.
     * @param mIH Handler de internacionalización.
     */
    public CarritoController(ProductoDAO productoDAO,
                             CarritoDAO carritoDao,
                             UsuarioDAO usuarioDAO,
                             CarritoCrearView carritoCrearView,
                             CarritoEliminarView carritoEliminarView,
                             CarritoModificarView carritoModificarView,
                             CarritoListaView carritoListaView,
                             ItemListaView itemListaView,
                             Usuario usuario,
                             MensajeInternacionalizacionHandler mIH) {
        this.carritoDao = carritoDao;
        this.carritoCrearView = carritoCrearView;
        this.usuarioDAO = usuarioDAO;
        this.productoDao = productoDAO;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoModificarView = carritoModificarView;
        this.carritoListaView = carritoListaView;
        this.itemListaView = itemListaView;
        this.usuario = usuario;
        this.carrito = new Carrito(usuario);
        this.mIH = mIH;
        configurarEventosAnadir();
        configurarEventosEliminar();
        configurarEventosModificar();
        configurarEventosListar();
        setCodigoCarrito();
    }


    /**
     * Configura los eventos para añadir productos al carrito.
     * Maneja la lógica de guardar, buscar, añadir y cancelar productos en el carrito.
     */
    private void configurarEventosAnadir(){

        carritoCrearView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                carritoDao.crear(carrito);
                usuario.addCarrito(carrito);
                usuarioDAO.actualizar(usuario);
                carritoCrearView.mostrarMensaje(mIH.get("mensaje.carrito.creado"));
                carritoCrearView.limpiarCampos();
                carrito = new Carrito(usuario);
                carritoCrearView.getGuardarButton().setEnabled(false);
            }
        });

        carritoCrearView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int codigo = Integer.parseInt(carritoCrearView.getCodigoTextField().getText());
                    buscarProductoEnCarrito(codigo);
                }catch (NumberFormatException ex){
                    carritoCrearView.mostrarMensaje(mIH.get("numberFormatException"));
                }
            }
        });

        carritoCrearView.getAnadirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int codigo = Integer.parseInt(carritoCrearView.getCodigoTextField().getText());
                    anadirProductoEnCarrito(codigo);
                    carritoCrearView.getGuardarButton().setEnabled(true);
                }catch (NumberFormatException ex){
                    carritoCrearView.mostrarMensaje(mIH.get("numberFormatException"));
                }
            }
        });

        carritoCrearView.getCancelarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoCrearView.limpiarCampos();
                carritoCrearView.getCodigoTextField().setEnabled(true);
                carrito.vaciarCarrito();
                carritoCrearView.mostrarMensaje(mIH.get("mensaje.cancelar"));
                carritoCrearView.getGuardarButton().setEnabled(false);
            }
        });

    }

    /**
     * Configura los eventos para eliminar carritos.
     * Permite buscar y eliminar carritos, mostrando mensajes de confirmación y resultados.
     */
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

                try{
                    carritoEliminarView.limpiarTabla();
                    int codigo = Integer.parseInt(carritoEliminarView.getCodigoTextField().getText());
                    Carrito carritoEncontrado = null;
                    if(usuario.getRol().equals(Rol.USUARIO)){
                        carritoEncontrado = carritoDao.buscarPorCodigoYUsuario(codigo, usuario);

                    }else{
                        carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                    }

                    carritoEliminarView.getFechaTextField().setText(FormateadorUtils.formatearFecha(carritoEncontrado.getFechaCreacionDate(), mIH.getLocale()));
                    carritoEliminarView.getEliminarButton().setEnabled(true);
                    carritoEliminarView.cargarDatos(carritoEncontrado);
                    carritoEliminarView.getUsuarioTextField().setText(carritoEncontrado.getUsuario().getUsername());
                    carritoEliminarView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularSubtotal(), mIH.getLocale()));;
                    carritoEliminarView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularIva(), mIH.getLocale()));
                    carritoEliminarView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularTotal(), mIH.getLocale()));
                    carritoEliminarView.getEliminarButton().setEnabled(true);

                }catch(NumberFormatException ex){

                    carritoEliminarView.mostrarMensaje(mIH.get("numberFormatException"));

                }catch (NullPointerException ex) {
                    carritoEliminarView.mostrarMensaje(mIH.get("mensaje.carrito.noencontrado"));
                    carritoEliminarView.getEliminarButton().setEnabled(false);
                    carritoEliminarView.limpiarCampos();
                }
            }
        });
    }

    /**
     * Configura los eventos para modificar carritos.
     * Permite buscar, editar, eliminar y añadir productos en un carrito existente.
     */
    private void configurarEventosModificar() {

        carritoModificarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoModificarView.getCodigoTextField().getText().isEmpty()) {
                    carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.buscar.codigo"));
                    return;
                }

                int codigo = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
                Carrito carritoEncontrado = null;

                if(usuario.getRol().equals(Rol.USUARIO)){
                    carritoEncontrado = carritoDao.buscarPorCodigoYUsuario(codigo, usuario);

                }else{
                    carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                }

                try{
                    carritoModificarView.getFechaTextField().setText(FormateadorUtils.formatearFecha(carritoEncontrado.getFechaCreacionDate(), mIH.getLocale()));
                    carritoModificarView.getUsuarioTextField().setText(carritoEncontrado.getUsuario().getUsername());
                    if(carritoEncontrado.obtenerItems().isEmpty()) {
                        carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.vacio"));
                    }else {
                        carritoModificarView.cargarDatos(carritoEncontrado);
                    }
                    carritoModificarView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularSubtotal(), mIH.getLocale()));
                    carritoModificarView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularIva(), mIH.getLocale()));
                    carritoModificarView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularTotal(), mIH.getLocale()));

                }catch (NumberFormatException ex){
                    carritoModificarView.mostrarMensaje("numberFormatException");

                }catch (NullPointerException ex) {
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

        carritoModificarView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(carritoModificarView.getTable1().getSelectedRow() != -1) {
                    int respuesta = JOptionPane.showConfirmDialog(
                            carritoModificarView,
                            mIH.get("mensaje.carrito.confirmacion.producto"),
                            mIH.get("mensaje.confirmacion.titulo"),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (respuesta == JOptionPane.YES_OPTION) {
                        int codigoCarrito = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
                        Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigoCarrito);
                        int codigoProducto = (Integer) carritoModificarView.getTable1().getValueAt(
                                carritoModificarView.getTable1().getSelectedRow(), 0);
                        carritoEncontrado.eliminarProducto(codigoProducto);
                        carritoDao.actualizar(carritoEncontrado);
                        carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.producto.eliminado"));
                        carritoModificarView.cargarDatos(carritoEncontrado);

                        carritoModificarView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularSubtotal(), mIH.getLocale()));
                        carritoModificarView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularIva(), mIH.getLocale()));
                        carritoModificarView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularTotal(), mIH.getLocale()));

                    } else {
                        carritoModificarView.mostrarMensaje(mIH.get("mensaje.cancelar"));
                    }
                } else {
                    carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.seleccionar.eliminar"));
                }
            }
        });

        carritoModificarView.getAnadirButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigoCarrito = Integer.parseInt(carritoModificarView.getCodigoTextField().getText());
                Carrito carritoEncontrado = carritoDao.buscarPorCodigo(codigoCarrito);

                int codigoNuevoProducto = Integer.parseInt(JOptionPane.showInputDialog(carritoModificarView, mIH.get("mensaje.carrito.ingresar.producto")));
                for(ItemCarrito item : carritoEncontrado.obtenerItems()) {
                    if (item.getProducto().getCodigo() == codigoNuevoProducto) {
                        carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.producto.ya.registrado.editar"));
                        return;
                    }
                }
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(carritoModificarView, mIH.get("mensaje.carrito.ingresar.cantidad")));

                carritoEncontrado.agregarProducto(productoDao.buscarPorCodigo(codigoNuevoProducto), cantidad);
                carritoDao.actualizar(carritoEncontrado);
                carritoModificarView.mostrarMensaje(mIH.get("mensaje.carrito.modificado"));
                carritoModificarView.cargarDatos(carritoEncontrado);
                carritoModificarView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularSubtotal(), mIH.getLocale()));
                carritoModificarView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularIva(), mIH.getLocale()));
                carritoModificarView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carritoEncontrado.calcularTotal(), mIH.getLocale()));

            }
        });

    }

    /**
     * Configura los eventos para listar y buscar carritos.
     * Permite listar todos los carritos y buscar uno específico por código.
     */
    private void configurarEventosListar() {

        carritoListaView.getListarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                carritoListaView.limpiarCampos();
                try{

                    if(usuario.getRol().equals(Rol.USUARIO)){
                        carritoListaView.cargarDatosLista(carritoDao.listarPorUsuario(usuario));
                    }else{
                        carritoListaView.cargarDatosLista(carritoDao.listarTodos());
                    }

                }catch(NullPointerException ex){
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

                try{
                    int codigo = Integer.parseInt(carritoListaView.getCodigoTextField().getText());
                    Carrito carritoEncontrado = null;

                    if(usuario.getRol().equals(Rol.USUARIO)){
                        carritoEncontrado = carritoDao.buscarPorCodigoYUsuario(codigo, usuario);

                    }else{
                        carritoEncontrado = carritoDao.buscarPorCodigo(codigo);
                    }

                    carritoListaView.cargarDatosBusqueda(carritoDao.buscarPorCodigo(codigo));

                }catch(NumberFormatException ex){
                    carritoListaView.mostrarMensaje(mIH.get("numberFormatException"));
                }catch(NullPointerException ex){
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

    /**
     * Busca un producto en el carrito por su código y muestra sus datos en la vista de creación.
     *
     * @param codigo Código del producto a buscar.
     */
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

    /**
     * Añade un producto al carrito por su código.
     * Si el producto ya existe en el carrito, incrementa la cantidad.
     *
     * @param codigo Código del producto a añadir.
     */
    private void anadirProductoEnCarrito(int codigo){
        Producto productoEncontrado =  productoDao.buscarPorCodigo(codigo);
        if(!verificarProductoEnCarrito(productoEncontrado)){
            int cantidad = (Integer) carritoCrearView.getCantidadComboBox().getSelectedItem();
            carrito.agregarProducto(productoEncontrado, cantidad);
            carritoCrearView.cargarDatos(carrito);
        }else{
            for (ItemCarrito item : carrito.obtenerItems()) {
                if (item.getProducto().getCodigo() == productoEncontrado.getCodigo()) {
                    item.setCantidad(item.getCantidad() + (Integer) carritoCrearView.getCantidadComboBox().getSelectedItem());
                }
            }
            carritoCrearView.cargarDatos(carrito);
            carritoCrearView.mostrarMensaje(mIH.get("mensaje.carrito.producto.ya.registrado"));
        }
        carritoCrearView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), mIH.getLocale()));
        carritoCrearView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carrito.calcularIva(), mIH.getLocale()));
        carritoCrearView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carrito.calcularTotal(), mIH.getLocale()));

    }

    /**
     * Verifica si un producto ya está en el carrito.
     *
     * @param producto Producto a verificar.
     * @return true si el producto está en el carrito, false en caso contrario.
     */
    private boolean verificarProductoEnCarrito(Producto producto) {
        for (ItemCarrito item : carrito.obtenerItems()) {
            if (item.getProducto().getCodigo() == producto.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Refresca la tabla de la vista de lista de carritos,
     * actualizando los formatos de fecha y moneda según el locale.
     *
     * @param locale Locale para formatear los datos.
     */
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

    /**
     * Refresca los datos de la vista de eliminar carrito,
     * actualizando los formatos de precios y totales según el locale.
     *
     * @param locale Locale para formatear los datos.
     */
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

    /**
     * Refresca los datos de la vista de modificar carrito,
     * actualizando los formatos de precios y totales según el locale.
     *
     * @param locale Locale para formatear los datos.
     */
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

    /**
     * Refresca los datos de la vista de crear carrito,
     * actualizando los formatos de precios y totales según el locale.
     *
     * @param locale Locale para formatear los datos.
     */
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

        carritoCrearView.getSubtotalTextField().setText(FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), mIH.getLocale()));
        carritoCrearView.getIvaTextField().setText(FormateadorUtils.formatearMoneda(carrito.calcularIva(), mIH.getLocale()));
        carritoCrearView.getTotalTextField().setText(FormateadorUtils.formatearMoneda(carrito.calcularTotal(), mIH.getLocale()));
    }

    /**
     * Refresca los datos de la vista de items de un carrito,
     * actualizando los formatos de precios según el locale.
     *
     * @param locale Locale para formatear los datos.
     */
    private void refrescarItemLista(Locale locale) {
        int rowCount = itemListaView.getModelo().getRowCount();

        for (int i = 0; i < rowCount; i++) {

            int codigoP = (Integer) itemListaView.getModelo().getValueAt(i, 0);

            Producto producto = productoDao.buscarPorCodigo(codigoP);
            String nuevoPrecioFormateado = FormateadorUtils.formatearMoneda(producto.getPrecio(), locale);

            itemListaView.getModelo().setValueAt(nuevoPrecioFormateado, i, 2);
        }
    }

    /**
     * Establece el código del carrito automáticamente,
     * incrementando respecto al último carrito registrado.
     */
    private void setCodigoCarrito(){
        if(!carritoDao.listarTodos().isEmpty()){
            carrito.setCodigo(carritoDao.listarTodos().getLast().getCodigo()+1);
        }
    }

    /**
     * Cambia el idioma de todas las vistas relacionadas con carritos,
     * usando el handler de internacionalización y refrescando los datos según el nuevo locale.
     *
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
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
