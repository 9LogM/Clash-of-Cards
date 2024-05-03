package clash_of_cards.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameRoundTest {
    private GameModel model;
    private GameRound gameRound;

    @BeforeEach
    void setUp() {
        model = new GameModel("standard", new WinCountManager(), List.of("Alice", "Bob"));
        gameRound = new GameRound(model, "Alice");
    }

    @Test
    void testRoundOperations() {
        assertEquals("Alice", gameRound.getCurrentJudge(), "Initial judge should be Alice.");
        gameRound.setCurrentJudge("Bob");
        assertEquals("Bob", gameRound.getCurrentJudge(), "Current judge should be updated to Bob.");
        gameRound.storeSelectedCard("Alice", "Card1");
        assertEquals("Card1", gameRound.getStoredCards().get("Alice"), "Stored card should match the input.");
        gameRound.endRound("Bob");
        assertEquals(1, model.getPlayer("Bob").getScore(), "Winning player's score should increment.");
        gameRound.resetRound();
        assertTrue(gameRound.getStoredCards().isEmpty(), "Stored cards should be cleared after reset.");
    }
}
