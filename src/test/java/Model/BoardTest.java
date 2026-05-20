package Model;

import Model.Enum.Color;
import Model.Pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void emptyBoard_hasNoPieces() {
        Board board = new Board(true);

        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                assertNull(board.getSquare(col, row).getPiece());
            }
        }

    }

    @Test
    void boardPiece_isWhiteRook(){
        Board board = new Board(false);
        Piece piece = board.getSquare(0,0).getPiece();

        assertNotNull(piece);
        assertInstanceOf(Rook.class, piece);
        assertEquals(Color.WHITE, piece.getColor());
    }
}
