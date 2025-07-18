package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoModificarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField codigoTextField;
    private JTextField fechaTextField;
    private JButton buscarButton;
    private JTable table1;
    private JTextField subtotalTextField;
    private JTextField ivaTextField;
    private JTextField totalTextField;
    private JButton editarButton;
    private JTextField usuarioTextField;
    private JLabel lblTitulo;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JLabel lblPago;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JLabel lblUsuario;
    private JLabel lblDatos;
    private JButton eliminarButton;
    private JButton anadirButton;
    private JLabel lblLista;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para modificar un carrito.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public CarritoModificarView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.carrito.modificar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        setFrameIcon(Icono.icono(Url.MODIFICAR));

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        editarButton.setIcon(Icono.icono(Url.MODIFICAR));
        eliminarButton.setIcon(Icono.icono(Url.ELIMINAR));
        anadirButton.setIcon(Icono.icono(Url.CREAR));

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Carga los datos de los items del carrito en la tabla.
     * @param carrito Carrito cuyos items se mostrarán.
     */
    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);

        for(ItemCarrito itemCarrito: carrito.obtenerItems()){
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    FormateadorUtils.formatearMoneda(producto.getPrecio(), mIH.getLocale()),
                    itemCarrito.getCantidad(),
            };
            modelo.addRow(fila);
        }
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param language Código de idioma (ejemplo: "es", "en").
     * @param country Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String language, String country) {
        mIH.setLenguaje(language, country);
        setTitle(mIH.get("menu.carrito.modificar"));
        lblTitulo.setText(mIH.get("ventana.carrito.modificar.titulo"));
        lblSubtotal.setText(mIH.get("ventana.carrito.subtotal"));
        lblIva.setText(mIH.get("ventana.carrito.iva"));
        lblTotal.setText(mIH.get("ventana.carrito.total"));
        lblPago.setText(mIH.get("ventana.carrito.pago"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        editarButton.setText(mIH.get("ventana.editar"));
        eliminarButton.setText(mIH.get("ventana.eliminar"));
        anadirButton.setText(mIH.get("ventana.anadir"));
        lblCodigo.setText(mIH.get("ventana.carrito.codigo"));
        lblFecha.setText(mIH.get("ventana.carrito.fecha"));
        lblUsuario.setText(mIH.get("ventana.carrito.usuario"));
        lblDatos.setText(mIH.get("ventana.carrito.datos"));
        lblLista.setText(mIH.get("ventana.carrito.lista"));
        Object[] columnas = {
            mIH.get("ventana.producto.codigo"),
            mIH.get("ventana.producto.nombre"),
            mIH.get("ventana.producto.precio"),
            mIH.get("ventana.carrito.cantidad")
        };
        modelo.setColumnIdentifiers(columnas);
        lblPago.setText(mIH.get("ventana.carrito.pago"));
    }

    //GETTERS Y SETTERS

    /**
     * Obtiene el campo de texto para el código del carrito.
     * @return JTextField del código.
     */
    public JTextField getCodigoTextField() {
        return codigoTextField;
    }

    /**
     * Obtiene el campo de texto para la fecha del carrito.
     * @return JTextField de la fecha.
     */
    public JTextField getFechaTextField() {
        return fechaTextField;
    }

    /**
     * Obtiene el botón para buscar carrito.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

    /**
     * Obtiene el botón para editar el carrito.
     * @return JButton de editar.
     */
    public JButton getEditarButton() {
        return editarButton;
    }

    /**
     * Obtiene la tabla de productos del carrito.
     * @return JTable de productos.
     */
    public JTable getTable1() {
        return table1;
    }

    /**
     * Obtiene el campo de texto para el subtotal.
     * @return JTextField del subtotal.
     */
    public JTextField getSubtotalTextField() {
        return subtotalTextField;
    }

    /**
     * Obtiene el campo de texto para el IVA.
     * @return JTextField del IVA.
     */
    public JTextField getIvaTextField() {
        return ivaTextField;
    }

    /**
     * Obtiene el campo de texto para el total.
     * @return JTextField del total.
     */
    public JTextField getTotalTextField() {
        return totalTextField;
    }

    /**
     * Obtiene el campo de texto para el usuario.
     * @return JTextField del usuario.
     */
    public JTextField getUsuarioTextField() {
        return usuarioTextField;
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return DefaultTableModel de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Obtiene el botón para añadir producto al carrito.
     * @return JButton de añadir.
     */
    public JButton getAnadirButton() {
        return anadirButton;
    }

    /**
     * Obtiene el botón para eliminar producto del carrito.
     * @return JButton de eliminar.
     */
    public JButton getEliminarButton() {
        return eliminarButton;
    }
}
