package clash_of_cards.view;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel {
    private static final Color BACKGROUND_COLOR = new Color(70, 70, 70);

    public static JPanel createOptionsPanel(Runnable backAction, Runnable hideCardsAction, Runnable judgeCardsAction) {
        JButton backButton = new JButton("Back to Main Menu");
        GUITools.styleButton(backButton);
        backButton.addActionListener(e -> backAction.run());

        JButton hideCardsButton = new JButton("Hide Cards");
        GUITools.styleButton(hideCardsButton);
        hideCardsButton.addActionListener(e -> hideCardsAction.run());

        JButton judgeCardsButton = new JButton("Judge Cards");
        GUITools.styleButton(judgeCardsButton);
        judgeCardsButton.addActionListener(e -> judgeCardsAction.run());

        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        optionsPanel.setBackground(BACKGROUND_COLOR);
        optionsPanel.add(backButton);
        optionsPanel.add(hideCardsButton);
        optionsPanel.add(judgeCardsButton);

        return optionsPanel;
    }
}
