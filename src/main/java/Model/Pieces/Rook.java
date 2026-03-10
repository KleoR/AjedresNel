package Model.Pieces;

import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.Rook;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        return null;
    }
}
