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
import java.awt.event.ActionListener;

public class ItemListaView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField codigoDelCarritoTextField;
    private JTable table1;
    private JButton salirButton;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para mostrar los items de un carrito.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public ItemListaView(MensajeInternacionalizacionHandler mIH) {
        super(mIH.get("ventana.carrito.items"), true, true, true, true);
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 500);
        setFrameIcon(Icono.icono(Url.VER));

        modelo = new DefaultTableModel();
        table1.setModel(modelo);

        cambiarIdioma(mIH.getLocale().getLanguage(), mIH.getLocale().getCountry());

        salirButton.setIcon(Icono.icono(Url.CERRAR));

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible(false);
            }
        });
    }

    /**
     * Carga los datos de los items del carrito en la tabla.
     * Llena la tabla con el código, nombre, precio y cantidad de cada producto en el carrito.
     * @param carrito Carrito cuyos items se mostrarán.
     */
    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);
        codigoDelCarritoTextField.setText(String.valueOf(carrito.getCodigo()));
        for(ItemCarrito itemCarrito: carrito.obtenerItems()){
            Producto producto = itemCarrito.getProducto();
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    FormateadorUtils.formatearMoneda(producto.getPrecio(), mIH.getLocale()),
                    itemCarrito.getCantidad()
            };
            modelo.addRow(fila);
        }
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * Actualiza los textos de la interfaz según el lenguaje y país especificados.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        Object[] columnas = {
                mIH.get("ventana.producto.codigo"),
                mIH.get("ventana.producto.nombre"),
                mIH.get("ventana.producto.precio"),
                mIH.get("ventana.carrito.cantidad")
        };
        modelo.setColumnIdentifiers(columnas);
        setTitle(mIH.get("ventana.carrito.items"));
        salirButton.setText(mIH.get("ventana.salir"));
        lblCodigo.setText(mIH.get("ventana.carrito.codigo"));
        lblTitulo.setText(mIH.get("ventana.carrito.items"));
    }

    /**
     * Obtiene la tabla de items del carrito.
     * @return JTable de items.
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

}
