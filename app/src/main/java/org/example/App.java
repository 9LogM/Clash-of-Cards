package clash_of_cards;

import clash_of_cards.view.MainMenuView;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenuView mainMenuView = new MainMenuView();
                mainMenuView.setVisible(true);
            }
        });
    }
}