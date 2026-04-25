package Model.Enum;

public enum GameStatus {
    IN_PROGRESS(""),
    WHITE_WINS("¡ VICTORIA PARA EL BLANCO !"),
    BLACK_WINS("¡ VICTORIA PARA EL NEGRO !"),
    DRAW("    ♔  ⚔  EMPATE  ⚔  ♚    ");

    private final String message;

    GameStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
