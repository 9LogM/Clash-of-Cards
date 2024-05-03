package clash_of_cards.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = new GameModel("standard", new WinCountManager(), List.of("Alice", "Bob"));
    }

    @Test
    void testGameOperations() {
        gameModel.assignCardsToPlayer("Alice");
        assertNotNull(gameModel.getPlayer("Alice"), "Player should exist after assigning cards.");
        gameModel.addObserver("Alice", score -> assertEquals(0, score, "Observer should be notified with correct score."));
        gameModel.notifyObservers("Alice");
        gameModel.storeSelectedCard("Alice", "Card1");
        assertEquals("Card1", gameModel.getStoredCards().get("Alice"), "Stored card should match the input.");
    }
}
