package Model.Pieces;

import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.Knight;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        return null;
    }
}
