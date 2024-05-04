package clash_of_cards.view;

import clash_of_cards.controller.GameController;
import clash_of_cards.model.GameModel;
import clash_of_cards.util.GUITools;
import clash_of_cards.util.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class GameView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel scorePanel;
    private JPanel whiteCardsPanel;
    private JPanel blackCardPanel;
    private JLabel roundLabel;
    private GameModel gameModel;
    private GameController controller;

    public GameView(GameController controller, GameModel gameModel) {
        this.controller = controller;
        this.gameModel = gameModel;
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame("Clash of Cards - Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(80, 80, 80));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.weightx = 1;

        gbc.gridy = 0;
        setupScorePanel(gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        setupBlackCard(gbc);

        gbc.gridy = 2;
        gbc.weighty = 2;
        setupWhiteCardsPanel(gbc);

        gbc.gridy = 3;
        gbc.weighty = 0;
        setupOptionsPanel(gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setupScorePanel(GridBagConstraints gbc) {
        scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        scorePanel.setOpaque(false);
        gameModel.getPlayerNames().forEach(playerName -> {
            int score = gameModel.getPlayerScore(playerName);
            Runnable viewCardsAction = () -> controller.displayPlayerCards(playerName);
            PlayerPanel playerPanel = new PlayerPanel(playerName, score, viewCardsAction);
            gameModel.addObserver(playerName, playerPanel);
            scorePanel.add(playerPanel);
        });
        mainPanel.add(scorePanel, gbc);
    }

    private void setupBlackCard(GridBagConstraints gbc) {
        blackCardPanel = new JPanel();
        blackCardPanel.setLayout(new BoxLayout(blackCardPanel, BoxLayout.Y_AXIS));
        blackCardPanel.setBackground(new Color(80, 80, 80));
        roundLabel = new JLabel();
        roundLabel.setForeground(Color.WHITE);
        roundLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        roundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        blackCardPanel.add(roundLabel);
        blackCardPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(blackCardPanel, gbc);
    }

    public void updateBlackCard(int round, String sentence) {
        roundLabel.setText("Round " + round);
        blackCardPanel.removeAll();
        blackCardPanel.add(roundLabel);
        blackCardPanel.add(BlackCard.createBlackCard(sentence));
        blackCardPanel.revalidate();
        blackCardPanel.repaint();
    }

    public void updateJudge(String newJudge) {
        Component[] components = scorePanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof PlayerPanel) {
                PlayerPanel panel = (PlayerPanel) comp;
                panel.setJudge(panel.getPlayerName().equals(newJudge));
            }
        }
    }    

    private void setupWhiteCardsPanel(GridBagConstraints gbc) {
        whiteCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        whiteCardsPanel.setBackground(new Color(80, 80, 80));
        whiteCardsPanel.setOpaque(false);
        mainPanel.add(whiteCardsPanel, gbc);
    }

    private void setupOptionsPanel(GridBagConstraints gbc) {
        JPanel optionsPanel = OptionsPanel.createOptionsPanel(
            saveAndExit(),
            this::clearWhiteCardsPanel,
            controller::displayStoredCards
        );
        mainPanel.add(optionsPanel, gbc);
    }

    public Runnable saveAndExit() {
        Runnable saveExit = () -> {
            if (gameModel != null) {
                GameUtils.saveGame(gameModel);
                JOptionPane.showMessageDialog(frame, "Game saved successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "No game in progress to save.");
            }
            frame.setVisible(false);
            controller.showMainMenu();
        };

        return saveExit;
    }

    public void displayPlayerCards(List<String> cards, String playerName, BiConsumer<String, String> cardSelectedAction) {
        clearWhiteCardsPanel();
        ButtonGroup group = new ButtonGroup();
        cards.forEach(card -> {
            JToggleButton cardButton = WhiteCard.createWhiteCard(card, () -> cardSelectedAction.accept(playerName, card));
            group.add(cardButton);
            whiteCardsPanel.add(cardButton);
        });
        GUITools.updatePanel(whiteCardsPanel);
    }

    public void displayStoredCards(Map<String, String> storedCards, BiConsumer<String, String> selectAction) {
        clearWhiteCardsPanel();
        ButtonGroup group = new ButtonGroup();
        List<Map.Entry<String, String>> entries = new ArrayList<>(storedCards.entrySet());
        Collections.shuffle(entries);
        entries.forEach(entry -> {
            String playerName = entry.getKey();
            String card = entry.getValue();
            JToggleButton cardButton = WhiteCard.createWhiteCard(card, () -> selectAction.accept(playerName, card));
            group.add(cardButton);
            whiteCardsPanel.add(cardButton);
        });
        GUITools.updatePanel(whiteCardsPanel);
    }

    public void clearWhiteCardsPanel() {
        whiteCardsPanel.removeAll();
        GUITools.updatePanel(whiteCardsPanel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }
}
