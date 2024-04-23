package clash_of_cards.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameModel {
    private List<Player> players;
    private List<String> whiteCards;
    private List<String> blackCards;
    private int targetPoints;
    private int numberOfRounds;
    private String edition;
    private int currentJudgeIndex;
    private String currentBlackCard;
    private List<String> currentWhiteCardsSelection;

    public GameModel(String edition, List<String> playerNames, int targetPoints, int numberOfRounds) {
        this.edition = edition;
        this.targetPoints = targetPoints;
        this.numberOfRounds = numberOfRounds;
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        loadCards();
        shufflePlayers();
        currentJudgeIndex = 0;
    }

    private void loadCards() {
        Answers answers = new Answers(edition);
        Sentences sentences = new Sentences(edition);
        whiteCards = answers.getAllAnswers();
        blackCards = sentences.getAllSentences();
        Collections.shuffle(whiteCards);
        Collections.shuffle(blackCards);
    }

    private void shufflePlayers() {
        Collections.shuffle(players);
    }

    public void startNewRound() {
        currentBlackCard = blackCards.remove(0);
        currentWhiteCardsSelection = new ArrayList<>();
        distributeWhiteCards();
    }

    private void distributeWhiteCards() {
        for (Player player : players) {
            while (player.getCards().size() < 6) {
                player.addCard(whiteCards.remove(0));
            }
        }
    }

    public void submitWhiteCard(String playerName, String card) {
        currentWhiteCardsSelection.add(card);
        players.stream()
               .filter(p -> p.getName().equals(playerName))
               .findFirst()
               .ifPresent(p -> p.getCards().remove(card));
    }

    public String getCurrentBlackCard() {
        return currentBlackCard;
    }

    public List<String> getCurrentWhiteCardsSelection() {
        return currentWhiteCardsSelection;
    }

    public Player getCurrentJudge() {
        return players.get(currentJudgeIndex);
    }

    public void nextJudge() {
        currentJudgeIndex = (currentJudgeIndex + 1) % players.size();
    }
}
