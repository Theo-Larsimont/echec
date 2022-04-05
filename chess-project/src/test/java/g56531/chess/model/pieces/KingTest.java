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
public class KingTest {

    @Test
    public void getPossibkeMoveMiddle() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(3, 4));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(3, 4), board);

        expected.add(new Position(3, 3));
        expected.add(new Position(4, 3));
        expected.add(new Position(4, 4));
        expected.add(new Position(4, 5));
        expected.add(new Position(3, 5));
        expected.add(new Position(2, 5));
        expected.add(new Position(2, 4));
        expected.add(new Position(2, 3));

        assertEqualsIgnoringOrder(expected, result);

    }

    @Test
    public void getPossibkeMoveMiddleWithFullOppositeColor() {
        Board board = new Board();
        King king = new King(Color.WHITE);
       
        board.setPiece(new King(Color.BLACK), new Position(3, 3));
        board.setPiece(new King(Color.BLACK), new Position(4, 3));
        board.setPiece(new King(Color.BLACK), new Position(4, 4));
        board.setPiece(new King(Color.BLACK), new Position(4, 5));
        board.setPiece(new King(Color.BLACK), new Position(3, 5));
        board.setPiece(new King(Color.BLACK), new Position(2, 5));
        board.setPiece(new King(Color.BLACK), new Position(2, 4));
        board.setPiece(new King(Color.BLACK), new Position(2, 3));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(3, 4), board);

        expected.add(new Position(3, 3));
        expected.add(new Position(4, 3));
        expected.add(new Position(4, 4));
        expected.add(new Position(4, 5));
        expected.add(new Position(3, 5));
        expected.add(new Position(2, 5));
        expected.add(new Position(2, 4));
        expected.add(new Position(2, 3));

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveTopLeftCorner() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(7, 0));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(7, 0), board);

        expected.add(new Position(7, 1));
        expected.add(new Position(6, 1));
        expected.add(new Position(6, 0));
        

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveTopRigthCorner() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(7, 7));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(7, 7), board);

        expected.add(new Position(7, 6));
        expected.add(new Position(6, 7));
        expected.add(new Position(6, 6));
        

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveBottomLeftCorner() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(0, 0));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(0, 0), board);

        expected.add(new Position(1, 0));
        expected.add(new Position(1, 1));
        expected.add(new Position(0, 1));
        

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveBottomRigthCorner() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(0, 7));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(0, 7), board);

        expected.add(new Position(1, 7));
        expected.add(new Position(1, 6));
        expected.add(new Position(0, 6));
        

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveBorderLeft() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(3, 0));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(3, 0), board);

        expected.add(new Position(4, 0));
        expected.add(new Position(2, 0));
        expected.add(new Position(4, 1));
        expected.add(new Position(3, 1));
        expected.add(new Position(2, 1));
        

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveBorderRigth() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(3, 7));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(3, 7), board);

        expected.add(new Position(4, 7));
        expected.add(new Position(2, 7));
        expected.add(new Position(4, 6));
        expected.add(new Position(3, 6));
        expected.add(new Position(2, 6));
        

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveBorderTop() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(7, 3));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(7, 3), board);

        expected.add(new Position(7, 4));
        expected.add(new Position(7, 2));
        expected.add(new Position(6, 4));
        expected.add(new Position(6, 3));
        expected.add(new Position(6, 2));
        

        assertEqualsIgnoringOrder(expected, result);

    }
    
    @Test
    public void getPossibkeMoveBorderBottom() {
        Board board = new Board();
        King king = new King(Color.WHITE);
        board.setPiece(king, new Position(0, 3));
        List<Position> result = new ArrayList<>();
        List<Position> expected = new ArrayList<>();
        result = king.getPossibleMoves(new Position(0, 3), board);

        expected.add(new Position(0, 4));
        expected.add(new Position(0, 2));
        expected.add(new Position(1, 4));
        expected.add(new Position(1, 3));
        expected.add(new Position(1, 2));
        

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
