package View;

import Model.Board;
import Model.Enum.Color;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner sc = new Scanner(System.in);

    public void showBoard(Board b) {
        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) System.out.print(b.getSquare(col, row) + " ");
            System.out.println();
        }
    }

    public void showTurn(Color color){
        if(color == Color.White) System.out.println("TURNO: BLANCO");
        else System.out.println("TURNO: NEGRO");
    }

    public void showTitle() {
        System.out.println("""
                ░█████╗░██╗░░██╗███████╗░██████╗░██████╗
                ██╔══██╗██║░░██║██╔════╝██╔════╝██╔════╝
                ██║░░╚═╝███████║█████╗░░╚█████╗░╚█████╗░
                ██║░░██╗██╔══██║██╔══╝░░░╚═══██╗░╚═══██╗
                ╚█████╔╝██║░░██║███████╗██████╔╝██████╔╝
                ░╚════╝░╚═╝░░╚═╝╚══════╝╚═════╝░╚═════╝░""");
    }

    public void showMainMenu() {
        System.out.println("\n⌜1⌟ CREAR PARTIDA ── ◇ ── ⌜2⌟ CARGAR PARTIDA ── ◇ ── ⌜0⌟ SALIR \n >> ");
    }

    public void showGameMenu() {
        System.out.println("\n⌜1⌟ MOVER PIEZA ── ◇ ── ⌜2⌟ RENDIRSE ── ◇ ── ⌜3⌟ TABLAS ── ◇ ── ⌜0⌟ SALIR \n >> ");
    }

    public void showError(String mjs) {
        System.out.println("[!] ERROR: " + mjs + "\n");
    }

    public String readOriginSquare() {
        System.out.print(">> Escribe la casilla de origen: ");
        return readSquare();
    }

    public String readDestinationSquare() {
        System.out.print(">> Escribe la casilla de destino: ");
        return readSquare();
    }

    public String readSquare() {
        while (true) {
            String square = sc.nextLine().toUpperCase().trim();
            if (square.matches("^[A-H][1-8]$")) return square;
            showError("Casilla invalida. Vuelve a introducir la casilla.");
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
}

