package clash_of_cards.view;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuView {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel nameEntryPanel;
    private JLabel titleLabel;
    private JButton startGame, instructions, highScores;
    private JButton startFamilyEdition, startNerdEdition, backButton, confirmNames;
    private JButton playerThree, playerFour, playerFive;
    private JTextField[] playerNames;
    private List<String> confirmedPlayerNames = new ArrayList<>();

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
        confirmNames = new JButton("Confirm Names");
        playerThree = new JButton("3 Players");
        playerFour = new JButton("4 Players");
        playerFive = new JButton("5 Players");
        playerNames = new JTextField[5];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = new JTextField(10);
            styleTextField(playerNames[i]);
        }
        nameEntryPanel = new JPanel(new FlowLayout());
        for (JTextField playerName : playerNames) {
            nameEntryPanel.add(playerName);
        }

        ButtonUtils.customButton(startGame);
        ButtonUtils.customButton(instructions);
        ButtonUtils.customButton(highScores);
        ButtonUtils.customButton(startFamilyEdition);
        ButtonUtils.customButton(startNerdEdition);
        ButtonUtils.customButton(backButton);
        ButtonUtils.customButton(confirmNames);
        ButtonUtils.customButton(playerThree);
        ButtonUtils.customButton(playerFour);
        ButtonUtils.customButton(playerFive);
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setPreferredSize(new Dimension(150, 50));
    }

    private void setupUI() {
        mainPanel = new JPanel(new GridBagLayout());
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
        confirmNames.setVisible(false);
        for (JButton playerButton : new JButton[]{playerThree, playerFour, playerFive}) {
            playerButton.setVisible(false);
        }
        for (JTextField playerName : playerNames) {
            playerName.setVisible(false);
        }
    
        buttonPanel.add(startFamilyEdition);
        buttonPanel.add(startNerdEdition);
        buttonPanel.add(backButton);
        buttonPanel.add(confirmNames);
        buttonPanel.add(playerThree);
        buttonPanel.add(playerFour);
        buttonPanel.add(playerFive);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(titleLabel, gbc);
    
        nameEntryPanel.setBackground(new Color(70, 70, 70));
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        mainPanel.add(nameEntryPanel, gbc);
    
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        mainPanel.add(buttonPanel, gbc);
    }

    private void setupMainFrame() {
        mainFrame = new JFrame("Clash of Cards - Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        startGame.addActionListener(e -> toggleButtonVisibility());
        startFamilyEdition.addActionListener(e -> showPlayerSelection());
        startNerdEdition.addActionListener(e -> showPlayerSelection());
        backButton.addActionListener(e -> showMainMenu());
        playerThree.addActionListener(e -> configureNameEntry(3));
        playerFour.addActionListener(e -> configureNameEntry(4));
        playerFive.addActionListener(e -> configureNameEntry(5));
        confirmNames.addActionListener(e -> {
            confirmedPlayerNames.clear();
            for (JTextField playerName : playerNames) {
                if (playerName.isVisible() && !playerName.getText().trim().isEmpty()) {
                    confirmedPlayerNames.add(playerName.getText().trim());
                }
            }
            showGameView(startFamilyEdition.isVisible() ? "Family" : "Nerd");
        });
    }

    private void toggleButtonVisibility() {
        boolean showMainButtons = startGame.isVisible();

        startGame.setVisible(!showMainButtons);
        instructions.setVisible(!showMainButtons);
        highScores.setVisible(!showMainButtons);
        startFamilyEdition.setVisible(showMainButtons);
        startNerdEdition.setVisible(showMainButtons);
        backButton.setVisible(showMainButtons);

        playerThree.setVisible(false);
        playerFour.setVisible(false);
        playerFive.setVisible(false);
        confirmNames.setVisible(false);
        for (JTextField playerName : playerNames) {
            playerName.setVisible(false);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void showPlayerSelection() {
        startFamilyEdition.setVisible(false);
        startNerdEdition.setVisible(false);
        backButton.setVisible(true);

        playerThree.setVisible(true);
        playerFour.setVisible(true);
        playerFive.setVisible(true);

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void configureNameEntry(int numberOfPlayers) {
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i].setVisible(i < numberOfPlayers);
        }
        confirmNames.setVisible(true);

        playerThree.setVisible(false);
        playerFour.setVisible(false);
        playerFive.setVisible(false);

        nameEntryPanel.revalidate();
        nameEntryPanel.repaint();
    }

    private void showGameView(String edition) {
        mainFrame.setVisible(false);
        GameView game = new GameView(this, edition, confirmedPlayerNames);
        game.showGameView();
    }
    
    public void showMainMenu() {
        startFamilyEdition.setVisible(false);
        startNerdEdition.setVisible(false);
        backButton.setVisible(false);
        confirmNames.setVisible(false);
        for (JButton playerButton : new JButton[]{playerThree, playerFour, playerFive}) {
            playerButton.setVisible(false);
        }
        for (JTextField playerName : playerNames) {
            playerName.setVisible(false);
        }

        startGame.setVisible(true);
        instructions.setVisible(true);
        highScores.setVisible(true);

        mainFrame.setVisible(true);
    }
}
