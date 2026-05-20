package Model.Pieces;

import Model.Board;
import Model.Enum.Color;
import Model.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {
    @Test
    void validMovements() {
        Board board = new Board(true);
        Rook rook = new Rook(Color.WHITE, board);

        board.getSquare(0, 0).setPiece(rook);

        rook.getValidMovements();

        for (Square square : rook.getValidMovements()) {
            int col = square.getColumnFromIndex();
            int row = square.getRowFromIndex();

            assertTrue(col >= 0 && col < 8);
            assertTrue(row >= 0 && row < 8);
        }
    }

    @Test
    void rookCanCaptureEnemyPawn() {
        Board board = new Board(true);
        Rook rookW = new Rook(Color.WHITE, board);
        Pawn pawB = new Pawn(Color.BLACK, board);

        board.getSquare(0, 0).setPiece(rookW);
        board.getSquare(0, 5).setPiece(pawB);

        assertTrue(rookW.getValidMovements().contains(pawB.getSquare()));
    }

    @Test
    void rookCantCaptureEnemyQueen() {
        Board board = new Board(true);
        Rook rookW = new Rook(Color.WHITE, board);
        Pawn pawB = new Pawn(Color.BLACK, board);
        Queen queenB = new Queen(Color.BLACK, board);

        board.getSquare(0, 0).setPiece(rookW);
        board.getSquare(0, 5).setPiece(pawB);
        board.getSquare(0, 4).setPiece(queenB);

        assertFalse(rookW.getValidMovements().contains(queenB.getSquare()));
        assertTrue(rookW.getValidMovements().contains(pawB.getSquare()));
    }
}
