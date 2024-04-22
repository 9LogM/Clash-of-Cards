package clash_of_cards.view;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuView {
    public JFrame mainFrame;
    public JPanel mainPanel;
    public JPanel buttonPanel;
    public JPanel nameEntryPanel;
    public JLabel titleLabel;
    public JButton startGame, instructions, highScores;
    public JButton startFamilyEdition, startNerdEdition, backButton, confirmNames;
    public JButton playerThree, playerFour, playerFive;
    public JTextField[] playerNames;
    public List<String> confirmedPlayerNames;

    public MainMenuView() {
        confirmedPlayerNames = new ArrayList<>();
        initializeComponents();
        setupUI();
        setupMainFrame();
    }

    public void initializeComponents() {
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
            GUIUtils.styleTextField(playerNames[i]);
        }
        nameEntryPanel = new JPanel(new FlowLayout());
        for (JTextField playerName : playerNames) {
            nameEntryPanel.add(playerName);
        }

        GUIUtils.styleButton(startGame);
        GUIUtils.styleButton(instructions);
        GUIUtils.styleButton(highScores);
        GUIUtils.styleButton(startFamilyEdition);
        GUIUtils.styleButton(startNerdEdition);
        GUIUtils.styleButton(backButton);
        GUIUtils.styleButton(confirmNames);
        GUIUtils.styleButton(playerThree);
        GUIUtils.styleButton(playerFour);
        GUIUtils.styleButton(playerFive);
    }

    public void setupUI() {
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

    public void setupMainFrame() {
        mainFrame = new JFrame("Clash of Cards - Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
}
