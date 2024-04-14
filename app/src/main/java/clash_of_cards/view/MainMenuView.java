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
        initializeComponents();
        setupUI();
        setupMainFrame();
    }

    private void initializeComponents() {
        startGame = new JButton("Start Game");
        instructions = new JButton("Instructions");
        highScores = new JButton("High Scores");
        
        ButtonUtils.customButton(startGame);
        ButtonUtils.customButton(instructions);
        ButtonUtils.customButton(highScores);
    }

    private void setupUI() {
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
    }

    private void setupMainFrame() {
        mainFrame = new JFrame("Clash of Cards - Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        
        startGame.addActionListener(e -> {
            mainFrame.setVisible(false);
            Table table = new Table(this);
            table.showTable();
        });
    }

    public void showMainMenu() {
        mainFrame.setVisible(true);
    }
}
