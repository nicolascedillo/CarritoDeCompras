package ec.edu.ups.vista.carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoListaView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField codigoTextField;
    private JButton listarButton;
    private JButton buscarButton;
    private JTable table1;
    private JButton verDetallesButton;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JLabel lblCarrito;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para listar carritos.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public CarritoListaView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("menu.carrito.buscar"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 700);
        setFrameIcon(Icono.icono(Url.BUSCAR));

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        buscarButton.setIcon(Icono.icono(Url.BUSCAR));
        listarButton.setIcon(Icono.icono(Url.LISTAR));
        verDetallesButton.setIcon(Icono.icono(Url.VER));

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
     * Carga los datos de una lista de carritos en la tabla.
     * @param carritos Lista de carritos a mostrar.
     */
    public void cargarDatosLista(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            Object[] fila = {
                carrito.getCodigo(),
                    FormateadorUtils.formatearFecha(carrito.getFechaCreacionDate(),mIH.getLocale()),
                carrito.getUsuario().getUsername(),
                FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularIva(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularTotal(),mIH.getLocale())
            };
            modelo.addRow(fila);
        }
    }

    /**
     * Carga los datos de un carrito buscado en la tabla.
     * @param carrito Carrito a mostrar.
     */
    public void cargarDatosBusqueda(Carrito carrito) {
        modelo.setRowCount(0);
        Object[] fila = {
                carrito.getCodigo(),
                FormateadorUtils.formatearFecha(carrito.getFechaCreacionDate(),mIH.getLocale()),
                carrito.getUsuario().getUsername(),
                FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularIva(),mIH.getLocale()),
                FormateadorUtils.formatearMoneda(carrito.calcularTotal(),mIH.getLocale())
        };
        modelo.addRow(fila);
    }

    /**
     * Limpia el campo de código y la tabla.
     */
    public void limpiarCampos() {
        codigoTextField.setText("");
        modelo.setRowCount(0);
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.carrito.buscar"));
        String[] columnas = {mIH.get("ventana.carrito.codigo"), mIH.get("ventana.carrito.fecha"), mIH.get("ventana.carrito.usuario"), mIH.get("ventana.carrito.subtotal"), mIH.get("ventana.carrito.iva"), mIH.get("ventana.carrito.total")};
        modelo.setColumnIdentifiers(columnas);
        lblTitulo.setText(mIH.get("ventana.carrito.buscar.titulo"));
        lblCodigo.setText(mIH.get("ventana.carrito.codigo"));
        listarButton.setText(mIH.get("ventana.listar"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        verDetallesButton.setText(mIH.get("ventana.carrito.buscar.detalles"));
        lblCarrito.setText(mIH.get("ventana.carrito.buscar.encontrados"));
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
     * Obtiene el botón para listar carritos.
     * @return JButton de listar.
     */
    public JButton getListarButton() {
        return listarButton;
    }

    /**
     * Obtiene el botón para buscar carritos.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

    /**
     * Obtiene la tabla de carritos.
     * @return JTable de carritos.
     */
    public JTable getTable1() {
        return table1;
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return DefaultTableModel de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Obtiene el botón para ver detalles de un carrito.
     * @return JButton de ver detalles.
     */
    public JButton getVerDetallesButton() {
        return verDetallesButton;
    }
}
