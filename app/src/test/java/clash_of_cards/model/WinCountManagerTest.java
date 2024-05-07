package clash_of_cards.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.util.Properties;

class WinCountManagerTest {
    private WinCountManager winCountManager;
    private WinObserver mockObserver;
    private File file;

    @BeforeEach
    void setUp() throws IOException {
        file = new File("winCounts.properties");
        if (!file.exists()) {
            file.createNewFile();
        }
        winCountManager = new WinCountManager();
        mockObserver = mock(WinObserver.class);
        winCountManager.addObserver(mockObserver);
    }

    @AfterEach
    void tearDown() {
        file.delete();
    }

    @Test
    void testLoadWinCountsFileExists() throws IOException {
        Properties props = new Properties();
        props.setProperty("player1", "1");
        try (FileOutputStream out = new FileOutputStream(file)) {
            props.store(out, "Test");
        }
        winCountManager = new WinCountManager();
        assertEquals(1, winCountManager.getWinCount("player1"));
    }

    @Test
    void testLoadWinCountsFileDoesNotExist() {
        File missingFile = new File("missingFile.properties");
        missingFile.delete();
        WinCountManager manager = new WinCountManager();
        assertEquals(0, manager.getWinCount("unknown"));
    }

    @Test
    void testSaveWinCounts() throws IOException {
        winCountManager.incrementWinCount("player2");
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(file)) {
            props.load(in);
        }
        assertEquals("1", props.getProperty("player2"));
    }

    @Test
    void testIncrementWinCountNewPlayer() {
        winCountManager.incrementWinCount("newplayer");
        assertEquals(1, winCountManager.getWinCount("newplayer"));
    }

    @Test
    void testResetWinCounts() throws IOException {
        winCountManager.incrementWinCount("player4");
        winCountManager.resetWinCounts();
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(file)) {
            props.load(in);
        }
        assertTrue(props.isEmpty());
    }

    @Test
    void testIncrementWinCountExistingPlayer() throws IOException {   
        winCountManager.incrementWinCount("existingPlayer");
        winCountManager.incrementWinCount("existingPlayer");
        assertEquals(2, winCountManager.getWinCount("existingPlayer"), "The win count should be incremented to 2.");
    }
}
