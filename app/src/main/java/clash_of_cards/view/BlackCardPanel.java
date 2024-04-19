package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class BlackCardPanel {
    private static final int CARD_WIDTH = 400;
    private static final int CARD_HEIGHT = 200;
    private static final Color TEXT_COLOR = new Color(240, 240, 240);
    private static final Font TEXT_FONT = new Font("Consolas", Font.PLAIN, 20);

    public static JPanel createBlackCard(String sentence) {
        JPanel cardContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardContainer.setOpaque(false);

        RoundJPanel cardPanel = new RoundJPanel(CARD_WIDTH, CARD_HEIGHT, 50, 70, new Color(140, 140, 140));
        cardPanel.setBackground(new Color(40, 40, 40));
        cardPanel.setLayout(new BorderLayout());

        JTextArea sentenceTextArea = new JTextArea(sentence);
        sentenceTextArea.setFont(TEXT_FONT);
        sentenceTextArea.setForeground(TEXT_COLOR);
        sentenceTextArea.setWrapStyleWord(true);
        sentenceTextArea.setLineWrap(true);
        sentenceTextArea.setEditable(false);
        sentenceTextArea.setOpaque(false);
        sentenceTextArea.setMargin(new Insets(20, 20, 20, 20));

        cardPanel.add(sentenceTextArea, BorderLayout.CENTER);
        cardContainer.add(cardPanel);

        return cardContainer;
    }
}
