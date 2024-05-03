package clash_of_cards.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContentLoaderTest {
    private ContentLoader contentLoader;

    @BeforeEach
    void setUp() {
        contentLoader = new ContentLoader("standard");
    }

    @Test
    void testContentLoadingAndRetrieval() {
        String answer = contentLoader.getRandom("Answer");
        assertNotNull(answer, "Should retrieve a random answer.");
        assertTrue(contentLoader.answersLeft(), "Should indicate answers are left after retrieval.");
    }
}
