package clash_of_cards.controller;

import clash_of_cards.model.GameModel;
import clash_of_cards.view.GameView;

public class GameController {
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        attachViewEvents();
    }

    private void attachViewEvents() {
        view.setStartRoundAction(this::startRound);
        view.setCardSubmissionAction(this::submitCard);
        view.setNextJudgeAction(this::nextJudge);
    }

    private void startRound() {
        model.startNewRound();
        view.updateBlackCard(model.getCurrentBlackCard());
        view.updatePlayerCards();
        view.displayJudge(model.getCurrentJudge().getName());
    }

    private void submitCard(String playerName, String card) {
        model.submitWhiteCard(playerName, card);
        view.updateSubmittedCards(model.getCurrentWhiteCardsSelection());
    }

    private void nextJudge() {
        model.nextJudge();
        view.displayJudge(model.getCurrentJudge().getName());
    }
}
