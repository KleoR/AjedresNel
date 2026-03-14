package Model.Pieces;

import Model.Board;
import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(Color color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return null;
    }

    protected abstract int[][] getDirections();

    @Override
    public ArrayList<Square> getValidMovements() {
        Square square = getSquare();
        if (square == null) return new ArrayList<>();

        ArrayList<Square> validMoves = new ArrayList<>();

        int row = square.getRowFromIndex();
        int col = square.getColumnFromIndex();

        for (int[] d : getDirections() ){
            int dCol = col + d[0];
            int dRow = row + d[1];

            while (withinLimits(dCol, dRow)){
                Square dSquare = board.getSquare(dCol, dRow);
                if (dSquare.getPiece() == null) validMoves.add(dSquare);
                else {
                    if (dSquare.getPiece().getColor() != getColor()) validMoves.add(dSquare);
                    break;
                }
                dCol += d[0];
                dRow += d[1];
            }
        }
        return validMoves;
    }
}
