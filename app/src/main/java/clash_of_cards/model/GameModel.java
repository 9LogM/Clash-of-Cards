package clash_of_cards.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clash_of_cards.model.GameRound;

public class GameModel {
    private List<String> playerNames;
    private HashMap<String, Player> playerCards;
    private String edition;
    private ContentLoader text;
    private int targetScore = 0;
    private int targetRounds = 0;
    private WinCountManager winCountManager;
    private HashMap<String, ScoreObserver> observers = new HashMap<>();
    private GameRound round;

    public GameModel(String edition, WinCountManager winCountManager, List<String> playerNames) {
        this.edition = edition;
        this.winCountManager = winCountManager;
        this.playerCards = new HashMap<>();
        this.text = new ContentLoader(edition);
        this.playerNames = playerNames;
        this.round = new GameRound(this, playerNames.get(0));
    }

    public void assignCardsToPlayer(String playerName) {
        Player player = new Player();
        for (int i = 0; i < 6; i++) {
            player.addCard(text.getRandom("Answer"));
        }
        playerCards.put(playerName, player);
    }

    public void storeSelectedCard(String playerName, String card) {
        round.storeSelectedCard(playerName, card);
    }

    public void addObserver(String playerName, ScoreObserver observer) {
        observers.put(playerName, observer);
    }

    public void removeObserver(String playerName) {
        observers.remove(playerName);
    }

    public void notifyObservers(String playerName) {
        if (observers.containsKey(playerName)) {
            int score = getPlayerScore(playerName);
            observers.get(playerName).updateScore(score);
        }
    }

    public boolean checkGameEnd() {
        if (targetScore > 0) {
            return playerCards.values().stream().anyMatch(player -> player.getScore() >= targetScore);
        } else if (targetRounds > 0) {
            return round.getCurrentRound() > targetRounds;
        }
        return false;
    }

    public void setTargetScore(int score) {
        this.targetScore = score;
    }

    public void setTargetRounds(int rounds) {
        this.targetRounds = rounds;
    }

    public String getCurrentJudge() {
        return round.getCurrentJudge();
    }

    public int getCurrentRound() {
        return round.getCurrentRound();
    }

    public HashMap<String, String> getStoredCards() {
        return round.getStoredCards();
    }

    public Player getPlayer(String playerName) {
        if (!playerCards.containsKey(playerName)) {
            assignCardsToPlayer(playerName);
        }
        return playerCards.get(playerName);
    }

    public List<String> getPlayerCards(String playerName) {
        return playerCards.get(playerName).getCards();
    }

    public int getPlayerScore(String playerName) {
        return getPlayer(playerName).getScore();
    }

    public String getEdition() {
        return edition;
    }

    public WinCountManager getWinCountManager() {
        return winCountManager;
    }

    public ContentLoader getTextLoader() {
        return text;
    }

    public HashMap<String, Player> getAllPlayers() {
        return playerCards;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public String getWinner() {
        return playerCards.entrySet().stream()
            .max(Map.Entry.comparingByValue(Comparator.comparingInt(Player::getScore)))
            .map(Map.Entry::getKey)
            .orElse("No winner");
    }

    public void endRound(String winnerName) {
        round.endRound(winnerName);
    }

    public void resetGame() {
        playerCards.clear();
        for (Player player : playerCards.values()) {
            player.resetScore();
        }
        round.resetRound();
    }
}
