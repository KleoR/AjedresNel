package Model.Pieces;

import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public PieceType getType() {
        return PieceType.Pawn;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        return null;
    }

}
