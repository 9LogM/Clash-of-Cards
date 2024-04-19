package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

import clash_of_cards.model.*;

public class GameView {
    private String edition;
    Dimension screenSize;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton backButton;
    private MainMenuView mainMenuView;
    private HashMap<String, Player> playerCards = new HashMap<>();
    private HashMap<String, String> storedCards = new HashMap<>();
    private JPanel whiteCardsPanel;
    private List<String> playerNames;

    public GameView(MainMenuView mainMenu, String edition, List<String> playerNames) {
        this.mainMenuView = mainMenu;
        this.edition = edition;
        this.playerNames = playerNames;
        playerCards = new HashMap<>();
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame("Clash of Cards - Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(80, 80, 80));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        addScorePanel(gbc);

        gbc.gridy = 1;
        blackCard(gbc);

        gbc.gridy = 2;
        gbc.weighty = 2;
        setupWhiteCardsPanel(gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.0;
        addOptions(gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private void setupWhiteCardsPanel(GridBagConstraints gbc) {
        whiteCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        whiteCardsPanel.setBackground(new Color(80, 80, 80));
        whiteCardsPanel.setOpaque(false);
        mainPanel.add(whiteCardsPanel, gbc);
    }

    private void displayPlayerCards(String playerName) {
        whiteCardsPanel.removeAll();
        ButtonGroup group = new ButtonGroup();
        Player player = playerCards.get(playerName);
        List<String> cards = player.getCards();
        for (String card : cards) {
            JToggleButton toggleButton = new JToggleButton(card);
            toggleButton.addActionListener(e -> {
                if (toggleButton.isSelected()) {
                    storedCards.put(playerName, card);
                }
            });
            toggleButton.setPreferredSize(new Dimension(200, 150));
            group.add(toggleButton);
            whiteCardsPanel.add(toggleButton);
        }
        whiteCardsPanel.revalidate();
        whiteCardsPanel.repaint();
    }
    
    private JPanel createPlayerPanel(String playerName) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setOpaque(false);
    
        Player player = playerCards.get(playerName);
    
        JLabel nameLabel = new JLabel(playerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel scoreLabel = new JLabel(String.valueOf(player.getScore()));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JButton viewCardsButton = new JButton("View Cards");
        viewCardsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCardsButton.setForeground(new Color(240, 240, 240));
        viewCardsButton.setBackground(new Color(50, 50, 50));
        viewCardsButton.setBorderPainted(false);
        viewCardsButton.setFocusPainted(false);
        viewCardsButton.setFont(new Font("Consolas", Font.BOLD, 16));
        viewCardsButton.addActionListener(e -> displayPlayerCards(playerName));
    
        playerPanel.add(nameLabel);
        playerPanel.add(scoreLabel);
        playerPanel.add(viewCardsButton);
    
        return playerPanel;
    }
    
    private void assignCardsToPlayer(String playerName) {
        Player player = new Player();
        Answers answers = new Answers(edition);
        for (int i = 0; i < 6; i++) {
            player.addCard(answers.getRandomAnswer());
        }
        playerCards.put(playerName, player);
    }

    private JComponent createCard(String text) {
        JTextArea cardArea = new JTextArea(text);
        cardArea.setEditable(false);
        cardArea.setWrapStyleWord(true);
        cardArea.setLineWrap(true);
        cardArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        cardArea.setOpaque(false);
        cardArea.setForeground(Color.BLACK);
        cardArea.setBackground(new Color(255, 255, 255));
        JScrollPane scrollPane = new JScrollPane(cardArea);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        return scrollPane;
    }

    private void addOptions(GridBagConstraints gbc) {
        backButton = new JButton("Back to Main Menu");
        JButton hideCardsButton = new JButton("Hide Cards");
        JButton judgeCardsButton = new JButton("Judge Cards");
    
        ButtonUtils.customButton(backButton);
        backButton.addActionListener(e -> {
            frame.setVisible(false);
            mainMenuView.showMainMenu();
        });
    
        ButtonUtils.customButton(hideCardsButton);
        hideCardsButton.addActionListener(e -> {
            whiteCardsPanel.removeAll();
            whiteCardsPanel.revalidate();
            whiteCardsPanel.repaint();
        });
    
        ButtonUtils.customButton(judgeCardsButton);
        judgeCardsButton.addActionListener(e -> displayStoredCards());
    
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(70, 70, 70));
        bottomPanel.add(backButton);
        bottomPanel.add(hideCardsButton);
        bottomPanel.add(judgeCardsButton);
    
        mainPanel.add(bottomPanel, gbc);
    }

    private void displayStoredCards() {
        whiteCardsPanel.removeAll();
        for (String card : storedCards.values()) {
            whiteCardsPanel.add(createCard(card));
        }
        whiteCardsPanel.revalidate();
        whiteCardsPanel.repaint();
    }

    private void addScorePanel(GridBagConstraints gbc) {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 35, 10));
        scorePanel.setOpaque(false);
        for (String playerName : playerNames) {
            if (!playerCards.containsKey(playerName)) {
                assignCardsToPlayer(playerName);
            }
            scorePanel.add(createPlayerPanel(playerName));
        }
        
        mainPanel.add(scorePanel, gbc);
    }
    
    private void blackCard(GridBagConstraints gbc) {
        int cardWidth = 400;
        int cardHeight = 200;

        JPanel cardContainer = new JPanel();
        cardContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        cardContainer.setOpaque(false);

        RoundJPanel cardPanel = new RoundJPanel(cardWidth, cardHeight, 50, 70, new Color(140, 140, 140));
        cardPanel.setBackground(new Color(40, 40, 40));
        cardPanel.setLayout(new BorderLayout());

        Sentences sentence = new Sentences(edition);
        JTextArea sentenceTextArea = new JTextArea(sentence.getRandomSentence());
        sentenceTextArea.setFont(new Font("Consolas", Font.PLAIN, 20));
        sentenceTextArea.setForeground(new Color(240, 240, 240));
        sentenceTextArea.setWrapStyleWord(true);
        sentenceTextArea.setLineWrap(true);
        sentenceTextArea.setEditable(false);
        sentenceTextArea.setOpaque(false);
        sentenceTextArea.setMargin(new Insets(20, 20, 20, 20));

        cardPanel.add(sentenceTextArea, BorderLayout.CENTER);
        cardContainer.add(cardPanel);
        mainPanel.add(cardContainer, gbc);
    }

    public void showGameView() {
        frame.setVisible(true);
    }
}
