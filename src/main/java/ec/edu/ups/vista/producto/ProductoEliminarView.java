package ec.edu.ups.vista.producto;

import ec.edu.ups.util.Icono;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.util.Url;

import javax.swing.*;

public class ProductoEliminarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JLabel lblNombre;
    private JButton salirButton;
    private JButton btnEliminar;
    private JButton buscarButton;
    private JLabel lblTitulo;
    private JOptionPane jOptionPane;
    private MensajeInternacionalizacionHandler mIH;

    /**
     * Constructor de la ventana para eliminar productos.
     * Inicializa componentes, configura iconos y carga el idioma usando el handler de internacionalización.
     * @param mIH Handler de internacionalización.
     */
    public ProductoEliminarView(MensajeInternacionalizacionHandler mIH) {
        this.mIH = mIH;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450,200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
        setFrameIcon(Icono.icono(Url.ELIMINAR));

        btnEliminar.setIcon(Icono.icono(Url.ELIMINAR));
        buscarButton.setIcon(Icono.icono(Url.BUSCAR));

        setTitle(mIH.get("menu.producto.eliminar"));
        lblTitulo.setText(mIH.get("ventana.producto.eliminar.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        btnEliminar.setText(mIH.get("ventana.eliminar"));
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos del formulario de eliminación de producto.
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
        setTitle(mIH.get("menu.producto.eliminar"));
        lblTitulo.setText(mIH.get("ventana.producto.eliminar.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        buscarButton.setText(mIH.get("ventana.buscar"));
        btnEliminar.setText(mIH.get("ventana.eliminar"));
    }

    //GETTERS Y SETTERS

    /**
     * Obtiene el panel principal de la ventana.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Establece el panel principal de la ventana.
     * @param panelPrincipal JPanel a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Obtiene la etiqueta del precio.
     * @return JLabel del precio.
     */
    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    /**
     * Establece la etiqueta del precio.
     * @param lblPrecio JLabel a asignar.
     */
    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    /**
     * Obtiene el campo de texto para el precio.
     * @return JTextField del precio.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * Establece el campo de texto para el precio.
     * @param txtPrecio JTextField a asignar.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * Obtiene el campo de texto para el nombre.
     * @return JTextField del nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Establece el campo de texto para el nombre.
     * @param txtNombre JTextField a asignar.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Obtiene la etiqueta del código.
     * @return JLabel del código.
     */
    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    /**
     * Establece la etiqueta del código.
     * @param lblCodigo JLabel a asignar.
     */
    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    /**
     * Obtiene el campo de texto para el código.
     * @return JTextField del código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Establece el campo de texto para el código.
     * @param txtCodigo JTextField a asignar.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Obtiene la etiqueta del nombre.
     * @return JLabel del nombre.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Establece la etiqueta del nombre.
     * @param lblNombre JLabel a asignar.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * Obtiene el botón para salir.
     * @return JButton de salir.
     */
    public JButton getSalirButton() {
        return salirButton;
    }

    /**
     * Establece el botón para salir.
     * @param salirButton JButton a asignar.
     */
    public void setSalirButton(JButton salirButton) {
        this.salirButton = salirButton;
    }

    /**
     * Obtiene el botón para eliminar.
     * @return JButton de eliminar.
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * Establece el botón para eliminar.
     * @param btnEliminar JButton a asignar.
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * Obtiene el botón para buscar.
     * @return JButton de buscar.
     */
    public JButton getBuscarButton() {
        return buscarButton;
    }

    /**
     * Establece el botón para buscar.
     * @param buscarButton JButton a asignar.
     */
    public void setBuscarButton(JButton buscarButton) {
        this.buscarButton = buscarButton;
    }

    /**
     * Obtiene el JOptionPane de la ventana.
     * @return JOptionPane.
     */
    public JOptionPane getjOptionPane() {
        return jOptionPane;
    }

    /**
     * Obtiene la etiqueta del título.
     * @return JLabel del título.
     */
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    /**
     * Establece la etiqueta del título.
     * @param lblTitulo JLabel a asignar.
     */
    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }

    /**
     * Establece el JOptionPane de la ventana.
     * @param jOptionPane JOptionPane a asignar.
     */
    public void setjOptionPane(JOptionPane jOptionPane) {
        this.jOptionPane = jOptionPane;
    }

}
