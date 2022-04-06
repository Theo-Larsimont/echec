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
public class RookTest {

    @Test
    public void getPossibleMove() {
        Board board = new Board();
        Rook rook = new Rook(Color.WHITE);
        board.setPiece(rook, new Position(3, 3));
        List<Position> expected = new ArrayList<>();
        List<Position> result = rook.getPossibleMoves(new Position(3, 3), board);
        for (int i = 0; i < 8; ++i) {
            if (i != 3) {
                expected.add(new Position(i, 3));
                expected.add(new Position(3, i));
            }
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveLeftBorder() {
        Board board = new Board();
        Rook rook = new Rook(Color.WHITE);
        board.setPiece(rook, new Position(3, 0));
        List<Position> expected = new ArrayList<>();
        List<Position> result = rook.getPossibleMoves(new Position(3, 0), board);
        for (int i = 0; i < 8; ++i) {
            if (i != 0) {
                expected.add(new Position(3, i));
            }
            if (i != 3) {
                expected.add(new Position(i, 0));
            }
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveRigthBorder() {
        Board board = new Board();
        Rook rook = new Rook(Color.WHITE);
        board.setPiece(rook, new Position(3, 7));
        List<Position> expected = new ArrayList<>();
        List<Position> result = rook.getPossibleMoves(new Position(3, 7), board);
        for (int i = 0; i < 8; ++i) {
            if (i != 7) {
                expected.add(new Position(3, i));
            }
            if (i != 3) {
                expected.add(new Position(i, 7));
            }
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveCorner() {
        Board board = new Board();
        Rook rook = new Rook(Color.WHITE);
        board.setPiece(rook, new Position(7, 0));
        List<Position> expected = new ArrayList<>();
        List<Position> result = rook.getPossibleMoves(new Position(7, 0), board);
        for (int i = 0; i < 8; ++i) {
            if (i != 0) {
                expected.add(new Position(7, i));
            }
            if (i != 7) {
                expected.add(new Position(i, 0));
            }
        }

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveWithOppositePieceArround() {
        Board board = new Board();
        Rook rook = new Rook(Color.WHITE);
        board.setPiece(rook, new Position(3, 3));
        board.setPiece(new Pawn(Color.BLACK), new Position(6, 3));
        board.setPiece(new Pawn(Color.BLACK), new Position(3, 4));
        board.setPiece(new Pawn(Color.BLACK), new Position(0, 3));
        board.setPiece(new Pawn(Color.BLACK), new Position(3, 1));
        List<Position> expected = new ArrayList<>();
        List<Position> result = rook.getPossibleMoves(new Position(3, 3), board);
        expected.add(new Position(3, 1));
        expected.add(new Position(3, 2));
        expected.add(new Position(3, 4));
        expected.add(new Position(0, 3));
        expected.add(new Position(1, 3));
        expected.add(new Position(2, 3));
        expected.add(new Position(4, 3));
        expected.add(new Position(5, 3));
        expected.add(new Position(6, 3));

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveWithPieceArround() {
        Board board = new Board();
        Rook rook = new Rook(Color.WHITE);
        board.setPiece(rook, new Position(3, 3));
        board.setPiece(new Pawn(Color.WHITE), new Position(6, 3));
        board.setPiece(new Pawn(Color.WHITE), new Position(3, 4));
        board.setPiece(new Pawn(Color.WHITE), new Position(0, 3));
        board.setPiece(new Pawn(Color.WHITE), new Position(3, 1));
        List<Position> expected = new ArrayList<>();
        List<Position> result = rook.getPossibleMoves(new Position(3, 3), board);
        expected.add(new Position(3, 2));
        expected.add(new Position(1, 3));
        expected.add(new Position(2, 3));
        expected.add(new Position(4, 3));
        expected.add(new Position(5, 3));

        assertEqualsIgnoringOrder(expected, result);
    }

    /*
     *      Permet de tester si deux listes de positions sont identiques à l'ordre
     *      des éléments prêts. Cette méthode est appelée
     *      par les méthodes de test.
     */
    private void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }
}
