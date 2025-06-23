package ec.edu.ups.vista.carrito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoListaView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton listarButton;
    private JButton buscarButton;
    private JTable table1;
    private JTable table2;
    private DefaultTableModel modelo;
    private DefaultTableModel modelo2;

    public CarritoListaView() {
        setTitle("Buscar o Listar Carritos");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 700);
        modelo = new DefaultTableModel();
        String[] columnas = {"Código", "Fecha"};
        modelo.setColumnIdentifiers(columnas);
        table1.setModel(modelo);
    }

}
