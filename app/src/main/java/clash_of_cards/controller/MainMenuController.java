package clash_of_cards.controller;

import clash_of_cards.model.GameModel;
import clash_of_cards.model.WinCountManager;
import clash_of_cards.util.GUITools;
import clash_of_cards.util.GameUtils;
import clash_of_cards.view.MainMenuView;
import clash_of_cards.view.HighScoresView;

import javax.swing.*;

public class MainMenuController {
    private ControllerMediator mediator;
    private MainMenuView view;
    private GameModel gameModel;
    private HighScoresView highScoresView;
    private WinCountManager winCountManager;
    private GameController gameController;

    public MainMenuController(MainMenuView view, ControllerMediator mediator) {
        this.mediator = mediator;
        this.view = view;
        this.winCountManager = new WinCountManager();
        this.highScoresView = new HighScoresView(e -> showMainMenu(), winCountManager);
        this.mediator.setMainMenuController(this);
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        view.newGame.addActionListener(e -> newGame());
        view.continueGame.addActionListener(e -> continueGame());
        view.highScores.addActionListener(e -> showHighScores());
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
        setVisibleComponents(new JComponent[]{view.numberOfRoundsLabel, view.numberOfRoundsField}, false);
        setVisibleComponents(new JComponent[]{view.targetScoreLabel, view.targetScoreField, view.startGame}, true);
        setVisibleComponents(new JComponent[]{view.pointsMode, view.roundsMode}, false);
        GUITools.updatePanel(view.mainPanel);
    }

    private void setupRoundsMode() {
        setVisibleComponents(new JComponent[]{view.targetScoreLabel, view.targetScoreField}, false);
        setVisibleComponents(new JComponent[]{view.numberOfRoundsLabel, view.numberOfRoundsField, view.startGame}, true);
        setVisibleComponents(new JComponent[]{view.pointsMode, view.roundsMode}, false);
        GUITools.updatePanel(view.mainPanel);
    }

    private void startGame() {
        if (gameModel == null) {
            String edition = view.startFamilyEdition.isSelected() ? "Family" : "Nerd";
            gameModel = new GameModel(edition, winCountManager, view.confirmedPlayerNames);
        }
        try {
            setGameMode();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.mainFrame, "Invalid number format");
            return;
        }
        gameController = new GameController(gameModel, mediator);
        gameController.showGameView();
        view.mainFrame.setVisible(false);
    }

    private void setGameMode() throws NumberFormatException {
        if (view.targetScoreField.isVisible() && !view.targetScoreField.getText().isEmpty()) {
            gameModel.setTargetScore(Integer.parseInt(view.targetScoreField.getText()));
        } else if (view.numberOfRoundsField.isVisible() && !view.numberOfRoundsField.getText().isEmpty()) {
            gameModel.setTargetRounds(Integer.parseInt(view.numberOfRoundsField.getText()));
        }
    }

    private void continueGame() {
        GameModel loadedGame = GameUtils.loadGame();
        if (loadedGame != null) {
            gameModel = loadedGame;
            gameController = new GameController(gameModel, mediator);
            gameController.showGameView();
            view.mainFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(view.mainFrame, "No saved game found.");
        }
    }

    private void newGame() {
        if (gameModel != null) {
            gameModel.resetGame();
        }
        view.targetScoreField.setText("");
        view.numberOfRoundsField.setText("");
        resetPlayerNameFields();
        boolean showMainButtons = !view.newGame.isVisible();
        setVisibleComponents(new JComponent[]{view.newGame, view.instructions, view.highScores}, showMainButtons);
        setVisibleComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition}, !showMainButtons);
        setVisibleComponents(new JComponent[]{view.targetScoreLabel, view.targetScoreField, view.numberOfRoundsLabel, view.numberOfRoundsField}, false);
        view.backButton.setVisible(true);
        view.continueGame.setVisible(false);
        hideNameEntryComponents();
    }
    
    private void resetPlayerNameFields() {
        for (JTextField playerNameField : view.playerNames) {
            playerNameField.setText("");
        }
    }    

    private void showPlayerSelection() {
        setVisibleComponents(new JComponent[]{view.startFamilyEdition, view.startNerdEdition}, false);
        setVisibleComponents(new JComponent[]{view.playerThree, view.playerFour, view.playerFive}, true);
        hideNameEntryComponents();
    }

    private void configureNameEntry(int numberOfPlayers) {
        for (int i = 0; i < view.playerNames.length; i++) {
            view.playerNames[i].setVisible(i < numberOfPlayers);
        }
        setVisibleComponents(new JComponent[]{view.confirmNames, view.nameEntryPanel}, true);
        setVisibleComponents(new JComponent[]{view.playerThree, view.playerFour, view.playerFive}, false);
        GUITools.updatePanel(view.nameEntryPanel);
    }

    private void confirmNames() {
        view.confirmedPlayerNames.clear();
        for (JTextField playerName : view.playerNames) {
            if (playerName.isVisible() && !playerName.getText().trim().isEmpty()) {
                view.confirmedPlayerNames.add(playerName.getText().trim());
            }
        }
        setVisibleComponents(new JComponent[]{view.pointsMode, view.roundsMode, view.backButton}, true);
        setVisibleComponents(new JComponent[]{view.confirmNames, view.playerThree, view.playerFour, view.playerFive}, false);
        GUITools.updatePanel(view.mainPanel);
        hideNameEntryComponents();
    }

    private void showHighScores() {
        highScoresView.show();
        view.mainFrame.setVisible(false);
    }

    public void showMainMenu() {
        setVisibleComponents(new JComponent[]{
            view.startFamilyEdition, view.startNerdEdition, view.confirmNames, view.pointsMode, view.roundsMode, view.playerThree, view.playerFour, view.playerFive
        }, false);
        setVisibleComponents(new JComponent[]{view.newGame, view.instructions, view.highScores, view.continueGame}, true);
        setVisibleComponents(new JComponent[]{view.targetScoreLabel, view.targetScoreField, view.numberOfRoundsLabel, view.numberOfRoundsField, view.startGame, view.backButton}, false);
        hideNameEntryComponents();
        view.mainFrame.setVisible(true);
        highScoresView.hide();
    }

    private void setVisibleComponents(JComponent[] components, boolean visible) {
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
