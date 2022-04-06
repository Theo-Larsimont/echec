/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g56531.chess.model.pieces;

import g56531.chess.model.Board;
import g56531.chess.model.Color;
import g56531.chess.model.Position;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author larsi
 */
public class BishopTest {

    @Test
    public void getPossibleMove() {
        Board board = new Board();
        Bishop bishop = new Bishop(Color.WHITE);
        board.setPiece(bishop, new Position(3, 3));
        List<Position> expected = new ArrayList<>();
        List<Position> result = bishop.getPossibleMoves(new Position(3, 3), board);
        for (int i = 0, x = 6; i < 8; ++i, --x) {
            if (i != 3) {
                expected.add(new Position(i, i));
            }
            if (i < 7 && x != 3) {
                expected.add(new Position(x, i));
            }
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveLeftBorder() {
        Board board = new Board();
        Bishop bishop = new Bishop(Color.WHITE);
        board.setPiece(bishop, new Position(3, 0));
        List<Position> expected = new ArrayList<>();
        List<Position> result = bishop.getPossibleMoves(new Position(3, 0), board);
        for (int i = 4, x = 1, y = 2; i < 8; ++i, ++x, --y) {
            expected.add(new Position(i, x));
            if (i < 7) {
                expected.add(new Position(y, x));
            }
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveRigthBorder() {
        Board board = new Board();
        Bishop bishop = new Bishop(Color.WHITE);
        board.setPiece(bishop, new Position(3, 7));
        List<Position> expected = new ArrayList<>();
        List<Position> result = bishop.getPossibleMoves(new Position(3, 7), board);
        for (int i = 4, x = 6, y = 2; i < 8; ++i, --x, --y) {
            expected.add(new Position(i, x));
            if (i < 7) {
                expected.add(new Position(y, x));
            }
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveCorner() {
        Board board = new Board();
        Bishop bishop = new Bishop(Color.WHITE);
        board.setPiece(bishop, new Position(0, 0));
        List<Position> expected = new ArrayList<>();
        List<Position> result = bishop.getPossibleMoves(new Position(0, 0), board);
        for (int i = 1; i < 8; ++i) {
            expected.add(new Position(i, i));
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveWithOppositePieceArround() {
        Board board = new Board();
        Bishop bishop = new Bishop(Color.WHITE);
        board.setPiece(bishop, new Position(3, 3));
        board.setPiece(new Pawn(Color.BLACK), new Position(7, 7));
        board.setPiece(new Pawn(Color.BLACK), new Position(5, 1));
        board.setPiece(new Pawn(Color.BLACK), new Position(1, 1));
        board.setPiece(new Pawn(Color.BLACK), new Position(2, 4));
        List<Position> expected = new ArrayList<>();
        List<Position> result = bishop.getPossibleMoves(new Position(3, 3), board);
        expected.add(new Position(4, 2));
        expected.add(new Position(5, 1));
        expected.add(new Position(2, 4));
        expected.add(new Position(2, 2));
        expected.add(new Position(1, 1));
        expected.add(new Position(4, 4));
        expected.add(new Position(5, 5));
        expected.add(new Position(6, 6));
        expected.add(new Position(7, 7));

        assertEqualsIgnoringOrder(expected, result);
    }
    
    @Test
    public void getPossibleMoveWithPieceArround() {
        Board board = new Board();
        Bishop bishop = new Bishop(Color.WHITE);
        board.setPiece(bishop, new Position(3, 3));
        board.setPiece(new Pawn(Color.WHITE), new Position(7, 7));
        board.setPiece(new Pawn(Color.WHITE), new Position(5, 1));
        board.setPiece(new Pawn(Color.WHITE), new Position(1, 1));
        board.setPiece(new Pawn(Color.WHITE), new Position(2, 4));
        List<Position> expected = new ArrayList<>();
        List<Position> result = bishop.getPossibleMoves(new Position(3, 3), board);
        expected.add(new Position(4, 2));
        expected.add(new Position(2, 2));
        expected.add(new Position(4, 4));
        expected.add(new Position(5, 5));
        expected.add(new Position(6, 6));

        assertEqualsIgnoringOrder(expected, result);
    }

    private void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

}
