package clash_of_cards.model;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class GameModel {
    private String edition;
    private HashMap<String, Player> playerCards;
    private HashMap<String, String> storedCards;

    public GameModel(String edition) {
        this.edition = edition;
        this.playerCards = new HashMap<>();
        this.storedCards = new HashMap<>();
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
}
