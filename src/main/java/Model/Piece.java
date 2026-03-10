package Model;

import Model.Enum.Color;
import Model.Pieces.PieceType;

import java.util.ArrayList;

public abstract class Piece {
    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract PieceType getType();

    public abstract ArrayList<Square> getValidMovements();

    @Override
    public String toString() {
        return "" + getType().getSymbol(this.color);
    }
}
