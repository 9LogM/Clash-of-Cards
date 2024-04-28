package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class WhiteCard {
    private static final Dimension CARD_SIZE = new Dimension(170, 210);
    private static final Font CARD_FONT = new Font("Consolas", Font.PLAIN, 16);
    private static final Color HIGHLIGHT_COLOR = new Color(220, 248, 198);
    private static final Color NORMAL_BACKGROUND = Color.WHITE;

    public static JToggleButton createWhiteCard(String cardText, Runnable onSelection) {
        String htmlCardText = "<html><center>" + cardText + "</center></html>";
        JToggleButton cardButton = new JToggleButton(htmlCardText) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Shape clip = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setClip(clip);
                if (getModel().isSelected()) {
                    g2.setColor(HIGHLIGHT_COLOR);
                } else {
                    g2.setColor(NORMAL_BACKGROUND);
                }
                g2.fill(clip);
                super.paintComponent(g2);
            }
        };
        cardButton.addActionListener(e -> onSelection.run());
        cardButton.setPreferredSize(CARD_SIZE);
        cardButton.setFont(CARD_FONT);
        cardButton.setFocusPainted(false);
        cardButton.setContentAreaFilled(false);
        cardButton.setOpaque(false);
        return cardButton;
    }
}