package Model.Pieces;

import Model.Board;
import Model.Enum.Color;
import Model.Piece;
import Model.Square;

import java.util.ArrayList;

public class King extends Piece {
    public King(Color color, Board board) {
        super(color, board);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        Square square = super.getSquare();
        if (square == null) return new ArrayList<>();

        ArrayList<Square> validMoves = new ArrayList<>();
        int col = square.getColumnFromIndex();
        int row = square.getRowFromIndex();
        int[][] moveOffsets = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

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

    public boolean isInCheck() {
        Square kingSquare = this.getSquare();
        Color opponent = this.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE;

        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Piece piece = board.getSquare(col, row).getPiece();

                if (piece == null) continue;
                if (piece.getColor() == opponent && piece.getValidMovements().contains(kingSquare))
                    return true;
            }
        }
        return false;
    }

    public static King findKing(Color color, Board board) {

        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Piece piece = board.getSquare(col, row).getPiece();

                if (piece instanceof King king && king.getColor() == color)
                    return king;
            }
        }

        return null;
    }

    public boolean hasLegalMoves() {

        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Piece piece = board.getSquare(col, row).getPiece();

                if (piece == null || piece.getColor() != getColor()) continue;

                Square originSquare = piece.getSquare();
                for (Square destSquare : piece.getValidMovements()) {
                    // Simular movement
                    Piece capturedPiece = destSquare.getPiece();
                    originSquare.setPiece(null);
                    destSquare.setPiece(piece);
                    boolean stillInCheck = isInCheck();

                    destSquare.setPiece(capturedPiece);
                    originSquare.setPiece(piece);

                    if (!stillInCheck) return true;
                }
            }
        }

        return false;
    }

}
