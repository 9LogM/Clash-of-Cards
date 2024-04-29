package clash_of_cards.view;

import clash_of_cards.model.ScoreObserver;
import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel implements ScoreObserver {
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private JButton viewCardsButton;
    private String playerName;

    public PlayerPanel(String playerName, int score, Runnable viewCardsAction) {
        this.playerName = playerName;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameLabel = new JLabel(playerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel(String.valueOf(score));
        scoreLabel.setForeground(new Color(255, 205, 0));        
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        viewCardsButton = new JButton("View Cards");
        viewCardsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCardsButton.setForeground(Color.WHITE);
        viewCardsButton.setBackground(new Color(50, 50, 50));
        viewCardsButton.setBorderPainted(false);
        viewCardsButton.setFocusPainted(false);
        viewCardsButton.setFont(new Font("Consolas", Font.BOLD, 16));
        viewCardsButton.addActionListener(e -> viewCardsAction.run());

        this.add(nameLabel);
        this.add(scoreLabel);
        this.add(viewCardsButton);
    }

    @Override
    public void updateScore(int newScore) {
        scoreLabel.setText(String.valueOf(newScore));
    }

    public void setJudge(boolean isJudge) {
        viewCardsButton.setText(isJudge ? "Judge" : "View Cards");
        viewCardsButton.setEnabled(!isJudge);
    }

    public String getPlayerName() {
        return playerName;
    }
}
