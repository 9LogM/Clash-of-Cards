package clash_of_cards.util;

import clash_of_cards.model.GameModel;
import java.io.*;

public class GameUtils {
    public static void saveGame(GameModel gameModel) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_data.ser"))) {
            oos.writeObject(gameModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameModel loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_data.ser"))) {
            return (GameModel) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
