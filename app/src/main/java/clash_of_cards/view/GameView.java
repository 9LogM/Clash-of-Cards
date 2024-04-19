package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import clash_of_cards.model.*;

public class GameView {
    private String edition;
    private JFrame frame;
    private JPanel mainPanel;
    private MainMenuView mainMenuView;
    private HashMap<String, Player> playerCards;
    private HashMap<String, String> storedCards;
    private JPanel whiteCardsPanel;
    private List<String> playerNames;

    public GameView(MainMenuView mainMenu, String edition, List<String> playerNames) {
        this.mainMenuView = mainMenu;
        this.edition = edition;
        this.playerNames = playerNames;
        this.playerCards = new HashMap<>();
        this.storedCards = new HashMap<>();
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
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        setupScorePanel(gbc);

        gbc.gridy = 1;
        setupBlackCard(gbc);

        gbc.gridy = 2;
        gbc.weighty = 2;
        setupWhiteCardsPanel(gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.0;
        setupOptionsPanel(gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setupScorePanel(GridBagConstraints gbc) {
        JPanel scorePanel = ScorePanel.createScorePanel(playerNames, this::PlayerPanel);
        mainPanel.add(scorePanel, gbc);
    }

    private void setupBlackCard(GridBagConstraints gbc) {
        Sentences sentences = new Sentences(edition);
        String sentence = sentences.getRandomSentence();
        JPanel blackCardPanel = BlackCardPanel.createBlackCard(sentence);
        mainPanel.add(blackCardPanel, gbc);
    }

    private void setupWhiteCardsPanel(GridBagConstraints gbc) {
        whiteCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        whiteCardsPanel.setBackground(new Color(80, 80, 80));
        whiteCardsPanel.setOpaque(false);
        mainPanel.add(whiteCardsPanel, gbc);
    }

    private void setupOptionsPanel(GridBagConstraints gbc) {
        Runnable backAction = () -> GUIUtils.changeView(frame, false, mainMenuView);
        Runnable hideCardsAction = () -> {
            whiteCardsPanel.removeAll();
            GUIUtils.updatePanel(whiteCardsPanel);
        };
        Runnable judgeCardsAction = this::displayStoredCards;

        JPanel optionsPanel = OptionsPanel.createOptionsPanel(backAction, hideCardsAction, judgeCardsAction);
        mainPanel.add(optionsPanel, gbc);
    }

    private void assignCardsToPlayer(String playerName) {
        Player player = new Player();
        Answers answers = new Answers(edition);
        for (int i = 0; i < 6; i++) {
            player.addCard(answers.getRandomAnswer());
        }
        playerCards.put(playerName, player);
    }

    private void displayPlayerCards(String playerName) {
        whiteCardsPanel.removeAll();
        ButtonGroup group = new ButtonGroup();
        Player player = playerCards.get(playerName);
        List<String> cards = player.getCards();
        for (String card : cards) {
            Runnable onCardSelected = () -> storedCards.put(playerName, card);
            JToggleButton cardButton = WhiteCardPanel.createWhiteCard(card, onCardSelected);
            group.add(cardButton);
            whiteCardsPanel.add(cardButton);
        }
        GUIUtils.updatePanel(whiteCardsPanel);
    }

    private JPanel PlayerPanel(String playerName) {
        if (!playerCards.containsKey(playerName)) {
            assignCardsToPlayer(playerName);
        }
        Player player = playerCards.get(playerName);
        Runnable viewCardsAction = () -> displayPlayerCards(playerName);
        return PlayerPanel.createPlayerPanel(playerName, player.getScore(), viewCardsAction);
    }

    private void displayStoredCards() {
        whiteCardsPanel.removeAll();
        for (String card : storedCards.values()) {
            Runnable noAction = () -> {};
            JToggleButton cardButton = WhiteCardPanel.createWhiteCard(card, noAction);
            whiteCardsPanel.add(cardButton);
        }
        GUIUtils.updatePanel(whiteCardsPanel);
    }

    public void showGameView() {
        frame.setVisible(true);
    }
}
