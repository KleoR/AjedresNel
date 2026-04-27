package controller;

import Model.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    @Test
    void testWhite() {
        Square square = new Square(0,0);
        String squareString = square.toString();

        assertEquals(" ", squareString);
    }

    @Test
    void testBlack() {
        Square square = new Square(1,0);
        String squareString = square.toString();

        assertEquals("░", squareString);
    }
}
