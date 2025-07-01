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

        // Fondo degradado
        GradientPaint gp = new GradientPaint(0, 0, new Color(234, 182, 118), w, h, new Color(220, 240, 255));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        // "Piso" donde se colocan los objetos
        int floorY = (int) (h * 0.75);

        // Bolsas de compras: aumentar la altura
        int[] bagXs = { w / 16, w / 16 + w / 7, w / 16 + 2 * w / 7, w / 16 + 3 * w / 7 };
        int[] bagWidths = { 120, 100, 130, 90 };
        int[] bagHeights = { 300, 260, 320, 240 }; // Alturas aumentadas
        Color[] bagColors = { new Color(255, 200, 80), new Color(120, 200, 120), new Color(255, 120, 120), new Color(120, 180, 255) };

        int bagY = floorY - 320;

        for (int i = 0; i < bagXs.length; i++) {
            drawBag(g2d, bagXs[i], bagY + (320 - bagHeights[i]), bagWidths[i], bagHeights[i], bagColors[i]);
        }

        // Solo 2 carritos en la parte derecha
        int[] cartXs = { w - w / 3, w - w / 6 };
        int[] cartWidths = { 180, 160 };
        int[] cartHeights = { 70, 60 };
        int cartY = floorY - 100;

        for (int i = 0; i < cartXs.length; i++) {
            drawCartWithGift(g2d, cartXs[i], cartY, cartWidths[i], cartHeights[i]);
        }

        // Regalos distribuidos entre bolsas y carritos, sin superposición
        int giftsPerRow = 8;
        int giftSpacing = w / (giftsPerRow + 1);
        int giftY = floorY - 50;
        for (int i = 1; i <= giftsPerRow; i++) {
            int gx = i * giftSpacing - 25;
            int size = 40 + (i % 3) * 10;
            Color[] giftColors = {
                new Color(240, 90, 90), new Color(90, 180, 240), new Color(255, 200, 80),
                new Color(180, 120, 255), new Color(120, 220, 180), new Color(255, 120, 200),
                new Color(120, 255, 180), new Color(120, 120, 255)
            };
            drawGiftBox(g2d, gx, giftY, size, size, giftColors[i - 1]);
        }

        // Nombre creativo del sistema
        String nombre = "GiftCartopia";
        String subtitulo = "¡Donde las compras y los regalos se encuentran!";

        g2d.setFont(new Font("Serif", Font.BOLD, 54));
        FontMetrics fm = g2d.getFontMetrics();
        int xTexto = (w - fm.stringWidth(nombre)) / 2;
        g2d.setColor(new Color(40, 40, 60, 220));
        g2d.drawString(nombre, xTexto, 90);

        g2d.setFont(new Font("SansSerif", Font.PLAIN, 22));
        g2d.setColor(new Color(60, 60, 80, 180));
        //g2d.drawString(subtitulo, (w - g2d.getFontMetrics().stringWidth(subtitulo)) / 2, 120);

        g2d.dispose();
    }

    // Dibuja una bolsa de compras simple
    private void drawBag(Graphics2D g2d, int x, int y, int width, int height, Color color) {
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, width, height, 24, 24);
        g2d.setColor(color.darker());
        g2d.drawRoundRect(x, y, width, height, 24, 24);
        // Asas
        g2d.setStroke(new BasicStroke(5));
        g2d.drawArc(x + 14, y - 30, width / 2, 50, 0, 180);
        g2d.drawArc(x + width / 2 - 14, y - 30, width / 2, 50, 0, 180);
    }

    // Dibuja un carrito de compras grande con un regalo dentro
    private void drawCartWithGift(Graphics2D g2d, int x, int y, int width, int height) {
        // Carrito
        g2d.setColor(new Color(180, 180, 180));
        g2d.fillRect(x, y, width, height); // base
        g2d.setColor(new Color(120, 120, 120));
        g2d.drawRect(x, y, width, height);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(x + 20, y, x + 20, y - height); // manija
        g2d.drawLine(x + 20, y - height, x + width - 20, y - height);
        g2d.drawLine(x + width - 20, y - height, x + width - 20, y);

        // Ruedas
        g2d.setColor(new Color(80, 80, 80));
        g2d.fillOval(x + 20, y + height - 10, 28, 28);
        g2d.fillOval(x + width - 48, y + height - 10, 28, 28);

        // Regalo dentro del carrito
        drawGiftBox(g2d, x + width / 2 - 25, y - 40, 50, 50, new Color(255, 120, 120));
    }

    // Dibuja una caja de regalo con lazo
    private void drawGiftBox(Graphics2D g2d, int x, int y, int width, int height, Color boxColor) {
        g2d.setColor(boxColor);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(boxColor.darker());
        g2d.drawRect(x, y, width, height);

        // Lazo vertical
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x + width / 2 - 5, y, 10, height);
        // Lazo horizontal
        g2d.fillRect(x, y + height / 2 - 5, width, 10);

        // Moño
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x + width / 2 - 12, y - 14, 24, 24);
    }
}
