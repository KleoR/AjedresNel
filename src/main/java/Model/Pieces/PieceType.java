package Model.Pieces;

import Model.Enum.Color;

public enum PieceType {
    PAWN(   '♙', '\uED64'),
    BISHOP( '♗', '\uED60'),
    KING(   '♔', '\uED62'),
    KNIGHT( '♘', '\uED63'),
    QUEEN(  '♕', '\uED65'),
    ROOK(   '♖', '\uED66');

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
