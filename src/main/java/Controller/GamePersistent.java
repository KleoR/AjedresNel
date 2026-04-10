package Controller;

import Model.*;
import Model.Enum.*;
import Model.Pieces.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class GamePersistent {

    private static final Path SAVES_FOLDER = Paths.get("saves");

    public static boolean gameExists(String gameName) {
        return Files.exists(SAVES_FOLDER.resolve(gameName));
    }

    // -------------------------------- SAVE ----------------------------------------

    public static void save(Game game, String gameName) throws IOException {
        Files.createDirectories(SAVES_FOLDER);
        Path file = SAVES_FOLDER.resolve(gameName);

        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            bw.write(game.getTurn().name());
            bw.newLine();
            bw.write(game.getStatus().name());
            bw.newLine();

            writeCapturedPieces(bw, game.getCapturedPieces());
            writeBoard(game.getBoard(), bw);
        }

    }

    private static void writeCapturedPieces(BufferedWriter bw, ArrayList<Piece> captured) throws IOException {
        if (captured == null || captured.isEmpty()) bw.write("0");
        else {
            bw.write(String.valueOf(captured.size()));
            for (Piece p : captured) bw.write(" " + p.getType().name() + "-" + p.getColor().name());
        }

        bw.newLine();
    }

    private static void writeBoard(Board board, BufferedWriter bw) throws IOException {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getSquare(col, row).getPiece();

                if (piece != null)
                    bw.write(" " + col + "-" + row + "-" + piece.getType().name() + "-" + piece.getColor().name());
            }
            bw.newLine();
        }
    }

    // -------------------------------- LOAD --------------------------------

    public static Game load(String gameName) throws IOException {
        Path file = SAVES_FOLDER.resolve(gameName);

        try (BufferedReader br = Files.newBufferedReader(file)) {
            Color turn = Color.valueOf(br.readLine());
            GameStatus status = GameStatus.valueOf(br.readLine());
            ArrayList<Piece> capturedPieces = readCapturedPieces(br);
            Board board = readBoard(br);

            return new Game(board, status, turn, capturedPieces);
        }
    }

    private static ArrayList<Piece> readCapturedPieces(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null || line.isBlank()) return new ArrayList<>();

        String[] parts = line.split(" ");
        int count = Integer.parseInt(parts[0]);
        ArrayList<Piece> captured = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            String[] typeColor = parts[i].split("-");
            captured.add(createPiece(typeColor[0], typeColor[1], null));
        }
        return captured;
    }

    private static Board readBoard(BufferedReader br) throws IOException {
        Board board = new Board(true);

        for (int i = 0; i < 8; i++) {
            String line = br.readLine();

            if (line == null) throw new IOException("Fichero incompleto: faltan filas del tablero");
            if (line.isBlank()) continue;

            String[] tokens = line.split(" ");

            for (String token : tokens) {
                if (token.isBlank()) continue;
                String[] parts = token.split("-");

                if (parts.length != 4) throw new IOException("Formato inválido en tablero: " + token);

                int col = Integer.parseInt(parts[0]);
                int row = Integer.parseInt(parts[1]);

                Piece piece = createPiece(parts[2], parts[3], board);
                board.getSquare(col, row).setPiece(piece);
            }
        }
        return board;
    }

    private static Piece createPiece(String type, String color, Board board) {
        Color pieceColor = Color.valueOf(color);

        return switch (PieceType.valueOf(type)) {
            case PAWN -> new Pawn(pieceColor, board);
            case ROOK -> new Rook(pieceColor, board);
            case KNIGHT -> new Knight(pieceColor, board);
            case BISHOP -> new Bishop(pieceColor, board);
            case QUEEN -> new Queen(pieceColor, board);
            case KING -> new King(pieceColor, board);
        };
    }
}