package Model.Pieces;

import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.King;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        return null;
    }
}
