package clash_of_cards.controller;

public class ControllerMediator {
    private MainMenuController mainMenuController;
    private GameController gameController;

    public void setMainMenuController(MainMenuController controller) {
        this.mainMenuController = controller;
    }

    public void setGameController(GameController controller) {
        this.gameController = controller;
    }

    public void showMainMenu() {
        mainMenuController.showMainMenu();
    }
}
