package Controller.Enum;

import View.ConsoleView;

public enum GameMenuOption {
    MOVE_PIECE(1),
    RESIGN(2),
    DRAW(3),
    SAVE_GAME(4),
    EXIT(0);

    private final int index;

    GameMenuOption(int index) {
        this.index = index;
    }

    public static GameMenuOption fromIndex(int index) {
        for (GameMenuOption gm : values()) if (gm.index == index) return gm;
        throw new IllegalArgumentException("Opción Invalida");
    }
}
