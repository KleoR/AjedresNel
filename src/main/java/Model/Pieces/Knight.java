package Model.Pieces;

import Model.Board;
import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Color color, Board board) {
        super(color, board);
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
