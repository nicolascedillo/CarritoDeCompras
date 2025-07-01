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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

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

    // GETTERS Y SETTERS

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

}
