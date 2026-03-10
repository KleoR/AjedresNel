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
        return PieceType.Pawn;
    }

    @Override
    public ArrayList<Square> getValidMovements() {
        Square square = super.getSquare();
        if (square == null) return new ArrayList<>();

        ArrayList<Square> validMoves = new ArrayList<>();
        int forward = super.getColor() == Color.White ? 1 : -1;
        int starRow = super.getColor() == Color.White ? 1 : 6;
        int col = square.getColumnFromIndex();
        int row = square.getRowFromIndex();


        // una casilla adelante
        int r1 = row + forward;
        Square s1 = this.board.getSquare(col, r1);
        if (s1.getPiece() == null) {
            validMoves.add(s1);
            if (row == starRow) {
                int r2 = row + forward * 2;
                Square s2 = this.board.getSquare(col, r2);
                if (s2.getPiece() == null) validMoves.add(s2);
            }
        }


        return validMoves;
    }


}
