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
        view.confirmNames.addActionListener(e -> confirmNames());
        view.backToMainMenu.addActionListener(e -> showMainMenu());
    }

    private void toggleButtonVisibility() {
        boolean showMainButtons = !view.startGame.isVisible();
        setVisibilityForComponents(new JComponent[]{view.startGame, view.instructions, view.highScores}, showMainButtons);
        setVisibilityForComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition}, !showMainButtons);
        view.backButton.setVisible(true);
        hideNameEntryComponents();
    }

    private void showPlayerSelection() {
        setVisibilityForComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition}, false);
        setVisibilityForComponents(new JComponent[]{view.playerThree, view.playerFour, view.playerFive}, true);
        hideNameEntryComponents();
    }

    private void configureNameEntry(int numberOfPlayers) {
        for (int i = 0; i < view.playerNames.length; i++) {
            view.playerNames[i].setVisible(i < numberOfPlayers);
        }
        view.confirmNames.setVisible(true);
        view.nameEntryPanel.setVisible(true);
        setVisibilityForComponents(new JComponent[]{view.playerThree, view.playerFour, view.playerFive}, false);
        GUITools.updatePanel(view.nameEntryPanel);
    }

    private void confirmNames() {
        view.confirmedPlayerNames.clear();
        for (JTextField playerName : view.playerNames) {
            if (playerName.isVisible() && !playerName.getText().trim().isEmpty()) {
                view.confirmedPlayerNames.add(playerName.getText().trim());
            }
        }
        setVisibilityForComponents(new JComponent[]{view.pointsMode, view.roundsMode, view.backToMainMenu}, true);
        setVisibilityForComponents(new JComponent[]{view.confirmNames, view.playerThree, view.playerFour, view.playerFive}, false);
        GUITools.updatePanel(view.mainPanel);
        hideNameEntryComponents(); // Hide name entry components after confirming names
    }

    public void showMainMenu() {
        setVisibilityForComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition, view.confirmNames, view.pointsMode, view.roundsMode, view.backToMainMenu, view.playerThree, view.playerFour, view.playerFive}, false);
        setVisibilityForComponents(new JComponent[]{view.startGame, view.instructions, view.highScores}, true);
        view.backButton.setVisible(false);
        hideNameEntryComponents();
        view.mainFrame.setVisible(true);
    }

    private void setVisibilityForComponents(JComponent[] components, boolean visible) {
        for (JComponent component : components) {
            component.setVisible(visible);
        }
    }

    private void hideNameEntryComponents() {
        view.nameEntryPanel.setVisible(false);
        for (JTextField playerName : view.playerNames) {
            playerName.setVisible(false);
        }
        view.confirmNames.setVisible(false);
    }
}