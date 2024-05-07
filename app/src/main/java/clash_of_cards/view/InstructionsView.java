package clash_of_cards.view;

import clash_of_cards.util.GUITools;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InstructionsView {
    private JFrame frame;
    private JPanel panel;
    private JPanel buttonPanel;
    private JButton backButton;
    private JLabel instructionsLabel;
    private JTextPane instructionsText;

    public InstructionsView(ActionListener backListener) {
        initializeComponents(backListener);
        setupUI();
    }

    private void initializeComponents(ActionListener backListener) {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(80, 80, 80));
        panel.setBorder(BorderFactory.createEmptyBorder());
    
        instructionsLabel = new JLabel("Game Instructions", SwingConstants.CENTER);
        instructionsLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        instructionsLabel.setFont(new Font("Consolas", Font.BOLD, 60));
        instructionsLabel.setForeground(new Color(240, 240, 240));
        panel.add(instructionsLabel, BorderLayout.NORTH);
    
        backButton = new JButton("Back to Main Menu");
        GUITools.styleButton(backButton);
        backButton.addActionListener(backListener);
    
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(80, 80, 80));
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        instructionsText = new JTextPane();
        instructionsText.setContentType("text/html");
        instructionsText.setText(
            "<html>" +
            "<body style='font-family:Consolas; font-size:16pt; color:#f0f0f0; text-align:center; background-color:#333;'>" +
                "<h2>General Rules</h2>" +
                "<br>Number of Players: 3 to 5</br>" +
                "<br>Initial Cards: Each player begins with 6 white cards, each containing a different phrase.</br>" +
                "<h2>Round Progression</h2>" +
                "<br>1. Judge Role: The judge draws a black card that features a question or a fill-in-the-blank statement.</br>" +
                "<br>2. Player Submissions: Players other than the judge select one of their white cards that best completes the statement or answers the question in the most outrageous way.</br>" +
                "<br>3. Judgement Phase: All submissions are displayed anonymously. The judge reads them aloud and selects the most amusing response.</br>" +
                "<br>4. Scoring: The player whose card is chosen earns a point and takes on the role of the judge for the next round.</br>" +
                "<h2>Additional Rules</h2>" +
                "<br>Card Refresh: At the end of each round, players draw one new white card to maintain a hand of six.</br>" +
                "<h2>End Game Conditions</h2>" +
                "<br>The game concludes when any of the following conditions are met:</br>" +
                "<br>1. All predetermined rounds are completed.<br>" +
                "2. A player reaches the target score.<br>" +
                "3. All black cards have been used." +
                "<h2>Game Settings</h2>" +
                "<br>Editions: Choice between 'Family Edition' and 'Nerd Edition.'</br>" +
                "<br>Customization: Players set the number of rounds and target score at the start of the game.</br>" +
                "<h2>Device Handling</h2>" +
                "<br>The device is passed among players for card selection, ensuring choices remain private.</br>" +
                "<br>During the judgement phase, the device is placed in a central location where all players can view submissions without revealing their identities.</br>" +
                "<h2>Saving Progress</h2>" +
                "<br>Save and Exit: Players can save the game state at any time, which includes player names, their current cards, and scores.</br>" +
                "<br>Continuation: Upon restarting the game, players are prompted to either continue the saved game or start a new game.</br>" +
            "</body>" +
            "</html>"
        );
        instructionsText.setEditable(false);
        instructionsText.setBackground(new Color(80, 80, 80));
        JScrollPane scrollPane = new JScrollPane(instructionsText);
        scrollPane.getViewport().setBackground(new Color(80, 80, 80));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupUI() {
        frame = new JFrame("Clash of Cards - Instructions");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(panel);
        frame.setVisible(false);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.dispose();
    }
}
