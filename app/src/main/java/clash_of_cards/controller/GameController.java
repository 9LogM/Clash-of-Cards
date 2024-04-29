package clash_of_cards.controller;

import clash_of_cards.model.GameModel;
import clash_of_cards.view.GameView;
import clash_of_cards.model.ContentLoader;
import javax.swing.*;

public class GameController {
    private GameView view;
    private GameModel model;
    private ContentLoader textLoader;
    private ControllerMediator mediator;

    public GameController(GameModel model, ControllerMediator mediator) {
        this.model = model;
        this.mediator = mediator;
        this.textLoader = new ContentLoader(model.getEdition());
        this.view = new GameView(this, model);
        updateBlackCard();
        updateJudgeStatus();
    }

    public void updateRound() {
        if (model.checkGameEnd()) {
            showEndGame();
        } else {
            updateBlackCard();
            hideJudgeCards();
            updateJudgeStatus();
        }
    }

    public void updateBlackCard() {
        String sentence = textLoader.getRandom("Sentence");
        view.updateBlackCard(model.getCurrentRound(), sentence);
    }

    private void updateJudgeStatus() {
        String currentJudge = model.getCurrentJudge();
        view.updateJudge(currentJudge);
    }

    public void displayPlayerCards(String playerName) {
        view.displayPlayerCards(model.getPlayerCards(playerName), playerName, (player, card) -> model.storeSelectedCard(player, card));
    }

    public void displayStoredCards() {
        view.displayStoredCards(model.getStoredCards(), (playerName, card) -> {
            model.endRound(playerName);
            updateRound();
        });
    }

    public void hidePlayerCards() {
        view.clearWhiteCardsPanel();
    }

    private void hideJudgeCards() {
        view.clearWhiteCardsPanel();
    }

    public void showGameView() {
        view.show();
    }

    public void showMainMenu() {
        mediator.showMainMenu();
    }

    private void showEndGame() {
        String winner = model.getWinner();
        JOptionPane.showMessageDialog(null, "Game Over! The winner is " + winner + ". Returning to main menu.");
        model.resetGame();
        view.dispose();
        showMainMenu();
    }

}
