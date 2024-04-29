package clash_of_cards.model;

import java.util.Properties;

public interface WinObserver {
    void winCountsUpdated(Properties newWinCounts);
}
