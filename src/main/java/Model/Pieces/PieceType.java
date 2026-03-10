package Model.Pieces;

import Model.Enum.Color;

public enum PieceType {
    Pawn('♟', '♙'),
    Bishop('♝', '♗'),
    King('♚', '♔'),
    Knight('♞', '♘'),
    Queen('♛', '♕'),
    Rook('♜', '♖');

    private char whiteSymbol;
    private char blackSymbol;

    PieceType(char whiteSymbol, char blackSymbol) {
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public char getSymbol(Color c) {
        if(c == Color.White) return whiteSymbol;
        else return blackSymbol;
    }
}
