package clash_of_cards.view;

import javax.swing.*;

public class GameView extends JFrame {
    public GameView() {
        setTitle("Clash of Cards - Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        JLabel questionCardLabel = new JLabel("Question Card");
        panel.add(questionCardLabel);
    }
}
