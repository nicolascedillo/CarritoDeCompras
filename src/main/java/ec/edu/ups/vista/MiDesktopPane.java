package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class MiDesktopPane extends JDesktopPane {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2d.setColor(new Color(250, 250, 250));
        g2d.fillRect(0, 0, w, h);

        Color grisOscuro = new Color(60, 60, 60);
        Color naranja = new Color(255, 140, 0);
        Color rojo = new Color(200, 60, 60);
        Color azul = new Color(70, 130, 180);
        Color verde = new Color(100, 180, 100);

        // ========= DIBUJAR VARIOS ELEMENTOS EN "AMONTONAMIENTO ORDENADO" =========

        int filas = 4;
        int columnas = 8;
        int separacionX = w / (columnas + 1);
        int separacionY = h / (filas + 1);

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = separacionX * (col + 1) - 30;
                int y = separacionY * (fila + 1) + 10;

                int tipo = (fila + col) % 3;

                switch (tipo) {
                    case 0: // carrito
                        drawCarrito(g2d, x, y);
                        break;
                    case 1: // bolsa
                        drawFunda(g2d, x, y, naranja, grisOscuro);

                        break;
                    case 2: // regalo
                        drawCajaRegalo(g2d, x, y, verde, rojo);
                        break;
                }
            }
        }

        // ========= TÍTULO CENTRAL =========
        String titulo = "Librarix Market";
        g2d.setFont(new Font("SansSerif", Font.BOLD, 48));
        FontMetrics fm = g2d.getFontMetrics();
        int xTitulo = (w - fm.stringWidth(titulo)) / 2;
        g2d.setColor(grisOscuro);
        g2d.drawString(titulo, xTitulo, 80);

        g2d.setFont(new Font("SansSerif", Font.ITALIC, 18));
        g2d.setColor(new Color(100, 100, 100));

        g2d.dispose();
    }

// === FUNCIONES DE DIBUJO AUXILIARES ===

    private void drawCarrito(Graphics2D g2d, int x, int y) {
        int ancho = 60, alto = 40;
        g2d.setColor(new Color(60, 60, 60));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(x, y, ancho, alto);

        g2d.setStroke(new BasicStroke(1));
        for (int i = 1; i < 3; i++) {
            g2d.drawLine(x + i * (ancho / 3), y, x + i * (ancho / 3), y + alto);
        }
        g2d.drawLine(x, y + alto / 2, x + ancho, y + alto / 2);

        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(x, y, x - 15, y - 15);

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(x + 5, y + alto, 10, 10);
        g2d.fillOval(x + ancho - 15, y + alto, 10, 10);
    }

    private void drawFunda(Graphics2D g2d, int x, int y, Color bolsa, Color asa) {
        g2d.setColor(bolsa);
        g2d.fillRoundRect(x, y, 40, 50, 10, 10);

        g2d.setColor(asa);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawArc(x + 5, y - 10, 10, 10, 0, 180);
        g2d.drawArc(x + 25, y - 10, 10, 10, 0, 180);
    }

    private void drawCajaRegalo(Graphics2D g2d, int x, int y, Color caja, Color lazo) {
        g2d.setColor(caja);
        g2d.fillRect(x, y, 50, 35);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(x + 20, y, 10, 35);
        g2d.fillRect(x, y + 15, 50, 5);

        g2d.setColor(lazo);
        g2d.fillOval(x + 12, y - 8, 10, 10);
        g2d.fillOval(x + 28, y - 8, 10, 10);
    }

}
