package clash_of_cards.view;

import java.awt.*;
import javax.swing.*;
import clash_of_cards.utils.*;

public class MainMenuView {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JButton startGame, instructions, highScores;
    private JButton startFamilyEdition, startNerdEdition, backButton;

    public MainMenuView() {
        initializeComponents();
        setupUI();
        setupMainFrame();
    }

    private void initializeComponents() {
        startGame = new JButton("Start Game");
        instructions = new JButton("Instructions");
        highScores = new JButton("High Scores");
        startFamilyEdition = new JButton("Start Family Edition");
        startNerdEdition = new JButton("Start Nerd Edition");
        backButton = new JButton("Back to Main Menu");

        ButtonUtils.customButton(startGame);
        ButtonUtils.customButton(instructions);
        ButtonUtils.customButton(highScores);
        ButtonUtils.customButton(startFamilyEdition);
        ButtonUtils.customButton(startNerdEdition);
        ButtonUtils.customButton(backButton);
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

        startFamilyEdition.setVisible(false);
        startNerdEdition.setVisible(false);
        backButton.setVisible(false);

        buttonPanel.add(startFamilyEdition);
        buttonPanel.add(startNerdEdition);
        buttonPanel.add(backButton);

        mainPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupMainFrame() {
        mainFrame = new JFrame("Clash of Cards - Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        startGame.addActionListener(e -> toggleButtonVisibility());
        startFamilyEdition.addActionListener(e -> showGameView());
        startNerdEdition.addActionListener(e -> showGameView());
        backButton.addActionListener(e -> toggleButtonVisibility());
    }

    private void toggleButtonVisibility() {
        boolean showMainButtons = startGame.isVisible();

        startGame.setVisible(!showMainButtons);
        instructions.setVisible(!showMainButtons);
        highScores.setVisible(!showMainButtons);
        startFamilyEdition.setVisible(showMainButtons);
        startNerdEdition.setVisible(showMainButtons);
        backButton.setVisible(showMainButtons);

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void showGameView() {
        mainFrame.setVisible(false);
        GameView game = new GameView(this);
        game.showGameView();
    }

    public void showMainMenu() {
        mainFrame.setVisible(true);
    }
}
