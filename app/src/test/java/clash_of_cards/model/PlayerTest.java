package clash_of_cards.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void testScoreOperations() {
        assertEquals(0, player.getScore(), "Initial score should be zero.");
        player.incrementScore();
        assertEquals(1, player.getScore(), "Score should increment by 1.");
        player.setScore(5);
        assertEquals(5, player.getScore(), "Set score should work correctly.");
        player.resetScore();
        assertEquals(0, player.getScore(), "Reset score should set score to zero.");
    }

    @Test
    void testCardOperations() {
        player.addCard("Ace of Spades");
        List<String> cards = player.getCards();
        assertTrue(cards.contains("Ace of Spades"), "Added card should be in the list.");
        player.removeCard("Ace of Spades");
        assertFalse(player.getCards().contains("Ace of Spades"), "Removed card should not be in the list.");
    }
}
