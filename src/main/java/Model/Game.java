package Model;

import Model.Enum.Color;
import Model.Enum.GameStatus;

import java.util.ArrayList;

public class Game {
    private final Board board;
    private GameStatus status;
    private Color turn;
    private final ArrayList<Piece> capturedPieces;

    public Game() {
        this.board = new Board(false, "");
        this.status = GameStatus.IN_PROGRESS;
        this.turn = Color.WHITE;
        this.capturedPieces = new ArrayList<Piece>();
    }

    public Game(Board b, GameStatus gs, Color c, ArrayList<Piece> p) {
        this.board = b;
        this.status = gs;
        this.turn = c;
        this.capturedPieces = p;
    }

    public ArrayList<Piece> getCapturedPieces() {
        return capturedPieces == null ? new ArrayList<>() : capturedPieces;
    }

    public void addCapturedPiece(Piece piece){
        capturedPieces.add(piece);
    }

    public void setStatus(GameStatus newGS) {
        this.status = newGS;
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
        if (this.turn == Color.WHITE) this.turn = Color.BLACK;
        else this.turn = Color.WHITE;
    }
}
