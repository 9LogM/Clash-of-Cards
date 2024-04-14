package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class Table {

    Dimension screenSize;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton backButton;
    private MainMenuView mainMenuView;
    private int numWhiteCards = 4;
    private Color borderColorWhite = new Color(140, 140, 140);

    public Table(MainMenuView mainMenu) {
        this.mainMenuView = mainMenu;
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
        backButton.setBounds(50, screenSize.height - 150, 250, 50);
        backButton.addActionListener(e -> {
        frame.setVisible(false);
        mainMenuView.showMainMenu();
        });
        mainPanel.add(backButton);

        frame.add(mainPanel);
        addScorePanel();
        blackCard();
        whiteCards();
    }

    private void addScorePanel() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        scorePanel.setOpaque(false);
        scorePanel.add(createPlayerPanel("Mohamed G", "15"));
        scorePanel.add(createPlayerPanel("Mohamed H", "15"));
        scorePanel.add(createPlayerPanel("Wallash", "15"));

        int panelWidth = 500;
        int panelHeight = 100;
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

        playerPanel.add(nameLabel);
        playerPanel.add(scoreLabel);

        return playerPanel;
    }

    public void whiteCards() {
        
        int x = 0;
        int y = 150;
        int increment = 20;

        for(int i = 0; i <= numWhiteCards; i++) {
            RoundJPanel cardPanel = new RoundJPanel(200, 100, 50, 70, borderColorWhite);
            cardPanel.setLocation(x, y += increment);
            cardPanel.setBackground(new Color(240, 240, 240));
            mainPanel.add(cardPanel);
        }

        x = screenSize.width - 213;
        y = 150;
        increment = 20;

        for(int i = 0; i <= numWhiteCards; i++) {
            RoundJPanel cardPanel = new RoundJPanel(200, 100, 50, 70, borderColorWhite); 
            cardPanel.setLocation(x, y += increment);
            cardPanel.setBackground(new Color(240, 240, 240));
            mainPanel.add(cardPanel);
        }

        x = screenSize.width/2 - 50 + numWhiteCards*5;
        y = screenSize.height - 280;

        for(int i = 0; i <= numWhiteCards; i++) {
            RoundJPanel cardPanel = new RoundJPanel(100, 200, 50, 70, borderColorWhite); 
            cardPanel.setLocation(x -=increment, y);
            cardPanel.setBackground(new Color(240, 240, 240));
            mainPanel.add(cardPanel);
        }
    }

    public void blackCard() {
        int x = (int) (screenSize.width / 2.5);
        int y = (int) (screenSize.height / 6);
    
        RoundJPanel cardPanel = new RoundJPanel(200, 300, 50, 70, borderColorWhite); 
        cardPanel.setLocation(x, y); 
        cardPanel.setBackground(new Color(40, 40, 40));
        cardPanel.setLayout(null); 
    
        JLabel sentence = new JLabel("<html>Kids, Dad is trying something new this week.<br>It is called _____.</html>");
        sentence.setFont(new Font("Consolas", Font.PLAIN, 20));
        sentence.setForeground(new Color(240, 240, 240)); 
        sentence.setBounds(10, 50, 180, 200); 
        sentence.setHorizontalAlignment(JLabel.CENTER); 
        sentence.setVerticalAlignment(JLabel.CENTER);
        sentence.setHorizontalTextPosition(JLabel.CENTER);
        sentence.setVerticalTextPosition(JLabel.CENTER);
        sentence.setOpaque(false);
    
        cardPanel.add(sentence);
        mainPanel.add(cardPanel);
    
        frame.revalidate();
        frame.repaint();
    }
    
    public void showTable() {
        frame.setVisible(true);
    }
}


