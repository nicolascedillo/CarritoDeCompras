package ec.edu.ups.vista.producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoCrearView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel lblCodigo;
    private JLabel lblPrecio;
    private JLabel lblNombre;
    private JLabel lblTitulo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para crear productos.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public ProductoCrearView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(Icono.icono(Url.CREAR));

        setTitle(mIH.get("menu.producto.crear"));
        lblTitulo.setText(mIH.get("ventana.producto.crear.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        btnAceptar.setText(mIH.get("ventana.aceptar"));
        btnLimpiar.setText(mIH.get("ventana.limpiar"));

        btnAceptar.setIcon(Icono.icono(Url.CREAR));
        btnLimpiar.setIcon(Icono.icono(Url.LIMPIAR));

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos del formulario de creación de producto.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }


    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.producto.crear"));
        lblTitulo.setText(mIH.get("ventana.producto.crear.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        btnAceptar.setText(mIH.get("ventana.aceptar"));
        btnLimpiar.setText(mIH.get("ventana.limpiar"));
    }

    /**
     * Obtiene el panel principal de la ventana.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene el campo de texto para el precio.
     * @return JTextField del precio.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * Obtiene el campo de texto para el nombre.
     * @return JTextField del nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Obtiene el campo de texto para el código.
     * @return JTextField del código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Obtiene el botón de aceptar.
     * @return JButton de aceptar.
     */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    /**
     * Obtiene el botón de limpiar.
     * @return JButton de limpiar.
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

}
