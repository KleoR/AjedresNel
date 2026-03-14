package Controller.Enum;

import View.ConsoleView;

public enum MainMenuOption {
    CREATE_GAME(1),
    LOAD_GAME(2),
    EXIT(0);

    private int index;

    MainMenuOption(int index) {
        this.index = index;
    }

    public static MainMenuOption getMainMenuOption(ConsoleView view){
        return MainMenuOption.fromInt(view.readInt(MainMenuOption.values().length));
    }

    public static MainMenuOption fromInt(int index) {
        for (MainMenuOption option : values()) if (option.index == index) return option;
        throw new IllegalArgumentException("Opción no Valida");
    }
}
