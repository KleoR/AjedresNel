package View;

import java.util.Scanner;

class ConsoleInput {
    private final Scanner sc = new Scanner(System.in);
    private final ConsoleView view;

    public ConsoleInput(ConsoleView view) {
        this.view = view;
    }

    boolean yesOrNo() {
        while (true) {
            String option = sc.nextLine().trim().toUpperCase();
            if (option.matches("^[SN]$")) return option.equals("S");
            view.showError("Opción invalida, tiene que se [ S ] para si y [ N ] para no.");
        }
    }

    public String readSquare(String msj) {
        do {
            System.out.print(msj);
            String input = sc.nextLine().toUpperCase().trim();

            if (input.equals("MENU") || input.equals("BACK") || input.matches("^[A-H][1-8]$")) return input;
            view.showError("Casilla invalida. Vuelve a introducir la casilla.");

        } while (true);
    }

    public int readInt(int max) {
        while (true) {
            if (!sc.hasNextInt()) {
                view.showError("Numero invalido. Vuelve a introducir un numero.");
                sc.nextLine();
            } else {
                int num = sc.nextInt();
                sc.nextLine();
                if (num >= 0 && num <= max) return num;
                view.showError("Numero fuera de rango. Vuelve a introducir un numero.");
            }
        }
    }

    public String readFileName() {
        while (true) {
            System.out.print("\n                  ◇ Escribe le nombre para el fichero de la partida ◇\n >> ");
            String gameName = sc.nextLine().trim();

            if (gameName.matches("^[A-Za-z0-9_-]{1,20}$")) return gameName;
            view.showError("Formato de nombre no valido. Vuelva a introducirlo.");
        }
    }
}
