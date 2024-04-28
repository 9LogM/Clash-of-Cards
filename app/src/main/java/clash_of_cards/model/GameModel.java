package clash_of_cards.model;

import clash_of_cards.model.ScoreObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameModel {
    private String edition;
    private HashMap<String, Player> playerCards;
    private HashMap<String, String> storedCards;
    private SentencesAndAnswers text;
    private int targetScore = 0;
    private int targetRounds = 0;
    private int currentRound = 0;
    private HashMap<String, ScoreObserver> observers = new HashMap<>();

    public GameModel(String edition) {
        this.edition = edition;
        this.playerCards = new HashMap<>();
        this.storedCards = new HashMap<>();
        this.text = new SentencesAndAnswers(edition);
    }

    public void addObserver(String playerName, ScoreObserver observer) {
        observers.put(playerName, observer);
    }
    
    public void removeObserver(String playerName) {
        observers.remove(playerName);
    }
    
    private void notifyObservers(String playerName) {
        if (observers.containsKey(playerName)) {
            int score = getPlayerScore(playerName);
            observers.get(playerName).updateScore(score);
        }
    }

    public void setTargetScore(int score) {
        this.targetScore = score;
    }

    public void setTargetRounds(int rounds) {
        this.targetRounds = rounds;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public int getTargetRounds() {
        return targetRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void assignCardsToPlayer(String playerName) {
        Player player = new Player();
        for (int i = 0; i < 6; i++) {
            player.addCard(text.getRandomAnswer());
        }
        playerCards.put(playerName, player);
    }

    public List<String> getPlayerCards(String playerName) {
        return playerCards.get(playerName).getCards();
    }

    public void storeSelectedCard(String playerName, String card) {
        storedCards.put(playerName, card);
    }

    public HashMap<String, String> getStoredCards() {
        return storedCards;
    }

    public Player getPlayer(String playerName) {
        if (!playerCards.containsKey(playerName)) {
            assignCardsToPlayer(playerName);
        }
        return playerCards.get(playerName);
    }

    public int getPlayerScore(String playerName) {
        return getPlayer(playerName).getScore();
    }

    public String getEdition() {
        return edition;
    }

    public void endRound(String winnerName) {
        Player winner = playerCards.get(winnerName);
        winner.incrementScore();
        notifyObservers(winnerName);
        
        Set<String> uniqueCards = new HashSet<>(storedCards.values());
        
        for (Player player : playerCards.values()) {
            uniqueCards.forEach(card -> {
                if (player.getCards().contains(card)) {
                    player.removeCard(card);
                    player.addCard(text.getRandomAnswer());
                }
            });
        }
        
        storedCards.clear();
        currentRound++;
    }
    
    public boolean checkGameEnd() {
        if (targetScore > 0) {
            return playerCards.values().stream().anyMatch(player -> player.getScore() >= targetScore);
        } else if (targetRounds > 0) {
            return currentRound >= targetRounds;
        }
        return false;
    }
}
