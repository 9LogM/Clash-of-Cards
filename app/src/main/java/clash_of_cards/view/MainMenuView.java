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
    public JButton pointsMode, roundsMode, backToMainMenu;
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
        pointsMode = new JButton("Points Mode");
        roundsMode = new JButton("Rounds Mode");
        backToMainMenu = new JButton("Back to Main Menu");
        playerThree = new JButton("3 Players");
        playerFour = new JButton("4 Players");
        playerFive = new JButton("5 Players");
        playerNames = new JTextField[5];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = new JTextField(10);
            playerNames[i].setFont(new Font("SansSerif", Font.PLAIN, 18));
            playerNames[i].setHorizontalAlignment(JTextField.CENTER);
            playerNames[i].setPreferredSize(new Dimension(150, 50));
        }
        nameEntryPanel = new JPanel(new FlowLayout());
        for (JTextField playerName : playerNames) {
            nameEntryPanel.add(playerName);
        }

        GUITools.styleButton(startGame);
        GUITools.styleButton(instructions);
        GUITools.styleButton(highScores);
        GUITools.styleButton(startFamilyEdition);
        GUITools.styleButton(startNerdEdition);
        GUITools.styleButton(backButton);
        GUITools.styleButton(confirmNames);
        GUITools.styleButton(pointsMode);
        GUITools.styleButton(roundsMode);
        GUITools.styleButton(backToMainMenu);
        GUITools.styleButton(playerThree);
        GUITools.styleButton(playerFour);
        GUITools.styleButton(playerFive);
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
        buttonPanel.add(startFamilyEdition);
        buttonPanel.add(startNerdEdition);
        buttonPanel.add(backButton);
        buttonPanel.add(confirmNames);
        buttonPanel.add(pointsMode);
        buttonPanel.add(roundsMode);
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