package clash_of_cards.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

public class RoundJPanel extends JPanel {
    private static final RenderingHints HINTS = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private final int arcWidth = 20;
    private final int arcHeight = 20;
    private final int borderWidth = 1;
    private final Color borderColor;

    public RoundJPanel(int width, int height, int x, int y, Color borderColor) {
        super();
        this.setSize(width, height);
        this.setLocation(x, y);
        this.setOpaque(false);
        this.setLayout(null);
        this.borderColor = borderColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(HINTS);
        
        int width = getWidth();
        int height = getHeight();

        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, width - borderWidth, height - borderWidth, arcWidth, arcHeight);
    }
}