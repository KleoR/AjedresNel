package View;

import Controller.Enum.GameMenuOption;
import Controller.Enum.MainMenuOption;
import Model.Board;
import Model.Enum.Color;
import Model.Enum.GameStatus;
import Model.Piece;

import java.util.ArrayList;

public class ConsoleView {
    private final ConsoleInput input = new ConsoleInput(this);

    public String trueColor(Color color) {
        if (color == Color.WHITE) return "BLANCO";
        else return "NEGRO";
    }

    // --------------------- SHOW ----------------------

    public void showTitle() {
        System.out.println(GameMessage.GAME_TITLE.get());
    }

    public void showExit() {
        System.out.print(GameMessage.GAME_EXIT.get());
    }

    public void showCheck(Color color) {
        System.out.printf(GameMessage.GAME_CHECK.get(), trueColor(color));
    }

    public void showCheckMate(Color color) {
        System.out.printf(GameMessage.GAME_CHECKMATE.get(), trueColor(color));
    }

    public void showFinishGame(GameStatus gs) {
        System.out.printf(GameMessage.GAME_FINISH.get(), gs.getMessage());
    }

    public void showTurn(Color color) {
        System.out.printf(GameMessage.GAME_TURN.get(), trueColor(color));
    }

    public void showError(String mjs) {
        System.out.println("\n [!] ERROR: " + mjs + " [!] \n");
    }

    public void showSuccess(String mjs) {
        System.out.println("\n [✓] ÉXITO: " + mjs + " [✓] \n");
    }

    public void showInfo(String mjs) {
        System.out.println("\n [!] INFO: " + mjs + " [!] \n");
    }

    // --------------------- READ ----------------------

    public void returnMenu(){
        System.out.println("◇ Escribe [ MENU ] para volver al menu ◇ y [BACK] para reelegir origen ◇\n");
    }

    public String readOriginSquare() {
        return input.readSquare("♦ Escribe la casilla de origen >> ");
    }

    public String readDestinationSquare() {
        return input.readSquare("♦ Escribe la casilla de destino >> ");
    }

    public String readFileName() {
        return input.readFileName();
    }

    // --------------------- CONFIRM -----------------------

    public boolean confirmOverwriteFile() {
        System.out.print("\n      ◇ Ya existe una partida con ese nombre. ¿Quieres sobrescribirla? [ S / N ] ◇\n >> ");
        return input.yesOrNo();
    }

    public boolean confirmResign() {
        System.out.print("\n ◇ ¿Quieres rendirte? [ S / N ] ◇\n >> ");
        return input.yesOrNo();
    }

    public boolean confirmDrawOffer() {
        System.out.print("\n ◇ ¿Quieres proponer tablas? [ S / N ] ◇\n >> ");
        return input.yesOrNo();
    }

    public boolean acceptDrawOffer() {
        System.out.print("\n ◇ EL otro jugador a propuesto tablas ¿Quieres aceptarlas? [ S / N ] ◇\n >> ");
        return input.yesOrNo();
    }

    // ------------------------- GAME OPTIONS -------------------------------

    public GameMenuOption getGameMenuOption() {
        System.out.print(GameMessage.GAME_GAME_MENU.get());
        return GameMenuOption.fromIndex(input.readInt(GameMenuOption.values().length - 1));
    }

    public MainMenuOption getMainMenuOption() {
        System.out.print(GameMessage.GAME_MAIN_MENU.get());
        return MainMenuOption.fromIndex(input.readInt(MainMenuOption.values().length - 1));
    }

    // ------------------------------- TABLERO ----------------------
    public void showBoard(Board b) {
        System.out.printf("%n%27s◇ ━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━━┯━━ ◇%n", "");

        for (int row = 7; row >= 0; row--) {
            System.out.printf("%25s" + "%d ┃ ", "", (row + 1));

            for (int col = 0; col < 8; col++) System.out.print(b.getSquare(col, row).toString() + (col < 7 ? " │ " : " ┃"));
            if (row > 0) System.out.printf("%n%27s┠───┼───┼───┼───┼───┼───┼───┼───┨%n", "");
        }

        System.out.printf("%n%27s◇ ━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━━┷━━ ◇ ", "");
        System.out.printf("%n%27s  a   b   c   d   e   f   g   h%n", "");
    }


    public void showCapturedPieces(ArrayList<Piece> pCaptured) {
        if (pCaptured.isEmpty()) return;

        ArrayList<Piece> whitePieces = new ArrayList<>();
        ArrayList<Piece> blackPieces = new ArrayList<>();

        for (Piece piece : pCaptured) {
            if (piece.getColor() == Color.WHITE) whitePieces.add(piece);
            else blackPieces.add(piece);
        }

        System.out.print("CAPTURAS: ");

        for (Piece piece : whitePieces) System.out.print(piece.getType().getSymbol(Color.WHITE) + " ");

        System.out.print("| ");

        for (Piece piece : blackPieces) System.out.print(piece.getType().getSymbol(Color.BLACK) + " ");
    }

}

