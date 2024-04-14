package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;
import clash_of_cards.text_corpus.*;

public class WhiteCards {
    
    Dimension screenSize;
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel wordLabel;
    private int numWhiteCards = 6;
    private Color borderColorWhite = new Color(140, 140, 140);

    Words word = new Words();
    public static void main(String[] args) {
        new WhiteCards();
    }

    public WhiteCards() {
      frame = new JFrame("Clash of Cards");
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mainPanel =new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 200));
      mainPanel.setBackground(new Color(80, 80, 80));
      
      playerView();

      frame.add(mainPanel);
      frame.setVisible(true);
    }

    private void playerView() {
        for(int i = 0; i < numWhiteCards; i++) {
            RoundJPanel cardPanel = new RoundJPanel(170, 250, borderColorWhite); 
            cardPanel.setBackground(new Color(240, 240, 240));
            JLabel wordLabel = new JLabel(word.getRandomWord());
            wordLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
            wordLabel.setForeground(new Color(40, 40, 40)); 
            wordLabel.setBounds(8, 60, 120, 100); 
            wordLabel.setHorizontalAlignment(JLabel.CENTER); 
            wordLabel.setVerticalAlignment(JLabel.CENTER);
            wordLabel.setHorizontalTextPosition(JLabel.CENTER);
            wordLabel.setVerticalTextPosition(JLabel.CENTER);
            wordLabel.setOpaque(false);

            cardPanel.add(wordLabel);
            mainPanel.add(cardPanel);
        }
    }
}
