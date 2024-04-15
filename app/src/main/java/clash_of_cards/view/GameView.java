package clash_of_cards.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import clash_of_cards.text_corpus.Answers;
import clash_of_cards.text_corpus.Sentences;
import clash_of_cards.utils.ButtonUtils;

public class GameView {
    private String edition;
    Dimension screenSize;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton backButton;
    private MainMenuView mainMenuView;
    private JButton viewCardsButton;
    private String judge = new String("Kate");

    public GameView(MainMenuView mainMenu, String edition) {
        this.mainMenuView = mainMenu;
        this.edition = edition;
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame("Clash of Cards - Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, screenSize.width, screenSize.height);
        mainPanel.setBackground(new Color(80, 80, 80));

        backButton = new JButton("Back to Main Menu");
        ButtonUtils.customButton(backButton);
        backButton.setBounds(50, 50, 250, 50);
        backButton.addActionListener(e -> {
            frame.setVisible(false);
            mainMenuView.showMainMenu();
        });
        mainPanel.add(backButton);

        frame.add(mainPanel);
        addScorePanel();
        blackCard();
    }

    private void addScorePanel() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 35, 10));
        scorePanel.setOpaque(false);
        scorePanel.add(createPlayerPanel("Mohamed G", "15"));
        scorePanel.add(createPlayerPanel("Mohamed H", "15"));
        scorePanel.add(createPlayerPanel("Wallash", "15"));
        scorePanel.add(createPlayerPanel("Kate", "15"));

        int panelWidth = 600;
        int panelHeight = 200;
        scorePanel.setBounds((screenSize.width - panelWidth) / 2, 30, panelWidth, panelHeight);
        mainPanel.add(scorePanel);
    }

    private JPanel createPlayerPanel(String playerName, String score) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(playerName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel(score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if(!playerName.equals(judge)) {
            viewCardsButton = new JButton("View Cards");
        } else {
            viewCardsButton = new JButton("Judge");
            viewCardsButton.setEnabled(false);
        }

        viewCardsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCardsButton.setForeground(new Color(240, 240, 240));
        viewCardsButton.setBackground(new Color(50, 50, 50));
        viewCardsButton.setBorderPainted(false);
        viewCardsButton.setFocusPainted(false);
        viewCardsButton.setFont(new Font("Consolas", Font.BOLD, 16));

        viewCardsButton.addActionListener(e -> showWhiteCards(playerName));

        playerPanel.add(nameLabel);
        playerPanel.add(scoreLabel);
        playerPanel.add(viewCardsButton);

        return playerPanel;
    }

    public void whiteCards() {
        int cardWidth = 150;
        int cardHeight = 180;
        int increment = 170;
        int totalWidth = 6 * cardWidth + (6 - 1) * (increment - cardWidth);

        int x = (screenSize.width - totalWidth) / 2;
        int y = screenSize.height - 280;

        Answers answers = new Answers(edition);

        for (int i = 0; i < 6; i++) {
            RoundJPanel cardPanel = new RoundJPanel(cardWidth, cardHeight, 50, 70, new Color(140, 140, 140));
            cardPanel.setLocation(x, y);
            cardPanel.setBackground(new Color(240, 240, 240));
            cardPanel.setLayout(new BorderLayout());

            JTextArea answerTextArea = new JTextArea(answers.getRandomAnswer());
            answerTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
            answerTextArea.setForeground(Color.BLACK);
            answerTextArea.setWrapStyleWord(true);
            answerTextArea.setLineWrap(true);
            answerTextArea.setEditable(false);
            answerTextArea.setOpaque(false);
            answerTextArea.setMargin(new Insets(10, 10, 10, 10));

            cardPanel.add(answerTextArea, BorderLayout.CENTER);
            mainPanel.add(cardPanel);

            x += increment;
        }
    }

    public void blackCard() {
        int cardWidth = 400;
        int cardHeight = 200;
        int x = (screenSize.width - cardWidth) / 2;
        int y = (screenSize.height - cardHeight) / 2;

        RoundJPanel cardPanel = new RoundJPanel(cardWidth, cardHeight, 50, 70, new Color(140, 140, 140));
        cardPanel.setLocation(x, y);
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
        mainPanel.add(cardPanel);

        frame.revalidate();
        frame.repaint();
    }

    public void showWhiteCards(String playerName) {
        frame.setVisible(false);
        new WhiteCards(edition, playerName);
    }

    public void showGameView() {
        frame.setVisible(true);
    }
}
