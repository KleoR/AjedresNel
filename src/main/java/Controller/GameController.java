package Controller;

import Model.Enum.Color;
import Model.Enum.Column;
import Model.Enum.GameStatus;
import Model.Enum.Row;
import Model.Game;
import Model.Piece;
import Model.Pieces.King;
import Model.Square;
import View.ConsoleView;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {
    private final ConsoleView view;
    private Game game;

    public GameController(ConsoleView view) {
        this.view = view;
    }

    public void showBoard() {
        view.showTurn(game.getTurn());
        view.showCapturedPieces(game.getCapturedPieces());
        view.showBoard(this.game.getBoard());
    }

    public void createNewGame() {
        this.game = new Game();
        showBoard();
    }

    // -----------------------------  UTILS --------------------------

    public Square squareFromString(String square) {
        char column = square.charAt(0);
        char raw = square.charAt(1);

        int col = Column.valueOf(String.valueOf(column)).getIndex();
        int row = Row.rowFromIndex(Character.getNumericValue(raw) - 1).getIndex();

        return game.getBoard().getSquare(col, row);
    }


    // ----------------------------- MOVE PIECE --------------------------

    public boolean movePiece() {
        String originInput = view.readOriginSquare();
        if (originInput.equals("MENU")) return false;

        Square originSquare = squareFromString(originInput);
        Piece originPiece = originSquare.getPiece();

        if (!isValidOrigin(originPiece)) return true;

        Square destinationSquare = squareFromString(view.readDestinationSquare());
        if (!isValidDestination(originSquare, destinationSquare, originPiece)) return true;

        executeMove(originSquare, destinationSquare, originPiece);
        return checkGameStateAfterMove();
    }

    private void executeMove(Square originSquare, Square destinationSquare, Piece originPiece) {
        Piece destPiece = destinationSquare.getPiece();

        if (destPiece != null) game.addCapturedPiece(destPiece);

        destinationSquare.setPiece(originPiece);
        originSquare.setPiece(null);
        game.passTurn();
    }

    private boolean isValidOrigin(Piece originPiece) {
        if (originPiece == null) {
            view.showError("No hay pieza que mover.");
            return false;
        }

        if (game.getTurn() != originPiece.getColor()) {
            view.showError("No puedes mover una pieza enemiga.");
            return false;
        }

        return true;
    }

    private boolean isValidDestination(Square originSquare, Square destinationSquare, Piece originPiece) {
        if (originSquare == destinationSquare) {
            view.showError("La casilla de destino es la misma que la de origen. Vuelva a introducir las casillas.");
            return false;
        }

        ArrayList<Square> legalMoves = originPiece.getValidMovements();

        if (!legalMoves.contains(destinationSquare)) {
            view.showError("No es un movimiento legal.");
            return false;
        }

        return true;
    }

    private boolean checkGameStateAfterMove() {
        King enemyKing = King.findKing(game.getTurn(), game.getBoard());

        assert enemyKing != null;
        if (enemyKing.isInCheck()) {
            if (!enemyKing.hasLegalMoves()) {
                GameStatus status = game.getTurn() == Color.WHITE ? GameStatus.BLACK_WINS : GameStatus.WHITE_WINS;
                game.setStatus(status);
                view.finishGame(status);
                view.showCheckMate(game.getTurn());
                return false;

            } else view.showCheck(game.getTurn());
        }
        return true;
    }

    private ArrayList<Square> getLegalMoves(Piece piece) { //Todo
        return null;
    }

    // ----------------------------- RESIGN --------------------------

    public boolean resignGame() {
        if (!view.confirmResign()) return false;

        if (game.getTurn() == Color.WHITE) game.setStatus(GameStatus.BLACK_WINS);
        else game.setStatus(GameStatus.WHITE_WINS);

        view.showInfo("Los " + game.getTurn().name() + " se han rendido.");
        view.finishGame(game.getStatus());

        return true;
    }

    // ----------------------------- DRAW --------------------------

    public boolean offerDraw() {
        if (!view.confirmDrawOffer()) return false;

        if (!view.acceptDrawOffer()) {
            view.showInfo("El rival NO acepta las tablas");
            return false;
        }

        game.setStatus(GameStatus.DRAW);
        view.finishGame(game.getStatus());

        return true;
    }

    public boolean rule50Draw(){
        //TODO
        return true;
    }

    public boolean copyMovesDraw(){
        //TODO
        return true;
    }

    public boolean stalemateDraw(){
        //TODO
        return true;
    }

    public boolean insufficientMaterialDraw(){
        //TODO
        return true;
    }

    // ----------------------------- GAME PERSISTENT --------------------------

    public void saveGame() {
        if (game == null) {
            view.showError("No hay ninguna partida que guardar.");
            return;
        }

        String fileName = view.readFileName();

        if (GamePersistent.gameExist(fileName)) {
            if (view.confirmOverwriteFile()) view.showInfo("El archivo [ " + fileName + " ] sera sobrescrito");
            else {
                view.showInfo("Vuelve a guardar con otro nombre.");
                return;
            }
        }

        try {
            GamePersistent.save(game, fileName);
            view.showSuccess("Partida [ " + fileName + " ] guardad correctamente.");
        } catch (IOException e) {
            view.showError("Error al guardar la partida");
        }
    }

    public boolean loadGame() {
        String fileName = view.readFileName();

        if (!GamePersistent.gameExist(fileName)) {
            view.showError("No existe ningún juego con el nombre [ " + fileName + " ].");
            return false;
        }

        try {
            this.game = GamePersistent.load(fileName);
            view.showSuccess("La partida [ " + fileName + " ] ha sido cargada con éxito.");
            showBoard();
            return true;
        } catch (IOException e) {
            view.showError("Error al cargar la partida");
            return false;
        }
    }
}
