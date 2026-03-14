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
                    System.out.println("En proceso");
                    break;
                case EXIT:
                    System.out.println("Saliendo del Juego...");
                    System.exit(0);
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
                    break;
                case DRAW:
                    break;
                case SAVE_GAME:
                    break;
                case EXIT:
                    return;
            }
        }
    }
}
