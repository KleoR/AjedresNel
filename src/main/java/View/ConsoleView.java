package View;

import Model.Board;
import Model.Enum.Color;
import Model.Enum.GameStatus;
import Model.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner sc = new Scanner(System.in);

    public void showTitle() {
        System.out.println("""
                
                ◇ ────────────────────────────────────────── ◇
                
                               ▒▒┐        ▒┐▒▒▒┐▒▒▒┐  ▒▒▒┐  ▒▒▒┐ ▒▒▒▒┐
                             ▒┌─▒┐      ▒│▒┌─┘▒┌─▒┐▒┌─▒┐▒┌─┘ └─▒┌┘
                             ▒▒▒▒│      ▒│▒▒┐  ▒│  ▒│▒▒▒┌┘▒▒┐     ▒┌┘
                             ▒┌─▒│▒    ▒│▒┌┘  ▒│  ▒│▒┌─▒┐▒┌┘   ▒┌┘
                             ▒│  ▒│└▒▒┌┘▒▒▒┐▒▒▒┌┘▒│  ▒│▒▒▒┐▒▒▒▒┐
                             └┘  └┘  └─┘  └──┘└──┘  └┘  └┘└──┘└───┘
                
                ◇ ──────────────── ◇ AJEDREZ V1.0 ◇ ──────────────── ◇
                """);
    }

