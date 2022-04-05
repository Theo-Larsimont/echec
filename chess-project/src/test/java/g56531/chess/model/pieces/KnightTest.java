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
public class KnightTest {

    @Test

    public void getPossibleMove() {
        Board board = new Board();
        Knight knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(3, 3));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = knight.getPossibleMoves(new Position(3, 3), board);
        expected.add(new Position(5, 2));
        expected.add(new Position(4, 1));
        expected.add(new Position(5, 4));
        expected.add(new Position(4, 5));
        expected.add(new Position(2, 1));
        expected.add(new Position(1, 2));
        expected.add(new Position(1, 4));
        expected.add(new Position(2, 5));

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test

    public void getPossibleMoveWithPosInLeftBord() {
        Board board = new Board();
        Knight knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(3, 0));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = knight.getPossibleMoves(new Position(3, 0), board);
        expected.add(new Position(1, 1));
        expected.add(new Position(2, 2));
        expected.add(new Position(5, 1));
        expected.add(new Position(4, 2));

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test

    public void getPossibleMoveWithPosInColTwo() {
        Board board = new Board();
        Knight knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(3, 1));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = knight.getPossibleMoves(new Position(3, 1), board);
        expected.add(new Position(4, 3));
        expected.add(new Position(5, 2));
        expected.add(new Position(2, 3));
        expected.add(new Position(1, 2));
        expected.add(new Position(5, 0));
        expected.add(new Position(1, 0));

        assertEqualsIgnoringOrder(expected, result);
    }
    
    @Test

    public void getPossibleMoveWithOppositeColor() {
        Board board = new Board();
        Knight knight = new Knight(Color.WHITE);
        Knight knight2 = new Knight(Color.BLACK);
        board.setPiece(knight, new Position(3, 3));
        board.setPiece(knight2, new Position(5, 2));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = knight.getPossibleMoves(new Position(3, 3), board);
        expected.add(new Position(5, 2));
        expected.add(new Position(4, 1));
        expected.add(new Position(5, 4));
        expected.add(new Position(4, 5));
        expected.add(new Position(2, 1));
        expected.add(new Position(1, 2));
        expected.add(new Position(1, 4));
        expected.add(new Position(2, 5));

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
