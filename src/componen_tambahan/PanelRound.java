/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componen_tambahan;


import java.awt.*;
import javax.swing.JPanel;

public class PanelRound extends JPanel {

    private int roundTopLeft = 20;
    private int roundTopRight = 20;
    private int roundBottomLeft = 20;
    private int roundBottomRight = 20;

    public PanelRound() {
        setOpaque(false);
    }

    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                roundTopLeft, roundTopLeft);

        g2.dispose();
        super.paintComponent(grphcs);
    }
}