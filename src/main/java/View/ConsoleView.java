package View;

import Model.Board;
import Model.Enum.Color;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner sc = new Scanner(System.in);

    public void showBoard(Board b) {
        System.out.println("\n    ◇  ┯━┯━┯━┯━┯━┯━┯  ◇");

        for (int row = 7; row >= 0; row--) {
            System.out.print("  " + (row + 1) + (row != 7 && row != 0 ? " ┃" : "   "));
            columnDraw(b, row);
        }
        System.out.println("       a   b   c   d   e   f   g   h\n");
    }

    public void columnDraw(Board b, int row) {
        for (int col = 0; col < 8; col++) {
            String piece = b.getSquare(col, row).toString();
            System.out.print(piece + (col != 7 ? "│" : ""));
        }

        if (row != 0) System.out.println((row != 7 ? "┃ " : "") + "\n    ┠─┼─┼─┼─┼─┼─┼─┼─┨");
        else System.out.println("\n    ◇  ┷━┷━┷━┷━┷━┷━┷  ◇");
    }

    public void showTurn(Color color) {
        if (color == Color.White) System.out.println("\n ⌜ ── ◇ ── TURNO: ♔ BLANCO ── ◇ ── ⌟");
        else System.out.println("\n ⌜ ── ◇ ── TURNO: ♚ NEGRO ── ◇ ── ⌟");
        //"◇ ─────────────────── ◇"
    }

    public void showTitle() {
        System.out.println(""" 
                TITULO""");
    }

    public void showMainMenu() {
        System.out.print("\n ◇ ─ [1] CREAR PARTIDA ── [2] CARGAR PARTIDA ── [0] SALIR ─ ◇ \n >> ");
    }

    public void showGameMenu() {
        System.out.print("\n ◇ ─ [1] MOVER PIEZA ── [2] RENDIRSE ── [3] TABLAS ── [4] GUARDAR PARTIDA ── [0] SALIR ─ ◇ \n >> ");
    }

    public void showError(String mjs) {
        System.out.println(" ERROR: " + mjs + "\n");
    }

    public void showSuccess(String mjs) {
        System.out.println(" SUCCESS: " + mjs + "\n");
    }

    public String readOriginSquare() {
        System.out.print("""
                
                [!] Escribe [ MENU ] para volver al menú.
                
                >> Escribe la casilla de origen:\s""");
        return readSquare();
    }

    public String readDestinationSquare() {
        System.out.print(">> Escribe la casilla de destino: ");
        return readSquare();
    }

    public String readSquare() {
        while (true) {
            String square = sc.nextLine().toUpperCase().trim();
            if (square.equals("MENU")) return square;

            if (square.matches("^[A-H][1-8]$")) return square;
            showError("Casilla invalida. Vuelve a introducir la casilla. Escribe MENU para volver al menú.");
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
                else showError("Numero fuera de rango. Vuelve a introducir un numero.");
            }
        }
    }

    public String readFileName(){
        while (true){
            System.out.println("Escribe le nombre para el fichero de la partida: ");
            String gameName = sc.nextLine().trim();

            if (gameName.matches("^[A-Za-z0-9_-]{1,20}$")) return gameName;
            else showError("Formato de nombre no valido. Vuelva a introducirlo.");
        }
    }
}

