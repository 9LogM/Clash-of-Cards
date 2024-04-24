package clash_of_cards.controller;

import clash_of_cards.model.GameModel;
import clash_of_cards.view.GameView;
import clash_of_cards.view.MainMenuView;
import clash_of_cards.view.GUITools;
import javax.swing.*;

public class MainMenuController {
    private MainMenuView view;
    private GameModel gameModel;

    public MainMenuController(MainMenuView view) {
        this.view = view;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        view.play.addActionListener(e -> toggleButtonVisibility());
        view.startFamilyEdition.addActionListener(e -> showPlayerSelection());
        view.startNerdEdition.addActionListener(e -> showPlayerSelection());
        view.backButton.addActionListener(e -> showMainMenu());
        view.playerThree.addActionListener(e -> configureNameEntry(3));
        view.playerFour.addActionListener(e -> configureNameEntry(4));
        view.playerFive.addActionListener(e -> configureNameEntry(5));
        view.confirmNames.addActionListener(e -> confirmNames());
        view.pointsMode.addActionListener(e -> setupPointsMode());
        view.roundsMode.addActionListener(e -> setupRoundsMode());
        view.startGame.addActionListener(e -> startGame());
    }

    private void setupPointsMode() {
        view.numberOfRoundsLabel.setVisible(false);
        view.numberOfRoundsField.setVisible(false);
        view.targetScoreLabel.setVisible(true);
        view.targetScoreField.setVisible(true);
        view.startGame.setVisible(true);
        view.pointsMode.setVisible(false);
        view.roundsMode.setVisible(false);
        GUITools.updatePanel(view.mainPanel);
    }
    
    private void setupRoundsMode() {
        view.targetScoreLabel.setVisible(false);
        view.targetScoreField.setVisible(false);
        view.numberOfRoundsLabel.setVisible(true);
        view.numberOfRoundsField.setVisible(true);
        view.startGame.setVisible(true);
        view.pointsMode.setVisible(false);
        view.roundsMode.setVisible(false);
        GUITools.updatePanel(view.mainPanel);
    }

    private void startGame() {
        if (gameModel == null) {
            String edition = view.startFamilyEdition.isSelected() ? "Family" : "Nerd";
            gameModel = new GameModel(edition);
        }
        try {
            if (view.targetScoreField.isVisible() && !view.targetScoreField.getText().isEmpty()) {
                int targetScore = Integer.parseInt(view.targetScoreField.getText());
                gameModel.setTargetScore(targetScore);
            } else if (view.numberOfRoundsField.isVisible() && !view.numberOfRoundsField.getText().isEmpty()) {
                int targetRounds = Integer.parseInt(view.numberOfRoundsField.getText());
                gameModel.setTargetRounds(targetRounds);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.mainFrame, "Invalid number format");
            return;
        }
    
        GameView gameView = new GameView(this, gameModel, view.confirmedPlayerNames);
        view.mainFrame.setVisible(false);
        gameView.showGameView();
    }

    private void toggleButtonVisibility() {
        boolean showMainButtons = !view.play.isVisible();
        setVisibilityForComponents(new JComponent[]{view.play, view.instructions, view.highScores}, showMainButtons);
        setVisibilityForComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition}, !showMainButtons);
        view.targetScoreLabel.setVisible(false);
        view.targetScoreField.setVisible(false);
        view.numberOfRoundsLabel.setVisible(false);
        view.numberOfRoundsField.setVisible(false);
        view.backButton.setVisible(true);
        view.startGame.setVisible(false);
        hideNameEntryComponents();
    }

    private void showPlayerSelection() {
        setVisibilityForComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition}, false);
        setVisibilityForComponents(new JComponent[]{view.playerThree, view.playerFour, view.playerFive}, true);
        view.startGame.setVisible(false);
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
        setVisibilityForComponents(new JComponent[]{view.pointsMode, view.roundsMode, view.backButton}, true);
        setVisibilityForComponents(new JComponent[]{view.confirmNames, view.playerThree, view.playerFour, view.playerFive}, false);
        GUITools.updatePanel(view.mainPanel);
        hideNameEntryComponents();
    }

    public void showMainMenu() {
        setVisibilityForComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition, view.confirmNames, 
            view.pointsMode, view.roundsMode,view.playerThree, view.playerFour, view.playerFive}, false);
        setVisibilityForComponents(new JComponent[]{view.play, view.instructions, view.highScores}, true);
        view.targetScoreLabel.setVisible(false);
        view.targetScoreField.setVisible(false);
        view.numberOfRoundsLabel.setVisible(false);
        view.numberOfRoundsField.setVisible(false);
        view.backButton.setVisible(false);
        view.startGame.setVisible(false);
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
