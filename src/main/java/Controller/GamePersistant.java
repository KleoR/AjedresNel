package Controller;

import Model.Board;
import Model.Game;
import Model.Piece;
import Model.Square;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GamePersistant {

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

    public static boolean gameExist(String gameName){
        Path file = Paths.get("saves", gameName);
        return Files.exists(file);
    }

    public static void load() {
    }
}
