package Controller;

import Model.Enum.Color;
import Model.Enum.Column;
import Model.Enum.GameStatus;
import Model.Enum.Row;
import Model.Game;
import Model.Piece;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Square;
import View.ConsoleView;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {
    private final ConsoleView view;
    private Game game;
    private int moveCounter;
    ArrayList<String> boardImages = new ArrayList<>();

    public GameController(ConsoleView view) {
        this.view = view;
        this.moveCounter = 0;
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
        char colChar = square.charAt(0);
        char rowChar = square.charAt(1);

        int col = Column.valueOf(String.valueOf(colChar)).getIndex();
        int row = Row.rowFromIndex(Character.getNumericValue(rowChar) - 1).getIndex();

        return game.getBoard().getSquare(col, row);
    }

    private void boardSnapshot() {
        StringBuilder image = new StringBuilder(game.getTurn().name());

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                Piece piece = game.getBoard().getSquare(col, row).getPiece();
                if (piece != null)
                    image.append(col).append(row).append(piece.getType().name()).append(piece.getColor().name());
            }
        }
        boardImages.add(image.toString());
    }

    // ----------------------------- MOVE PIECE --------------------------

    public boolean movePiece() {
        view.returnMenu();

        while (true) {
            String originInput = view.readOriginSquare();
            if (originInput.equals("MENU")) return false;

            Square originSquare = squareFromString(originInput);
            Piece originPiece = originSquare.getPiece();

            if (!isValidOrigin(originPiece)) continue;


            while (true) {
                String destInput = view.readDestinationSquare();
                if (destInput.equals("MENU")) return false;
                else if (destInput.equals("BACK")) break;

                Square destinationSquare = squareFromString(destInput);
                if (!isValidDestination(originSquare, destinationSquare, originPiece)) continue;

                executeMove(originSquare, destinationSquare, originPiece);
                return finishGameCheck();
            }
        }
    }

    private void executeMove(Square originSquare, Square destinationSquare, Piece originPiece) {
        Piece destPiece = destinationSquare.getPiece();

        if (destPiece != null) {
            game.addCapturedPiece(destPiece);
            this.moveCounter = -1;
        } else if (originPiece instanceof Pawn) this.moveCounter = -1;

        destinationSquare.setPiece(originPiece);
        originSquare.setPiece(null);

        boardSnapshot();
        game.passTurn();
    }

    private ArrayList<Square> getLegalMoves(Piece piece) {
        ArrayList<Square> legalMoves = new ArrayList<>();

        Square originSquare = piece.getSquare();
        for (Square destSquare : piece.getValidMovements()) {
            Piece capturedPiece = destSquare.getPiece();

            originSquare.setPiece(null);
            destSquare.setPiece(piece);

            King king = King.findKing(piece.getColor(), game.getBoard());
            boolean kingInCheck = king != null && king.isInCheck();

            destSquare.setPiece(capturedPiece);
            originSquare.setPiece(piece);

            if (!kingInCheck) legalMoves.add(destSquare);
        }
        return legalMoves;
    }

    // ----------------------------- VALIDATIONS -----------------------------

    private boolean finishGameCheck() {
        if (rule50Draw()) return false;
        if (tripleRepetitionDraw()) return false;
        return checkGameStateAfterMove();
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

        ArrayList<Square> legalMoves = getLegalMoves(originPiece);

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
            if (enemyKing.hasLegalMoves()) view.showCheck(game.getTurn());
            else {
                GameStatus status = game.getTurn() == Color.WHITE ? GameStatus.BLACK_WINS : GameStatus.WHITE_WINS;
                game.setStatus(status);
                view.showFinishGame(status);
                view.showCheckMate(game.getTurn());
                return false;
            }
        }
        return true;
    }

    // ----------------------------- RESIGN --------------------------

    public boolean resignGame() {
        if (!view.confirmResign()) return false;

        if (game.getTurn() == Color.WHITE) game.setStatus(GameStatus.BLACK_WINS);
        else game.setStatus(GameStatus.WHITE_WINS);

        view.showInfo("Los " + game.getTurn().name() + " se han rendido.");
        view.showFinishGame(game.getStatus());

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
        view.showFinishGame(game.getStatus());

        return true;
    }

    private boolean tripleRepetitionDraw() {
        for (String image1 : boardImages) {
            int rep = 0;

            for (String image2 : boardImages) {
                if (image1.equals(image2)) rep++;
            }

            if (rep >= 3) {
                game.setStatus(GameStatus.DRAW);
                view.showFinishGame(game.getStatus());
                return true;
            }
        }
        return false;
    }

    private boolean rule50Draw() {
        this.moveCounter++;

        if (this.moveCounter >= 100) {
            game.setStatus(GameStatus.DRAW);
            view.showFinishGame(game.getStatus());
            return true;
        }
        return false;
    }


//    private boolean stalemateDraw() {
//        //TODO
//        return true;
//    }
//
//    public boolean insufficientMaterialDraw() {
//        //TODO
//        return true;
//    }

    // ----------------------------- GAME PERSISTENT --------------------------

    public void saveGame() {
        if (game == null) {
            view.showError("No hay ninguna partida que guardar.");
            return;
        }

        String fileName = view.readFileName();

        if (GamePersistent.gameExists(fileName)) {
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

        if (!GamePersistent.gameExists(fileName)) {
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