package Model.Pieces;

import Model.Board;
import Model.Enum.Color;

public class Rook extends SlidingPiece {

    public Rook(Color color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.Rook;
    }

    @Override
    protected int[][] getDirections() {
        return new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    }
}
