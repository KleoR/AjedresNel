package Model;

import Model.Enum.Color;
import Model.Enum.GameStatus;

public class Game {
    private Board board;
    private GameStatus status;
    private Color turn;

    public Game() {
        this.board = new Board();
        this.status = GameStatus.IN_PROGRESS;
        this.turn = Color.White;
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurn() {
        return turn;
    }

    public void passTurn(){
        if(this.turn == Color.White) this.turn = Color.Black;
        else this.turn = Color.White;
    }
}
