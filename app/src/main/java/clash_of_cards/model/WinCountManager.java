package clash_of_cards.model;

import clash_of_cards.model.WinObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class WinCountManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private Properties winCounts = new Properties();
    private File winCountFile = new File("winCounts.properties");
    private List<WinObserver> observers = new ArrayList<>();

    public WinCountManager() {
        loadWinCounts();
    }

    private void loadWinCounts() {
        if (winCountFile.exists()) {
            try (FileInputStream in = new FileInputStream(winCountFile)) {
                winCounts.load(in);
            } catch (IOException e) {
                System.err.println("Error loading win counts: " + e.getMessage());
            }
        }
    }

    public void saveWinCounts() {
        try (FileOutputStream out = new FileOutputStream(winCountFile)) {
            winCounts.store(out, "Win Counts for Clash of Cards");
            notifyObservers();
        } catch (IOException e) {
            System.err.println("Error saving win counts: " + e.getMessage());
        }
    }

    public void incrementWinCount(String playerName) {
        playerName = playerName.toLowerCase();
        int wins = Integer.parseInt(winCounts.getProperty(playerName, "0"));
        winCounts.setProperty(playerName, String.valueOf(++wins));
        saveWinCounts();
    }

    public int getWinCount(String playerName) {
        return Integer.parseInt(winCounts.getProperty(playerName.toLowerCase(), "0"));
    }

    public Properties getWinCounts() {
        return winCounts;
    }

    public void resetWinCounts() {
        winCounts.clear();
        try (FileOutputStream out = new FileOutputStream(winCountFile)) {
            winCounts.store(out, "Win Counts Reset");
            notifyObservers();
        } catch (IOException e) {
            System.err.println("Error resetting win counts: " + e.getMessage());
        }
    }

    public void addObserver(WinObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (WinObserver observer : observers) {
            observer.winCountsUpdated(new Properties(winCounts));
        }
    }
}