package clash_of_cards.view;

import javax.swing.*;

public class ResultsView extends JFrame {
    public ResultsView() {
        setTitle("Game Results");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel winnerLabel = new JLabel("Winner: Player 1");
        panel.add(winnerLabel);
    }
}
