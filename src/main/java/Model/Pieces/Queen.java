package Model.Pieces;

import Model.Board;
import Model.Enum.Color;

public class Queen extends SlidingPiece {
    public Queen(Color color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    @Override
    protected int[][] getDirections() {
        return new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    }

}
