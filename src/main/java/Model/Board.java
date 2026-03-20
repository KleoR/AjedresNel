package Model;

import Model.Enum.Color;
import Model.Pieces.*;

public class Board {
    Square[][] squares = new Square[8][8];

    public Board(boolean empty) {
        for (int col = 0; col < 8; col++) for (int row = 0; row < 8; row++) squares[col][row] = new Square(col, row);
        if (!empty) setupPieces();
    }


    public Square getSquare(int col, int row) {
        return squares[col][row];
    }

    public void setupPieces() {
        squares[0][0].setPiece(new Rook(Color.White, this));
        squares[1][0].setPiece(new Knight(Color.White, this));
        squares[2][0].setPiece(new Bishop(Color.White, this));
        squares[3][0].setPiece(new Queen(Color.White, this));
        squares[4][0].setPiece(new King(Color.White, this));
        squares[5][0].setPiece(new Bishop(Color.White, this));
        squares[6][0].setPiece(new Knight(Color.White, this));
        squares[7][0].setPiece(new Rook(Color.White, this));

        for (int i = 0; i < 8; i++) squares[i][1].setPiece(new Pawn(Color.White, this));

        squares[0][7].setPiece(new Rook(Color.Black, this));
        squares[1][7].setPiece(new Knight(Color.Black, this));
        squares[2][7].setPiece(new Bishop(Color.Black, this));
        squares[3][7].setPiece(new Queen(Color.Black, this));
        squares[4][7].setPiece(new King(Color.Black, this));
        squares[5][7].setPiece(new Bishop(Color.Black, this));
        squares[6][7].setPiece(new Knight(Color.Black, this));
        squares[7][7].setPiece(new Rook(Color.Black, this));

        for (int i = 0; i < 8; i++) squares[i][6].setPiece(new Pawn(Color.Black, this));
    }
}
