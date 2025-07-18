package ec.edu.ups.vista.producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para listar y buscar productos.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public ProductoListaView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(Icono.icono(Url.BUSCAR));

        btnListar.setIcon(Icono.icono(Url.LISTAR));
        btnBuscar.setIcon(Icono.icono(Url.BUSCAR));

        modelo = new DefaultTableModel();
        Object[] columnas = {mIH.get("ventana.producto.codigo"), mIH.get("ventana.producto.nombre"), mIH.get("ventana.producto.precio")};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        setTitle(mIH.get("menu.producto.buscar"));
        lblTitulo.setText(mIH.get("ventana.producto.buscar.titulo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        btnBuscar.setText(mIH.get("ventana.buscar"));
        btnListar.setText(mIH.get("ventana.listar"));

    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Carga los datos de una lista de productos en la tabla.
     * @param listaProductos Lista de productos a mostrar.
     */
    public void cargarDatos(List<Producto> listaProductos) {
        modelo.setNumRows(0);

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    FormateadorUtils.formatearMoneda(producto.getPrecio(), mIH.getLocale())
            };
            modelo.addRow(fila);
        }

    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        Object[] columnas = {mIH.get("ventana.producto.codigo"), mIH.get("ventana.producto.nombre"), mIH.get("ventana.producto.precio")};
        modelo.setColumnIdentifiers(columnas);
        setTitle(mIH.get("menu.producto.buscar"));
        lblTitulo.setText(mIH.get("ventana.producto.buscar.titulo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        btnBuscar.setText(mIH.get("ventana.buscar"));
        btnListar.setText(mIH.get("ventana.listar"));
    }

    /**
     * Obtiene el campo de texto para buscar productos.
     * @return JTextField de búsqueda.
     */
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    /**
     * Obtiene el botón para buscar productos.
     * @return JButton de buscar.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Obtiene el panel principal de la ventana.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene el botón para listar productos.
     * @return JButton de listar.
     */
    public JButton getBtnListar() {
        return btnListar;
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return DefaultTableModel de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

}
