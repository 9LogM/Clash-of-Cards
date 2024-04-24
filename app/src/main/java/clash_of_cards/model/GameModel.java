package clash_of_cards.model;

import java.util.HashMap;
import java.util.List;

public class GameModel {
    private String edition;
    private HashMap<String, Player> playerCards;
    private HashMap<String, String> storedCards;
    private Answers answers;
    private int targetScore = 0;
    private int targetRounds = 0;
    private int currentRound = 0;

    public GameModel(String edition) {
        this.edition = edition;
        this.playerCards = new HashMap<>();
        this.storedCards = new HashMap<>();
        this.answers = new Answers(edition);
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
        Answers answers = new Answers(edition);
        for (int i = 0; i < 6; i++) {
            player.addCard(answers.getRandomAnswer());
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
        storedCards.values().forEach(card -> {
            playerCards.forEach((playerName, player) -> {
                player.getCards().remove(card);
                player.addCard(answers.getRandomAnswer());
            });
        });
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
