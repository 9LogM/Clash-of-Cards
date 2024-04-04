package clash_of_cards.view;

import javax.swing.*;

public class LobbyView extends JFrame {
    public LobbyView() {
        setTitle("Game Lobby");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel waitingLabel = new JLabel("Waiting for players...");
        panel.add(waitingLabel);

        JButton startGameButton = new JButton("Start Game");
        panel.add(startGameButton);
    }
}
