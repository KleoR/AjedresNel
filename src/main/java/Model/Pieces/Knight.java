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
        Square square = super.getSquare();
        if (square == null) return new ArrayList<>();

        ArrayList<Square> validMoves = new ArrayList<>();
        int col = square.getColumnFromIndex();
        int row = square.getRowFromIndex();
        int[][] moveOffsets = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

        for (int[] moves : moveOffsets) {
            int dCol = col + moves[0];
            int dRow = row + moves[1];

            if (!withinLimits(dCol, dRow)) continue;

            Square dSquare = board.getSquare(dCol, dRow);

            if (dSquare.getPiece() == null || dSquare.getPiece().getColor() != getColor()) {
                validMoves.add(dSquare);
            }
        }

        return validMoves;
    }
}