//    public void showTitle2() {
//        System.out.println("""
//                ◇ ────────────────────────────────────────── ◇
//                            ______      _____  ________  _______   _______   ________  ________
//                           /      \\    /     |/        |/       \\ /       \\ /        |/       |
//                          /▒▒▒  |   ▒▒  |▒▒▒▒/ ▒▒▒   |▒▒▒   |▒▒▒▒/ ▒▒▒▒/
//                          ▒    ▒ | __   ▒ |▒    |   ▒ |  ▒ |▒    ▒< ▒    |      /▒/
//                          ▒▒▒▒ |/  |  ▒ |▒▒ /    ▒ |  ▒ |▒▒▒   |▒▒ /      /▒/
//                          ▒ |  ▒ |▒ \\__▒ |▒ |_____ ▒ |__▒ |▒ |  ▒ |▒ |_____  /▒/____
//                          ▒/   ▒ / ▒▒▒ / ▒▒▒▒ /▒▒▒ /  ▒ /  ▒/ ▒▒▒▒/  ▒▒▒▒/
//
//                ◇ ──────────────── ◇ AJEDREZ V1.0 ◇ ──────────────── ◇""");
//        System.out.println("""
//                ◇ ────────────────────────────────────────── ◇
//
//                        ▁▁▁▁           ▁▁    ▁▁▁▁▁▁    ▁▁▁▁▁▁      ▁▁▁▁▁▁      ▁▁▁▁▁▁     ▁▁▁▁▁▁▁▁
//                      ▁|▄▀▄▀|▁        |▄▀|  |▄▀▄▀▄▀|  |▄▀▄▀▄▀|▁   |▄▀▄▀▄▀|▁   |▄▀▄▀▄▀|  |▀▄▀▄▀▄▀▄▀|
//                     |▄▀|▁▁|▄▀|       |▄▀|  |▄▀|▔▔▔   |▄▀|▔▔|▄▀|  |▄▀|▔▔|▄▀|  |▄▀|▔▔▔     ▔▔▔|▄▀|▔
//                     |▄▀▄▀▄▀▄▀|  ▁▁   |▄▀|  |▄▀▄▀|    |▄▀|  |▄▀|  |▄▀▄▀▄▀|▁   |▄▀▄▀|      ▁|▄▀|▔
//                     |▄▀|▔▔|▄▀| |▄▀|  |▄▀|  |▄▀|▁▁▁   |▄▀|▁▁|▄▀|  |▄▀|▔▔|▄▀|  |▄▀|▁▁▁   ▁|▄▀|▁▁▁▁
//                     |▄▀|  |▄▀|  ▔|▄▀▄▀|▔   |▄▀▄▀▄▀|  |▄▀▄▀▄▀|▔   |▄▀|  |▄▀|  |▄▀▄▀▄▀| |▄▀▄▀▄▀▄▀▄|
//                      ▔▔    ▔▔     ▔▔▔▔      ▔▔▔▔▔▔    ▔▔▔▔▔▔      ▔▔    ▔▔    ▔▔▔▔▔▔   ▔▔▔▔▔▔▔▔▔
//
//                ◇ ──────────────── ◇ AJEDREZ V1.0 ◇ ──────────────── ◇""");
//    }

    public void showBoard(Board b) {
        String sp = "                           ";
        System.out.println("\n" + sp + " ◇  ┯━┯━┯━┯━┯━┯━┯  ◇");

        for (int row = 7; row >= 0; row--) {
            System.out.print(sp + (row + 1) + (row != 7 && row != 0 ? "┃" : "  "));
            columnDraw(b, row);
        }
        System.out.println(sp + "   a   b   c   d   e   f   g   h\n");
    }

    public void columnDraw(Board b, int row) {
        String sp = "                            ";
        for (int col = 0; col < 8; col++) {
            String piece = b.getSquare(col, row).toString();
            System.out.print(piece + (col != 7 ? "│" : ""));
        }

        if (row != 0) System.out.println((row != 7 ? "┃ " : "") + "\n" + sp + "┠─┼─┼─┼─┼─┼─┼─┼─┨");
        else System.out.println("\n" + sp + "◇  ┷━┷━┷━┷━┷━┷━┷  ◇");
    }

    public void showTurn(Color color) {
        if (color == Color.WHITE) System.out.println("\n                      ⌜ ── ◇ ── TURNO: ♔ BLANCO ── ◇ ── ⌟");
        else System.out.println("\n                      ⌜ ── ◇ ── TURNO: ♚ NEGRO ── ◇ ── ⌟");
    }

    public void showExit() {
        System.out.print(
                """
                        
                                    ┌───────────────────────────────┐
                                    │ ♖   ♘   ♗    DESCONECTANDO DEL TABLERO...    ♗   ♘   ♖ │
                                    └───────────────────────────────┘
                                                         ◇ ¡Gracias por jugar! ◇
                        """);
    }

    public void showMainMenu() {
        System.out.print("\n              ◇ ─ [1] CREAR PARTIDA · [2] CARGAR PARTIDA · [0] SALIR ─ ◇\n >> ");
    }

    public void showGameMenu() {
        System.out.print("\n◇ ─ [1] MOVER PIEZA · [2] RENDIRSE · [3] TABLAS · [4] GUARDAR PARTIDA · [0] VOLVER ─ ◇ \n >> ");
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

    public String readOriginSquare() {
        System.out.println("                             ◇ Escribe [MENU] para volver ◇\n");
        return readSquare("♦ Escribe la casilla de origen >> ");
    }

    public String readDestinationSquare() {
        return readSquare("♦ Escribe la casilla de destino >> ");
    }

    public String readSquare(String msj) {
        while (true) {
            System.out.print(msj);
            String square = sc.nextLine().toUpperCase().trim();
            if (square.equals("MENU")) return square;

            if (square.matches("^[A-H][1-8]$")) return square;
            showError("Casilla invalida. Vuelve a introducir la casilla. Escribe [ MENU ] para volver al menú.");
        }
    }

    public int readInt(int max) {
        while (true) {
            if (!sc.hasNextInt()) {
                showError("Numero invalido. Vuelve a introducir un numero.");
                sc.nextLine();
            } else {
                int num = sc.nextInt();
                sc.nextLine();
                if (num >= 0 && num <= max) return num;
                showError("Numero fuera de rango. Vuelve a introducir un numero.");
            }
        }
    }

    public String readFileName() {
        while (true) {
            System.out.print("\n                  ◇ Escribe le nombre para el fichero de la partida ◇\n >> ");
            String gameName = sc.nextLine().trim();

            if (gameName.matches("^[A-Za-z0-9_-]{1,20}$")) return gameName;
            showError("Formato de nombre no valido. Vuelva a introducirlo.");
        }
    }

    public boolean confirmOverwriteFile() {
        System.out.print("\n      ◇ Ya existe una partida con ese nombre. ¿Quieres sobrescribirla? [ S / N ] ◇\n >> ");
        return yesOrNo();
    }

    public boolean confirmResign() {
        System.out.print("\n ◇ ¿Quieres rendirte? [ S / N ] ◇\n >> ");
        return yesOrNo();
    }

    public boolean confirmDrawOffer() {
        System.out.print("\n ◇ ¿Quieres proponer tablas? [ S / N ] ◇\n >> ");
        return yesOrNo();
    }
    public boolean acceptDrawOffer() {
        System.out.print("\n ◇ EL otro jugador a propuesto tablas ¿Quieres aceptarlas? [ S / N ] ◇\n >> ");
        return yesOrNo();
    }

    private boolean yesOrNo() {
        while (true) {
            String option = sc.nextLine().trim().toUpperCase();
            if (option.matches("^[SN]$")) return option.equals("S");
            showError("Opción invalida, tiene que se [ S ] para si y [ N ] para no.");
        }
    }

    public void finishGame(GameStatus gs) {
        String text = " ";

        switch (gs) {
            case DRAW -> text = "   ♔  ⚔  EMPATE  ⚔  ♚";
            case BLACK_WINS -> text = "¡ VICTORIA PARA EL NEGRO !\n";
            case WHITE_WINS -> text = "¡ VICTORIA PARA EL BLANCO !\n";
        }

        System.out.print("   ⌜ ─────────────────── ◇ ─────────────────── ⌟\n");
        System.out.print("                                " + text);
        System.out.print("   ⌞ ─────────────────── ◇ ─────────────────── ⌟\n");
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

    public void showCheck(Color color){
        System.out.println("EL rey " + color.name() + "esta en Jaque.");
    }

    public void showCheckMate(Color color){
        System.out.println("EL rey " + color.name() + "esta en JaqueMate.");
    }
}

