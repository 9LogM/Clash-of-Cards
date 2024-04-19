package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

public class ScorePanel {
    private static final int SPACING = 35;

    public static JPanel createScorePanel(List<String> playerNames, Function<String, JPanel> createPlayerPanel) {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, SPACING, 10));
        scorePanel.setOpaque(false);
        for (String playerName : playerNames) {
            scorePanel.add(createPlayerPanel.apply(playerName));
        }
        return scorePanel;
    }
}
