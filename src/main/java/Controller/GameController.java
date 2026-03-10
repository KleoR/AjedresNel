package Controller;

import Model.Board;
import Model.Enum.Column;
import Model.Enum.Row;
import Model.Game;
import Model.Piece;
import Model.Square;
import View.ConsoleView;

public class GameController {
    private final ConsoleView view;
    private final MenuController menu;
    private Game game;

    public GameController(ConsoleView view, MenuController menu) {
        this.view = view;
        this.menu = menu;
    }

    public void showBoard(){
        view.showBoard(this.game.getBoard());
        view.showTurn(game.getTurn());
    }

    public void createNewGame() {
        this.game = new Game();
        showBoard();
    }

    public void movePiece() {
        Square originSquare = squareFromString(view.readOriginSquare());
        Piece originPiece = originSquare.getPiece();

        if (originPiece == null) {
            view.showError("No hay hay que mover.");
            return;
        }

        if(game.getTurn() != originPiece.getColor()){
            view.showError("No puedes mover una pieza enemiga.");
            return;
        }

        Square destinationSquare = squareFromString(view.readDestinationSquare());
        Piece destinationPiece = destinationSquare.getPiece();

        if(originSquare == destinationSquare){
            view.showError("La casilla de destino es la misma que la de origen. Vuelva a introducir las casillas.");
            return;
        }

        if (destinationPiece != null) {
            if (originPiece.getColor() == destinationPiece.getColor()) {
                view.showError("Ya hay una pieza aliada en esa casilla");
                return;
            }
        }

        destinationSquare.setPiece(originPiece);
        originSquare.setPiece(null);

        game.passTurn();
    }

    

    public Square squareFromString(String square) {
        char column = square.charAt(0);
        char raw = square.charAt(1);

        int col = Column.valueOf(String.valueOf(column)).getIndex();
        int row = Row.rowFromIndex(Character.getNumericValue(raw) - 1).getIndex();

        return game.getBoard().getSquare(col, row);
    }


}
