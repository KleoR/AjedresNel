package Model;

import Model.Enum.Color;
import Model.Enum.Column;
import Model.Enum.Row;

public class Square {
    private Column col;
    private Row row;
    private Color color;
    private Piece piece;

    public Square(int col, int row) {
        this.col = Column.columnFromIndex(col);
        this.row = Row.rowFromIndex(row);
        this.color = Color.colorFromIndexes(this.col, this.row);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private String squareColor(){
        if(this.color == Color.White) return "▓";
        else return "░";
    }

    @Override
    public String toString() {
        if (this.piece == null) return squareColor();
        else return piece.toString();
    }
}
