package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel {
    private static final Color FOREGROUND_COLOR = Color.WHITE;
    private static final Font NAME_FONT = new Font("Consolas", Font.BOLD, 18);
    private static final Font SCORE_FONT = new Font("Consolas", Font.BOLD, 18);
    private static final Color BUTTON_BACKGROUND = new Color(50, 50, 50);
    private static final Font BUTTON_FONT = new Font("Consolas", Font.BOLD, 16);

    public static JPanel createPlayerPanel(String playerName, int score, Runnable viewCardsAction) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(playerName);
        nameLabel.setForeground(FOREGROUND_COLOR);
        nameLabel.setFont(NAME_FONT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel(String.valueOf(score));
        scoreLabel.setForeground(FOREGROUND_COLOR);
        scoreLabel.setFont(SCORE_FONT);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewCardsButton = new JButton("View Cards");
        viewCardsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCardsButton.setForeground(FOREGROUND_COLOR);
        viewCardsButton.setBackground(BUTTON_BACKGROUND);
        viewCardsButton.setBorderPainted(false);
        viewCardsButton.setFocusPainted(false);
        viewCardsButton.setFont(BUTTON_FONT);
        viewCardsButton.addActionListener(e -> viewCardsAction.run());

        playerPanel.add(nameLabel);
        playerPanel.add(scoreLabel);
        playerPanel.add(viewCardsButton);

        return playerPanel;
    }
}
