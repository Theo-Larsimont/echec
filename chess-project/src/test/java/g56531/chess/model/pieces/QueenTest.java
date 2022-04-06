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
public class QueenTest {

    @Test
    public void getPossibleMove() {
        Board board = new Board();
        Queen queen = new Queen(Color.WHITE);
        board.setPiece(queen, new Position(3, 3));
        List<Position> expected = new ArrayList<>();
        List<Position> result = queen.getPossibleMoves(new Position(3, 3), board);
        for (int i = 0, x = 6; i < 8; ++i, --x) {
            if (i != 3) {
                expected.add(new Position(i, i));
                expected.add(new Position(3, i));
                expected.add(new Position(i, 3));
            }
            if (i < 7 && x != 3) {
                expected.add(new Position(i, x));
            }
        }
        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveBorderLeft() {
        Board board = new Board();
        Queen queen = new Queen(Color.WHITE);
        board.setPiece(queen, new Position(3, 0));
        List<Position> expected = new ArrayList<>();
        List<Position> result = queen.getPossibleMoves(new Position(3, 0), board);
        for (int i = 0, x = 6; i < 8; ++i, --x) {
            if (i != 0) {
                expected.add(new Position(3, i));
            }
            if (i != 3) {
                expected.add(new Position(i, 0));
            }
        }

        for (int i = 1, x = 4, y = 2; i < 5; ++i, ++x, --y) {
            expected.add(new Position(x, i));
            if (i < 4) {
                expected.add(new Position(y, i));
            }

        }
        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveBorderRigth() {
        Board board = new Board();
        Queen queen = new Queen(Color.WHITE);
        board.setPiece(queen, new Position(3, 7));
        List<Position> expected = new ArrayList<>();
        List<Position> result = queen.getPossibleMoves(new Position(3, 7), board);
        for (int i = 0, x = 6; i < 8; ++i, --x) {
            if (i != 7) {
                expected.add(new Position(3, i));
            }
            if (i != 3) {
                expected.add(new Position(i, 7));
            }
        }

        for (int i = 6, x = 4, y = 2; i > 2; --i, ++x, --y) {
            expected.add(new Position(x, i));
            if (i > 3) {
                expected.add(new Position(y, i));
            }

        }
        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveCorner() {
        Board board = new Board();
        Queen queen = new Queen(Color.WHITE);
        board.setPiece(queen, new Position(0, 0));
        List<Position> expected = new ArrayList<>();
        List<Position> result = queen.getPossibleMoves(new Position(0, 0), board);
        for (int i = 1; i < 8; ++i) {
            expected.add(new Position(0, i));
            expected.add(new Position(i, 0));
            expected.add(new Position(i, i));
        }
        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveWithOppositePieceArround() {
        Board board = new Board();
        Queen queen = new Queen(Color.WHITE);
        board.setPiece(queen, new Position(3, 3));
        board.setPiece(new Pawn(Color.BLACK), new Position(5, 1));
        board.setPiece(new Pawn(Color.BLACK), new Position(5, 3));
        board.setPiece(new Pawn(Color.BLACK), new Position(7, 7));
        board.setPiece(new Pawn(Color.BLACK), new Position(3, 7));
        board.setPiece(new Pawn(Color.BLACK), new Position(0, 6));
        board.setPiece(new Pawn(Color.BLACK), new Position(2, 3));
        board.setPiece(new Pawn(Color.BLACK), new Position(2, 2));
        board.setPiece(new Pawn(Color.BLACK), new Position(3, 0));
        List<Position> expected = new ArrayList<>();
        List<Position> result = queen.getPossibleMoves(new Position(3, 3), board);
        for (int i = 0; i < 8; ++i) {
            if (i != 3) {
                expected.add(new Position(3, i));
            }
        }
        for (int i = 2; i < 8; ++i) {
            if (i != 3) {
                expected.add(new Position(i, i));
            }
            if (i != 3 && i < 6) {
                expected.add(new Position(i, 3));
            }
        }
        for (int i = 1, x = 5; i < 7; ++i, --x) {
            if (i != 3) {
                expected.add(new Position(x, i));
            }
        }
        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void getPossibleMoveWithPieceArround() {
        Board board = new Board();
        Queen queen = new Queen(Color.WHITE);
        board.setPiece(queen, new Position(3, 3));
        board.setPiece(new Pawn(Color.WHITE), new Position(5, 1));
        board.setPiece(new Pawn(Color.WHITE), new Position(5, 3));
        board.setPiece(new Pawn(Color.WHITE), new Position(7, 7));
        board.setPiece(new Pawn(Color.WHITE), new Position(3, 7));
        board.setPiece(new Pawn(Color.WHITE), new Position(0, 6));
        board.setPiece(new Pawn(Color.WHITE), new Position(2, 3));
        board.setPiece(new Pawn(Color.WHITE), new Position(3, 0));
        board.setPiece(new Pawn(Color.WHITE), new Position(2, 2));
        List<Position> expected = new ArrayList<>();
        List<Position> result = queen.getPossibleMoves(new Position(3, 3), board);
        for (int i = 1; i < 7; ++i) {
            if (i != 3) {
                expected.add(new Position(3, i));
            }
        }
        for (int i = 4; i < 7; ++i) {
            expected.add(new Position(i, i));
        }
        for (int i = 2, x = 4; i < 6; ++i, --x) {
            if (i != 3) {
                expected.add(new Position(x, i));
            }
        }
        expected.add(new Position(4, 3));
        
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
