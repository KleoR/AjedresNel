package Controller;

import Controller.Enum.GameMenuOption;
import Controller.Enum.MainMenuOption;
import Model.Enum.GameStatus;
import View.ConsoleView;

public class MenuController {
    private final ConsoleView view;
    private final GameController gameController;

    public MenuController() {
        this.view = new ConsoleView();
        this.gameController = new GameController(this.view);
    }

    public void showMainMenu() {
        view.showTitle();
        while (true) {
            view.showMainMenu();
            switch (MainMenuOption.getMainMenuOption(view)) {
                case CREATE_GAME:
                    gameController.createNewGame();
                    showGameMenu();
                    break;
                case LOAD_GAME:
                    if (gameController.loadGame()) showGameMenu();
                    break;
                case EXIT:
                    view.showExit();
                    System.exit(0);
            }
        }
    }

    public void showGameMenu() {
        while (true) {
            view.showGameMenu();
            switch (GameMenuOption.getGameMenuOption(view)) {
                case MOVE_PIECE:
                    while (gameController.movePiece()) {
                        gameController.showBoard();
                    }
                    break;
                case RESIGN:
                    if (gameController.resignGame()) return;
                    break;
                case DRAW:
                    if (gameController.offerDraw()) return;
                    break;
                case SAVE_GAME:
                    gameController.saveGame();
                    break;
                case EXIT:
                    return;

            }
        }
    }
}
