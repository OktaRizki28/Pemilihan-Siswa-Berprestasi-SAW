package componen_tambahan;

import java.awt.*;
import javax.swing.JButton;

public class RoundButton extends JButton {

    public RoundButton() {

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        setForeground(Color.WHITE);

        // Pengaturan icon dan text
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(RIGHT);
        setIconTextGap(5);

        // Margin kiri-kanan
        setMargin(new Insets(2, 10, 2, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                30,
                30);

        super.paintComponent(g);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground().darker());

        g2.drawRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                30,
                30);

        g2.dispose();
    }
}