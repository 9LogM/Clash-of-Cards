package clash_of_cards.model;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private int score;
    private List<String> cards;

    public Player() {
        this.score = 0;
        this.cards = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void resetScore() {
        this.score = 0;
    }

    public List<String> getCards() {
        return new ArrayList<>(cards);
    }

    public void addCard(String card) {
        cards.add(card);
    }

    public void removeCard(String card) {
        cards.remove(card);
    }

    public void incrementScore() {
        score++;
    }
}
