package componen_tambahan;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel_Gradient extends JPanel {

    private Color colorStart = Color.BLACK;
    private Color colorEnd = Color.WHITE;

    public Panel_Gradient() {
        setOpaque(false);
    }

    public Color getColorStart() {
        return colorStart;
    }

    public void setColorStart(Color colorStart) {
        this.colorStart = colorStart;
        repaint();
    }

    public Color getColorEnd() {
        return colorEnd;
    }

    public void setColorEnd(Color colorEnd) {
        this.colorEnd = colorEnd;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gradient = new GradientPaint(
                0, 0, colorStart,
                getWidth(), getHeight(), colorEnd);

        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }
}