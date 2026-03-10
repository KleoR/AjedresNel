package Model.Pieces;

import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.Bishop;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        return null;
    }
}
