package Controller;

import Controller.Enum.GameMenuOption;
import Controller.Enum.MainMenuOption;
import View.ConsoleView;

public class MenuController {
    private final ConsoleView view;
    private GameController gameController;

    public MenuController() {
        this.view = new ConsoleView();
        this.gameController = new GameController(this.view, this);
    }

    public void showMainMenu(){
        view.showTitle();
        while (true){
            view.showMainMenu();
            switch (MainMenuOption.getMainMenuOption(view)){
                case CREATE_GAME:
                    gameController.createNewGame();
                    showGameMenu();
                    break;
                case LOAD_GAME:
                    if (gameController.loadGame()) showGameMenu();
                    break;
                case EXIT:
                    view.showExit();
            }
        }
    }

    public void showGameMenu(){
        while (true){
            view.showGameMenu();
            switch (GameMenuOption.getGameMenuOption(view)){
                case MOVE_PIECE:
                    while (gameController.movePiece()){
                        gameController.showBoard();
                    }
                    break;
                case RESIGN:
                    //Todo 2
                    break;
                case DRAW:
                     //Todo
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
