package Controller;

import Model.Enum.Column;
import Model.Enum.Row;
import Model.Game;
import Model.Piece;
import Model.Square;
import View.ConsoleView;

import java.util.ArrayList;

public class GameController {
    private final ConsoleView view;
    private final MenuController menu;
    private Game game;

    public GameController(ConsoleView view, MenuController menu) {
        this.view = view;
        this.menu = menu;
    }

    public void showBoard() {
        view.showTurn(game.getTurn());
        view.showBoard(this.game.getBoard());
    }

    public void createNewGame() {
        this.game = new Game();
        showBoard();
    }

    public boolean movePiece() {
        String s = view.readOriginSquare();
        if (s.equals("MENU")) return false;

        Square originSquare = squareFromString(s);
        Piece originPiece = originSquare.getPiece();

        if (originPiece == null) {
            view.showError("No hay hay que mover.");
            return true;
        }

        if (game.getTurn() != originPiece.getColor()) {
            view.showError("No puedes mover una pieza enemiga.");
            return true;
        }

        Square destinationSquare = squareFromString(view.readDestinationSquare());
        Piece destinationPiece = destinationSquare.getPiece();

        if (originSquare == destinationSquare) {
            view.showError("La casilla de destino es la misma que la de origen. Vuelva a introducir las casillas.");
            return true;
        }

        ArrayList<Square> legalMoves = originPiece.getValidMovements();

        if (!legalMoves.contains(destinationSquare)) {
            view.showError("No es un movimiento legal.");
            return true;
        }

        destinationSquare.setPiece(originPiece);
        originSquare.setPiece(null);

        game.passTurn();
        return true;
    }


    public Square squareFromString(String square) {
        char column = square.charAt(0);
        char raw = square.charAt(1);

        int col = Column.valueOf(String.valueOf(column)).getIndex();
        int row = Row.rowFromIndex(Character.getNumericValue(raw) - 1).getIndex();

        return game.getBoard().getSquare(col, row);
    }


}
