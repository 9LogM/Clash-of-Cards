package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class WhiteCardPanel {
    private static final Dimension CARD_SIZE = new Dimension(200, 150);
    private static final Font CARD_FONT = new Font("Consolas", Font.PLAIN, 16);

    public static JToggleButton createWhiteCard(String cardText, Runnable onSelection) {
        JToggleButton cardButton = new JToggleButton(cardText);
        cardButton.addActionListener(e -> {
            if (cardButton.isSelected()) {
                onSelection.run();
            }
        });
        cardButton.setPreferredSize(CARD_SIZE);
        cardButton.setFont(CARD_FONT);
        cardButton.setOpaque(true);
        cardButton.setForeground(Color.BLACK);
        cardButton.setBackground(new Color(255, 255, 255));

        return cardButton;
    }
}
