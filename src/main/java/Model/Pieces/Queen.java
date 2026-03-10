package Model.Pieces;

import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.Queen;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        return null;
    }
}
