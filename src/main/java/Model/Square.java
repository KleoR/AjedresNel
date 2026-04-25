package Model;

import Model.Enum.Color;
import Model.Enum.Column;
import Model.Enum.Row;

public class Square {
    private final Color color;
    private final Column col;
    private final Row row;
    private Piece piece;

    public Square(int col, int row) {
        this.col = Column.columnFromIndex(col);
        this.row = Row.rowFromIndex(row);
        this.color = Color.colorFromIndexes(this.col, this.row);
    }

    public int getColumnFromIndex() {
        return col.getIndex();
    }

    public int getRowFromIndex() {
        return row.getIndex();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) piece.setSquare(this);
    }

    @Override
    public String toString() {
        if (this.piece != null) return piece.toString();
        return (this.color == Color.WHITE) ? "░" : " ";
    }
}
