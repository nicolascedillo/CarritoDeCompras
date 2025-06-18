package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarritoController {

    private final CarritoAnadirView carritoAnadirView;

    public CarritoController( CarritoAnadirView carritoAnadirView)  {
        this.carritoAnadirView = carritoAnadirView;
        configurarEventosAnadir();
    }


    private void configurarEventosAnadir(){

        carritoAnadirView.getGuardarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Carrito carrito = new Carrito();

                for (int i = 0; i < carritoAnadirView.getModelo().getRowCount(); i++) {
                    int codigo = Integer.parseInt(carritoAnadirView.getModelo().getValueAt(i, 0).toString());
                    String nombre = carritoAnadirView.getModelo().getValueAt(i, 1).toString();
                    double precio = Double.parseDouble(carritoAnadirView.getModelo().getValueAt(i, 2).toString());
                    int cantidad = Integer.parseInt(carritoAnadirView.getModelo().getValueAt(i, 3).toString());

                    Producto producto = new Producto(codigo, nombre, precio);
                    carrito.agregarProducto(producto, cantidad);

                    System.out.println(carrito);
                }

            }
        });
    }

}
