package Model.Pieces;

import Model.Enum.Color;

public enum PieceType {
    KING(   '♔', '♚'),
    QUEEN(  '♕', '♛'),
    ROOK(   '♖', '♜'),
    BISHOP( '♗', '♝'),
    KNIGHT( '♘', '♞'),
    PAWN(   '♙', '♟');

    private final char whiteSymbol;
    private final char blackSymbol;

    PieceType(char whiteSymbol, char blackSymbol) {
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public char getSymbol(Color c) {
        if (c == Color.WHITE) return whiteSymbol;
        else return blackSymbol;
    }
}
