package ec.edu.ups.util;

import javax.swing.*;

public class Icono {

    /**
     * Retorna el icono correspondiente al tipo de Url solicitado.
     * @param url Tipo de icono a cargar.
     * @return ImageIcon correspondiente al recurso solicitado, o null si no se encuentra.
     */
    public static ImageIcon icono(Url url) {
        switch (url){
            case BUSCAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/buscar.png"));
            case CARRITO:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/carrito.png"));
            case CERRAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/cerrar.png"));
            case CREAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/crear.png"));
            case ELIMINAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/eliminar.png"));
            case GUARDAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/guardar.png"));
            case IDIOMA:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/idioma.png"));
            case INICIAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/iniciar.png"));
            case LIMPIAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/limpiar.png"));
            case LISTAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/listar.png"));
            case MODIFICAR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/modificar.png"));
            case PRODUCTO:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/producto.png"));
            case SALIR:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/salir.png"));
            case SIGUIENTE:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/siguiente.png"));
            case USUARIO:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/usuario.png"));
            case VER:
                return new ImageIcon(Icono.class.getClassLoader().getResource("imagenes/ver.png"));
            default:
                System.err.println("No se pudo cargar el icono.");
                return null;
        }
    }
}
