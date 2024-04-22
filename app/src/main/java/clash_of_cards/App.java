package clash_of_cards;

import clash_of_cards.view.MainMenuView;
import clash_of_cards.controller.MainMenuController;

public class App {
    public static void main(String[] args) {
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(mainMenuView);
        mainMenuController.showMainMenu();
    }
}
