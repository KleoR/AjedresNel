package Model;

import Model.Enum.Color;
import Model.Enum.GameStatus;

public class Game {
    private final Board board;
    private final GameStatus status;
    private Color turn;

    public Game() {
        this.board = new Board(false);
        this.status = GameStatus.IN_PROGRESS;
        this.turn = Color.White;
    }

    public Game(Board b, GameStatus gs, Color c) {
        this.board = b;
        this.status = gs;
        this.turn = c;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurn() {
        return turn;
    }

    public void passTurn() {
        if (this.turn == Color.White) this.turn = Color.Black;
        else this.turn = Color.White;
    }
}
