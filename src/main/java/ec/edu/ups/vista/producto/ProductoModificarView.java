package ec.edu.ups.vista.producto;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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

        setTitle(mIH.get("menu.producto.modificar"));
        lblTitulo.setText(mIH.get("ventana.producto.modificar.titulo"));
        lblCodigo.setText(mIH.get("ventana.producto.codigo"));
        lblNombre.setText(mIH.get("ventana.producto.nombre"));
        lblPrecio.setText(mIH.get("ventana.producto.precio"));
        btnGuardar.setText(mIH.get("ventana.guardar"));
        buscarButton.setText(mIH.get("ventana.buscar"));

    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

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

    //GETTERS Y SETTERS

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

}
