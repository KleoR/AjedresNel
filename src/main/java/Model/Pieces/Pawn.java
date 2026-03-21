package Model.Pieces;

import Model.Board;
import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Color color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        Square square = super.getSquare();
        if (square == null) return new ArrayList<>();

        ArrayList<Square> validMoves = new ArrayList<>();
        int forward = super.getColor() == Color.WHITE ? 1 : -1;
        int startRow = super.getColor() == Color.WHITE ? 1 : 6;
        int col = square.getColumnFromIndex();
        int row = square.getRowFromIndex();

        addForwardMoves(row, forward, col, validMoves, startRow);
        addDiagonalCaptures(row, forward, col, validMoves);

        return validMoves;
    }

    private void addDiagonalCaptures(int row, int forward, int col, ArrayList<Square> validMoves) {
        // una casilla diagonal
        int r1 = row + forward;
        for (int dCol : new int[]{-1, 1}) {
            int colDest = col + dCol;
            if (withinLimits(colDest, r1)) {
                Square squareDest = this.board.getSquare(colDest, r1);
                if (squareDest.getPiece() != null && squareDest.getPiece().getColor() != super.getColor()) {
                    validMoves.add(squareDest);
                }
            }
        }
    }

    private void addForwardMoves(int row, int forward, int col, ArrayList<Square> validMoves, int startRow) {
        // una casilla adelante
        int r1 = row + forward;
        if (!withinLimits(col, r1)) return;
        Square s1 = this.board.getSquare(col, r1);
        if (s1.getPiece() == null) {
            validMoves.add(s1);

            // dos casillas adelante
            if (row == startRow) {
                int r2 = row + forward * 2;
                Square s2 = this.board.getSquare(col, r2);
                if (s2.getPiece() == null) validMoves.add(s2);
            }
        }
    }
}
