package Controller.Enum;

public enum MainMenuOption {
    CREATE_GAME(1),
    LOAD_GAME(2),
    EXIT(0);

    private final int index;

    MainMenuOption(int index) {
        this.index = index;
    }

    public static MainMenuOption fromIndex(int index) {
        for (MainMenuOption option : values()) if (option.index == index) return option;
        throw new IllegalArgumentException("Opción no Valida");
    }
}
