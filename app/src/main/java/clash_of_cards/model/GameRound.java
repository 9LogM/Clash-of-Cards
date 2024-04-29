package clash_of_cards.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameRound implements Serializable {
    private static final long serialVersionUID = 1L;
    private String currentJudge;
    private int currentRound;
    private HashMap<String, String> storedCards;
    private GameModel model;

    public GameRound(GameModel model, String startingJudge) {
        this.model = model;
        this.currentJudge = startingJudge;
        this.currentRound = 1;
        this.storedCards = new HashMap<>();
    }

    public String getCurrentJudge() {
        return currentJudge;
    }

    public void setCurrentJudge(String judgeName) {
        currentJudge = judgeName;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void storeSelectedCard(String playerName, String card) {
        storedCards.put(playerName, card);
    }

    public HashMap<String, String> getStoredCards() {
        return storedCards;
    }

    public void endRound(String winnerName) {
        Player winner = model.getPlayer(winnerName);
        winner.incrementScore();
        model.notifyObservers(winnerName);
        setCurrentJudge(winnerName);
        Set<String> uniqueCards = new HashSet<>(storedCards.values());

        for (Player player : model.getAllPlayers().values()) {
            uniqueCards.forEach(card -> {
                if (player.getCards().contains(card)) {
                    player.removeCard(card);
                    player.addCard(model.getTextLoader().getRandom("Answer"));
                }
            });
        }

        storedCards.clear();
        currentRound++;

        if (model.checkGameEnd()) {
            model.getWinCountManager().incrementWinCount(winnerName);
        }
    }

    public void resetRound() {
        storedCards.clear();
        currentRound = 1;
    }
}
