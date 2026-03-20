package Controller;

import Model.Board;
import Model.Enum.Color;
import Model.Enum.GameStatus;
import Model.Game;
import Model.Piece;
import Model.Pieces.*;
import Model.Square;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GamePersistent {

    public static void save(Game game, String gameName) throws IOException {
        Path saves = Paths.get("saves");

        if (!Files.exists(saves)) Files.createDirectories(saves);

        Path file = saves.resolve(gameName);

        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            bw.write(game.getTurn().name());
            bw.newLine();
            bw.write(game.getStatus().name());
            bw.newLine();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Square square = game.getBoard().getSquare(col, row);
                    Piece piece = square.getPiece();

                    if (piece == null) bw.write(col + " " + row + " Empty");
                    else bw.write(col + " " + row + " " + piece.getType().name() + " " + piece.getColor().name());

                    bw.newLine();
                }
            }
        }
    }

    public static boolean gameExist(String gameName) {
        Path file = Paths.get("saves", gameName);
        return Files.exists(file);
    }

    public static Game load(String gameName) throws IOException {
        Path file = Paths.get("saves", gameName);

        try (BufferedReader br = Files.newBufferedReader(file)) {
            Color turn = Color.valueOf(br.readLine());
            GameStatus status = GameStatus.valueOf(br.readLine());
            Board b = new Board(true);

            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");

                int col = Integer.parseInt(parts[0]);
                int row = Integer.parseInt(parts[1]);

                if (parts[2].equals("Empty")) continue;

                PieceType pieceType = PieceType.valueOf(parts[2]);
                Color pieceColor = Color.valueOf(parts[3]);

                Piece piece = switch (pieceType) {
                    case PAWN -> new Pawn(pieceColor, b);
                    case ROOK -> new Rook(pieceColor, b);
                    case QUEEN -> new Queen(pieceColor, b);
                    case KING -> new King(pieceColor, b);
                    case KNIGHT -> new Knight(pieceColor, b);
                    case BISHOP -> new Bishop(pieceColor, b);
                };
                b.getSquare(col, row).setPiece(piece);
            }
            return new Game(b, status, turn);
        }
    }
}
