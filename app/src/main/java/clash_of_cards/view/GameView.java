package clash_of_cards.view;

import clash_of_cards.controller.MainMenuController;
import clash_of_cards.model.GameModel;
import clash_of_cards.model.ContentLoader;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel whiteCardsPanel;
    private JPanel blackCardPanel;
    private JLabel roundLabel;
    private GameModel gameModel;
    private MainMenuController mainMenuController;
    private List<String> playerNames;
    private ContentLoader text;

    public GameView(MainMenuController mainMenuController, GameModel gameModel, List<String> playerNames) {
        this.mainMenuController = mainMenuController;
        this.gameModel = gameModel;
        this.playerNames = playerNames;
        this.text = new ContentLoader(gameModel.getEdition());
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
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        scorePanel.setOpaque(false);
        for (String playerName : playerNames) {
            int score = gameModel.getPlayerScore(playerName);
            Runnable viewCardsAction = () -> displayPlayerCards(playerName);
            PlayerPanel playerPanel = new PlayerPanel(playerName, score, viewCardsAction);
            gameModel.addObserver(playerName, playerPanel);
            scorePanel.add(playerPanel);
        }
        mainPanel.add(scorePanel, gbc);
    }

    private void setupBlackCard(GridBagConstraints gbc) {
        blackCardPanel = new JPanel();
        blackCardPanel.setLayout(new BoxLayout(blackCardPanel, BoxLayout.Y_AXIS));
        blackCardPanel.setBackground(new Color(80, 80, 80));
        roundLabel = new JLabel("Round " + gameModel.getCurrentRound());
        roundLabel.setForeground(Color.WHITE);
        roundLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        roundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        blackCardPanel.add(roundLabel);
        blackCardPanel.add(Box.createVerticalStrut(10));
        updateBlackCard();
        mainPanel.add(blackCardPanel, gbc);
    }
    
    private void updateBlackCard() {
        String sentence = text.getRandom("Sentence");
        blackCardPanel.removeAll();
        roundLabel.setText("Round " + gameModel.getCurrentRound());
        blackCardPanel.add(roundLabel);
        blackCardPanel.add(Box.createVerticalStrut(10));
        blackCardPanel.add(BlackCard.createBlackCard(sentence));
        blackCardPanel.revalidate();
        blackCardPanel.repaint();
    }
    
    private void setupWhiteCardsPanel(GridBagConstraints gbc) {
        whiteCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        whiteCardsPanel.setBackground(new Color(80, 80, 80));
        whiteCardsPanel.setOpaque(false);
        mainPanel.add(whiteCardsPanel, gbc);
    }

    private void setupOptionsPanel(GridBagConstraints gbc) {
        Runnable backAction = () -> {
            frame.setVisible(false);
            mainMenuController.showMainMenu();
        };
        Runnable hideCardsAction = this::hidePlayerCards;
        Runnable judgeCardsAction = this::displayStoredCards;

        JPanel optionsPanel = OptionsPanel.createOptionsPanel(backAction, hideCardsAction, judgeCardsAction);
        mainPanel.add(optionsPanel, gbc);
    }

    private void displayPlayerCards(String playerName) {
        whiteCardsPanel.removeAll();
        ButtonGroup group = new ButtonGroup();
        List<String> cards = gameModel.getPlayerCards(playerName);
        for (String card : cards) {
            Runnable onCardSelected = () -> gameModel.storeSelectedCard(playerName, card);
            JToggleButton cardButton = WhiteCard.createWhiteCard(card, onCardSelected);
            group.add(cardButton);
            whiteCardsPanel.add(cardButton);
        }
        GUITools.updatePanel(whiteCardsPanel);
    }

    public void displayStoredCards() {
        whiteCardsPanel.removeAll();
        ButtonGroup group = new ButtonGroup();
        gameModel.getStoredCards().forEach((playerName, card) -> {
            Runnable selectAction = () -> {
                gameModel.endRound(playerName);
                updateRound();
            };
            JToggleButton cardButton = WhiteCard.createWhiteCard(card, selectAction);
            group.add(cardButton);
            whiteCardsPanel.add(cardButton);
        });
        GUITools.updatePanel(whiteCardsPanel);
    }

    private void updateRound() {
        if (gameModel.checkGameEnd()) {
            showEndGame();
        } else {
            updateBlackCard();
            hideJudgeCards();
        }
    }

    private void hideJudgeCards() {
        whiteCardsPanel.removeAll();
        GUITools.updatePanel(whiteCardsPanel);
    }

    private void hidePlayerCards() {
        whiteCardsPanel.removeAll();
        GUITools.updatePanel(whiteCardsPanel);
    }

    public void showGameView() {
        frame.setVisible(true);
    }

    private void showEndGame() {
        String winner = gameModel.getWinner();
        JOptionPane.showMessageDialog(frame, "Game Over! The winner is " + winner + ". Returning to main menu.");
        gameModel.resetGame();
        frame.dispose();
        mainMenuController.showMainMenu();
    }
}
