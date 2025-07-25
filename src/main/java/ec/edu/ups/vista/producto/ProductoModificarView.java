package ec.edu.ups.vista.producto;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class ProductoModificarView extends JInternalFrame{
    private JPanel panelPrincipal;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JButton btnGuardar;
    private JLabel lblNombre;
    private JButton buscarButton;
    private JLabel lblTitulo;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para modificar productos.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public  ProductoModificarView(MensajeInternacionalizacionHandler mIH){
        this.mIH=mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450,200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
        setFrameIcon(Icono.icono(Url.MODIFICAR));

        btnGuardar.setIcon(Icono.icono(Url.GUARDAR));
        buscarButton.setIcon(Icono.icono(Url.BUSCAR));

        setTitle(mIH.get("menu.producto.modificar"));
        lblTitulo.setText(mIH.get("ventana.producto.modificar.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        btnGuardar.setText(mIH.get("ventana.guardar"));
        buscarButton.setText(mIH.get("ventana.buscar"));

    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos del formulario de modificación de producto.
     */
    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCodigo.setEnabled(true);
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
    }

    /**
     * Cambia el idioma de la ventana y sus componentes usando el handler de internacionalización.
     * @param lenguaje Código de idioma (ejemplo: "es", "en").
     * @param pais Código de país (ejemplo: "EC", "US").
     */
    public void cambiarIdioma(String lenguaje, String pais) {
        mIH.setLenguaje(lenguaje, pais);
        setTitle(mIH.get("menu.producto.modificar"));
        lblTitulo.setText(mIH.get("ventana.producto.modificar.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        btnGuardar.setText(mIH.get("ventana.guardar"));
        buscarButton.setText(mIH.get("ventana.buscar"));
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
     * Obtiene el botón para guardar los cambios.
     * @return JButton de guardar.
     */
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * Obtiene el botón para buscar productos.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

}
