package clash_of_cards.controller;

import clash_of_cards.view.MainMenuView;
import clash_of_cards.view.GameView;
import clash_of_cards.view.GUITools;
import javax.swing.*;

public class MainMenuController {
    private MainMenuView view;

    public MainMenuController(MainMenuView view) {
        this.view = view;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        view.startGame.addActionListener(e -> toggleButtonVisibility());
        view.startFamilyEdition.addActionListener(e -> showPlayerSelection());
        view.startNerdEdition.addActionListener(e -> showPlayerSelection());
        view.backButton.addActionListener(e -> showMainMenu());
        view.playerThree.addActionListener(e -> configureNameEntry(3));
        view.playerFour.addActionListener(e -> configureNameEntry(4));
        view.playerFive.addActionListener(e -> configureNameEntry(5));
        view.confirmNames.addActionListener(e -> {
            view.confirmedPlayerNames.clear();
            for (JTextField playerName : view.playerNames) {
                if (playerName.isVisible() && !playerName.getText().trim().isEmpty()) {
                    view.confirmedPlayerNames.add(playerName.getText().trim());
                }
            }
            showGameView(view.startFamilyEdition.isVisible() ? "Family" : "Nerd");
        });
    }

    private void toggleButtonVisibility() {
        boolean showMainButtons = view.startGame.isVisible();

        view.startGame.setVisible(!showMainButtons);
        view.instructions.setVisible(!showMainButtons);
        view.highScores.setVisible(!showMainButtons);
        view.startFamilyEdition.setVisible(showMainButtons);
        view.startNerdEdition.setVisible(showMainButtons);
        view.backButton.setVisible(showMainButtons);

        view.playerThree.setVisible(false);
        view.playerFour.setVisible(false);
        view.playerFive.setVisible(false);
        view.confirmNames.setVisible(false);
        for (JTextField playerName : view.playerNames) {
            playerName.setVisible(false);
        }

        GUITools.updatePanel(view.buttonPanel);
    }

    private void showPlayerSelection() {
        view.startFamilyEdition.setVisible(false);
        view.startNerdEdition.setVisible(false);
        view.backButton.setVisible(true);

        view.playerThree.setVisible(true);
        view.playerFour.setVisible(true);
        view.playerFive.setVisible(true);

        GUITools.updatePanel(view.buttonPanel);
    }

    private void configureNameEntry(int numberOfPlayers) {
        for (int i = 0; i < view.playerNames.length; i++) {
            view.playerNames[i].setVisible(i < numberOfPlayers);
        }
        view.confirmNames.setVisible(true);

        view.playerThree.setVisible(false);
        view.playerFour.setVisible(false);
        view.playerFive.setVisible(false);

        GUITools.updatePanel(view.nameEntryPanel);
    }

    private void showGameView(String edition) {
        view.mainFrame.setVisible(false);
        GameView game = new GameView(this, edition, view.confirmedPlayerNames);
        game.showGameView();
    }

    public void showMainMenu() {
        view.startFamilyEdition.setVisible(false);
        view.startNerdEdition.setVisible(false);
        view.backButton.setVisible(false);
        view.confirmNames.setVisible(false);
        for (JButton playerButton : new JButton[]{view.playerThree, view.playerFour, view.playerFive}) {
            playerButton.setVisible(false);
        }
        for (JTextField playerName : view.playerNames) {
            playerName.setVisible(false);
        }

        view.startGame.setVisible(true);
        view.instructions.setVisible(true);
        view.highScores.setVisible(true);

        view.mainFrame.setVisible(true);
    }
}
