package clash_of_cards.view;

import javax.swing.*;

public class MainMenuView extends JFrame {
    public MainMenuView() {
        setTitle("Clash of Cards - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton joinGameButton = new JButton("Join Game");
        panel.add(joinGameButton);

        JButton instructionsButton = new JButton("Instructions");
        panel.add(instructionsButton);
    }
}
