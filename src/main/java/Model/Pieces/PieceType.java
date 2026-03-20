package Model.Pieces;

import Model.Enum.Color;

public enum PieceType {
    PAWN('♟', '♙'),
    BISHOP('♝', '♗'),
    KING('♚' , '♔'),
    KNIGHT('♞', '♘'),
    QUEEN('♛', '♕'),
    ROOK('♜', '♖');

    private char whiteSymbol;
    private char blackSymbol;

    PieceType(char whiteSymbol, char blackSymbol) {
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public char getSymbol(Color c) {
        if (c == Color.White) return whiteSymbol;
        else return blackSymbol;
    }
}
