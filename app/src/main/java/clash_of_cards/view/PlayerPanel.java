package clash_of_cards.view;

import clash_of_cards.model.ScoreObserver;
import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel implements ScoreObserver {
    private static final Color FOREGROUND_COLOR = Color.WHITE;
    private static final Font NAME_FONT = new Font("Consolas", Font.BOLD, 18);
    private static final Font SCORE_FONT = new Font("Consolas", Font.BOLD, 18);
    private static final Color SCORE_COLOR = new Color(255, 205, 0);
    private static final Color BUTTON_BACKGROUND = new Color(50, 50, 50);
    private static final Font BUTTON_FONT = new Font("Consolas", Font.BOLD, 16);

    private JLabel nameLabel;
    private JLabel scoreLabel;

    public PlayerPanel(String playerName, int score, Runnable viewCardsAction) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        nameLabel = new JLabel(playerName);
        nameLabel.setForeground(FOREGROUND_COLOR);
        nameLabel.setFont(NAME_FONT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel(String.valueOf(score));
        scoreLabel.setForeground(SCORE_COLOR);        
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

        this.add(nameLabel);
        this.add(scoreLabel);
        this.add(viewCardsButton);
    }

    @Override
    public void updateScore(int newScore) {
        scoreLabel.setText(String.valueOf(newScore));
        this.revalidate();
        this.repaint();
    }
}
