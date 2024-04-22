package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class WhiteCardPanel {
    private static final Dimension CARD_SIZE = new Dimension(200, 250);
    private static final Font CARD_FONT = new Font("Consolas", Font.PLAIN, 16);

    public static JToggleButton createWhiteCard(String cardText, Runnable onSelection) {
        String htmlCardText = "<html><center>" + cardText + "</center></html>";
        JToggleButton cardButton = new JToggleButton(htmlCardText);
        cardButton.addActionListener(e -> {
            if (cardButton.isSelected()) {
                onSelection.run();
            }
        });
        
        cardButton.setPreferredSize(CARD_SIZE);
        cardButton.setFont(CARD_FONT);
        cardButton.setOpaque(false);
        cardButton.setContentAreaFilled(false);
        cardButton.setBorderPainted(false);
        cardButton.setForeground(Color.BLACK);
        cardButton.setBackground(new Color(255, 255, 255));

        RoundJPanel roundPanel = new RoundJPanel(CARD_SIZE.width, CARD_SIZE.height, Color.BLACK);
        roundPanel.setLayout(new BorderLayout());
        roundPanel.add(cardButton, BorderLayout.CENTER);

        JToggleButton wrapperToggleButton = new JToggleButton();
        wrapperToggleButton.add(roundPanel);
        wrapperToggleButton.setPreferredSize(CARD_SIZE);
        wrapperToggleButton.setContentAreaFilled(false);
        wrapperToggleButton.setBorderPainted(false);
        wrapperToggleButton.setOpaque(false);

        return wrapperToggleButton;
    }
}