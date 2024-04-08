package clash_of_cards.view;

import java.awt.*;
import javax.swing.*;

public class MainMenuView {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JButton startGame;
    private JButton instructions;
    private JButton highScores;

    public MainMenuView() {
        startGame = new JButton("Start Game");
        instructions = new JButton("Instructions");
        highScores = new JButton("High Scores");

        customButton(startGame);
        customButton(instructions);
        customButton(highScores);

        mainFrame = new JFrame("Clash of Cards - Main Menu");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(80, 80, 80));

        titleLabel = new JLabel("Clash of Cards");
        titleLabel.setFont(new Font("Consolas", Font.PLAIN, 100));
        titleLabel.setForeground(new Color(240, 240, 240));
        titleLabel.setHorizontalAlignment(JLabel.CENTER); 

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(70, 70, 70));
        buttonPanel.add(startGame);
        buttonPanel.add(instructions);
        buttonPanel.add(highScores);

        mainPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private void customButton(JButton button) {
        button.setForeground(new Color(240, 240, 240));
        button.setBackground(new Color(50, 50, 50));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Consolas", Font.BOLD, 20)); 
    }
}
