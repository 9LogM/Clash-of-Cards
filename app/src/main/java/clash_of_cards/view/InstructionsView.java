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
    private JTextArea instructionsText;

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
        buttonPanel.setBackground(new Color(70, 70, 70));
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        instructionsText = new JTextArea("There are two modes, Nerd and Family. The minimum players number are 3, and the max is 5. Every player is dealt with 6 cards, in the first round the first player is the judge, then the judge will be the winner of the previous round, the game ends when the number or max points or rounds are reached.");
        instructionsText.setFont(new Font("Consolas", Font.PLAIN, 40));
        instructionsText.setForeground(new Color(240, 240, 240));
        instructionsText.setBackground(new Color(80, 80, 80));
        instructionsText.setLineWrap(true);
        instructionsText.setWrapStyleWord(true);

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
