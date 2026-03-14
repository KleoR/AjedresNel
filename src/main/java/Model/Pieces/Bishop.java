package Model.Pieces;

import Model.Board;
import Model.Enum.Color;

public class Bishop extends SlidingPiece {
    public Bishop(Color color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.Bishop;
    }

    @Override
    protected int[][] getDirections() {
        return new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    }
}
