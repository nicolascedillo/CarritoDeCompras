package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

/**
 * MiDesktopPane es un JDesktopPane personalizado que dibuja un fondo temático
 * de compras con bolsas y texto, usando colores y formas llamativas.
 */
public class MiDesktopPane extends JDesktopPane {

    /**
     * Sobrescribe el método paintComponent para dibujar el fondo personalizado
     * con bolsas grandes, superpuestas y detalladas, además de texto decorativo.
     * @param g Objeto Graphics para dibujar en el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo
        g2.setColor(new Color(200, 230, 255));
        g2.fillRect(0, 0, getWidth(), getHeight() / 2);
        g2.setColor(new Color(255, 139, 106));
        g2.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Texto
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.BOLD, 30));
        String texto = "JAVA SHOPPING";
        int textWidth = g2.getFontMetrics().stringWidth(texto);
        g2.drawString(texto, (getWidth() - textWidth) / 2, getHeight() - 30);

        // Bolsita de compras grandes y superpuestas, más arriba y detalladas
        int bagWidth = getWidth() / 4 + 30;
        int bagHeight = getHeight() / 2 + 80;
        int bagY = getHeight() / 2 - bagHeight / 2 - 40; // levantadas

        // Bolsita azul
        int leftX = getWidth() / 2 - bagWidth - bagWidth / 7;
        int leftY = bagY + 110;
        g2.setColor(new Color(100, 200, 230));
        g2.fillRoundRect(leftX, leftY, bagWidth, bagHeight - 60, 40, 40);
        g2.setColor(new Color(60, 120, 160));
        g2.setStroke(new BasicStroke(6));
        g2.drawRoundRect(leftX, leftY, bagWidth, bagHeight - 60, 40, 40);
        g2.setStroke(new BasicStroke(8));
        g2.setColor(new Color(80, 80, 80));
        g2.drawArc(leftX + bagWidth / 8, leftY - 40, bagWidth / 3, 80, 0, 180);
        g2.drawArc(leftX + bagWidth - bagWidth / 8 - bagWidth / 3, leftY - 40, bagWidth / 3, 80, 0, 180);
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(leftX + bagWidth / 2 - 30, leftY + 30, 60, 30, 15, 15);
        g2.setColor(new Color(100, 200, 230).darker());
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("SALE", leftX + bagWidth / 2 - 18, leftY + 52);

        // Bolsita derecha
        int rightX = getWidth() / 2 + bagWidth / 7;
        int rightY = bagY + 110;
        g2.setColor(new Color(190, 80, 80));
        g2.fillRoundRect(rightX, rightY, bagWidth, bagHeight - 60, 40, 40);
        g2.setColor(new Color(120, 40, 40));
        g2.setStroke(new BasicStroke(6));
        g2.drawRoundRect(rightX, rightY, bagWidth, bagHeight - 60, 40, 40);
        g2.setStroke(new BasicStroke(8));
        g2.setColor(new Color(80, 80, 80));
        g2.drawArc(rightX + bagWidth / 8, rightY - 40, bagWidth / 3, 80, 0, 180);
        g2.drawArc(rightX + bagWidth - bagWidth / 8 - bagWidth / 3, rightY - 40, bagWidth / 3, 80, 0, 180);
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(rightX + bagWidth / 2 - 30, rightY + 30, 60, 30, 15, 15);
        g2.setColor(new Color(190, 80, 80).darker());
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString("NEW", rightX + bagWidth / 2 - 18, rightY + 52);

        // Bolsita central
        int centerX = getWidth() / 2 - bagWidth / 2;
        int centerY = bagY + 70;
        g2.setColor(new Color(255, 170, 60));
        g2.fillRoundRect(centerX, centerY, bagWidth, bagHeight, 50, 50);
        g2.setColor(new Color(200, 120, 30));
        g2.setStroke(new BasicStroke(8));
        g2.drawRoundRect(centerX, centerY, bagWidth, bagHeight, 50, 50);
        g2.setStroke(new BasicStroke(10));
        g2.setColor(new Color(120, 80, 40));
        g2.drawArc(centerX + bagWidth / 8, centerY - 50, bagWidth / 3, 100, 0, 180);
        g2.drawArc(centerX + bagWidth - bagWidth / 8 - bagWidth / 3, centerY - 50, bagWidth / 3, 100, 0, 180);
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRoundRect(centerX + bagWidth / 2 - 40, centerY + 40, 80, 36, 18, 18);
        g2.setColor(new Color(255, 170, 60).darker());
        g2.setFont(new Font("SansSerif", Font.BOLD, 18));
        g2.drawString("HOT!", centerX + bagWidth / 2 - 22, centerY + 65);

    }

}
