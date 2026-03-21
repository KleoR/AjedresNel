package Model;

import Model.Enum.Color;
import Model.Enum.Column;
import Model.Enum.Row;

public class Square {
    private final Column col;
    private final Row row;
    private final Color color;
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
        if (this.piece != null && piece != null && piece != this.piece)
            this.piece.setSquare(null); //Todo Pendiente de entender, pero funcionar funciona
        this.piece = piece;
        if (piece != null) piece.setSquare(this);
    }

    public int getColumnFromIndex() {
        return col.getIndex();
    }

    public int getRowFromIndex() {
        return row.getIndex();
    }

    private String squareColor() {
        if (this.color == Color.WHITE) return "░";
        else return "  ";
    }

    @Override
    public String toString() {
        if (this.piece == null) return squareColor();
        else return piece.toString();
    }
}
