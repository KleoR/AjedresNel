package Model;

import Model.Enum.Color;
import Model.Pieces.PieceType;

import java.util.ArrayList;

public abstract class Piece {
    private Color color;
    protected Board board;
    private Square square;

    public Piece(Color color, Board board) {
        this.color = color;
        this.board = board;
    }

    public Color getColor() {
        return color;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

    public abstract PieceType getType();

    public abstract ArrayList<Square> getValidMovements();

    @Override
    public String toString() {
        return "" + getType().getSymbol(this.color);
    }
}
