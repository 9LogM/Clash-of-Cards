package clash_of_cards.view;

import clash_of_cards.util.GUITools;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuView {
    public JFrame mainFrame;
    public JPanel mainPanel;
    public JPanel buttonPanel;
    public JPanel nameEntryPanel;
    public JLabel titleLabel, targetScoreLabel, numberOfRoundsLabel;
    public JButton continueGame, newGame, instructions, highScores, startFamilyEdition, startNerdEdition, pointsMode, roundsMode;
    public JButton playerThree, playerFour, playerFive, confirmNames, backButton, startGame;
    public JTextField targetScoreField, numberOfRoundsField;
    public JTextField[] playerNames;
    public List<String> confirmedPlayerNames;

    public MainMenuView() {
        confirmedPlayerNames = new ArrayList<>();
        initializeComponents();
        setupUI();
        setupMainFrame();
    }

    public void initializeComponents() {
        continueGame = new JButton("Continue");
        newGame = new JButton("New Game");
        instructions = new JButton("Instructions");
        highScores = new JButton("High Scores");
        startFamilyEdition = new JButton("Start Family Edition");
        startNerdEdition = new JButton("Start Nerd Edition");
        backButton = new JButton("Back to Main Menu");
        confirmNames = new JButton("Confirm Names");
        pointsMode = new JButton("Points Mode");
        roundsMode = new JButton("Rounds Mode");
        startGame = new JButton("Start Game");
        playerThree = new JButton("3 Players");
        playerFour = new JButton("4 Players");
        playerFive = new JButton("5 Players");

        targetScoreLabel = new JLabel("Target Score:");
        targetScoreLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        targetScoreLabel.setForeground(new Color(240, 240, 240));
        targetScoreField = new JTextField(10);
        numberOfRoundsLabel = new JLabel("Number of Rounds:");
        numberOfRoundsLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        numberOfRoundsLabel.setForeground(new Color(240, 240, 240));
        numberOfRoundsField = new JTextField(10);

        playerNames = new JTextField[5];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = new JTextField(10);
            GUITools.styleField(playerNames[i]);
        }
        nameEntryPanel = new JPanel(new FlowLayout());
        for (JTextField playerName : playerNames) {
            nameEntryPanel.add(playerName);
        }

        GUITools.styleField(targetScoreField);
        GUITools.styleField(numberOfRoundsField);
        GUITools.styleButton(continueGame);
        GUITools.styleButton(newGame);
        GUITools.styleButton(instructions);
        GUITools.styleButton(highScores);
        GUITools.styleButton(startFamilyEdition);
        GUITools.styleButton(startNerdEdition);
        GUITools.styleButton(backButton);
        GUITools.styleButton(confirmNames);
        GUITools.styleButton(pointsMode);
        GUITools.styleButton(roundsMode);
        GUITools.styleButton(startGame);
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
        buttonPanel.add(continueGame);
        buttonPanel.add(newGame);
        buttonPanel.add(backButton);
        buttonPanel.add(instructions);
        buttonPanel.add(highScores);
        buttonPanel.add(startFamilyEdition);
        buttonPanel.add(startNerdEdition);
        buttonPanel.add(confirmNames);
        buttonPanel.add(pointsMode);
        buttonPanel.add(roundsMode);
        buttonPanel.add(targetScoreLabel);
        buttonPanel.add(targetScoreField);
        buttonPanel.add(numberOfRoundsLabel);
        buttonPanel.add(numberOfRoundsField);
        buttonPanel.add(playerThree);
        buttonPanel.add(playerFour);
        buttonPanel.add(playerFive);
        buttonPanel.add(startGame);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(titleLabel, gbc);
        
        nameEntryPanel.setBackground(new Color(70, 70, 70));
        gbc.gridy = 1;
        gbc.weighty = 0;
        mainPanel.add(nameEntryPanel, gbc);

        gbc.gridy = 2;
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