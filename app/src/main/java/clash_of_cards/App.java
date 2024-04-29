package clash_of_cards;

import clash_of_cards.view.MainMenuView;
import clash_of_cards.controller.MainMenuController;
import clash_of_cards.controller.ControllerMediator;

public class App {
    public static void main(String[] args) {
        ControllerMediator mediator = new ControllerMediator();
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, mediator);
        mainMenuController.showMainMenu();
    }
}
