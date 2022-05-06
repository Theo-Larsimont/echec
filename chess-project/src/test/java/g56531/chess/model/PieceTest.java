/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g56531.chess.model;

import g56531.chess.model.pieces.Pawn;
import g56531.chess.model.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methode position
 * @author larsi
 */
public class PieceTest {

    Board board;

    public PieceTest() {
    }

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

     @Test
    public void testGetPossibleMovesP() {
        Position position = new Position(1,1);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test
    public void testGetPossibleMovesInitialPositionWhitePieceButSecondSquareIsNotFree() {
        Position position = new Position(1, 1);
        Position position2 = new Position(3, 1);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceOpp = new Pawn(Color.BLACK);
        board.setPiece(piece, position);
        board.setPiece(pieceOpp, position2);

        List<Position> expected = List.of(
                new Position(2, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesInitialPositionBlackPiece() {
        Position position = new Position(6, 1);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesInitialPositionBlackPieceButSecondSquareIsNotFree() {
        Position position = new Position(6, 1);
        Position position2 = new Position(4, 1);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceOpp = new Pawn(Color.WHITE);
        board.setPiece(piece, position);
        board.setPiece(pieceOpp, position2);

        List<Position> expected = List.of(
                new Position(5, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesFreeAroundWhitePiece() {
        Position position = new Position(2, 1);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesFreeAroundBlackPiece() {
        Position position = new Position(5, 1);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(4, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteWithOppositeColorInFront() {
        Position position = new Position(3, 4);
        Position PositionOpp = new Position(4, 4);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceOpp = new Pawn(Color.BLACK);
        board.setPiece(piece, position);
        board.setPiece(pieceOpp, PositionOpp);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackWithOppositeColorInFront() {
        Position position = new Position(5, 1);
        Position PositionOpp = new Position(4, 1);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceOpp = new Pawn(Color.WHITE);
        board.setPiece(piece, position);
        board.setPiece(pieceOpp, PositionOpp);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteInEndOfBoard() {
        Position position = new Position(7, 4);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackInEndOfBoard() {
        Position position = new Position(0, 1);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteIsWestEdge() {
        Position position = new Position(3, 0);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(4, 0)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteIsEastEdge() {
        Position position = new Position(3, 7);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(4, 7)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackIsWeastEdge() {
        Position position = new Position(4, 0);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 0)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackIsEastEdge() {
        Position position = new Position(4, 7);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 7)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteWithDiagonalOppositeColor() {
        Position position = new Position(4, 4);
        Position position2 = new Position(5, 3);
        Position position3 = new Position(5, 5);
        Pawn piece = new Pawn(Color.WHITE);
        Pawn pieceOpp = new Pawn(Color.BLACK);
        Pawn pieceOpp2 = new Pawn(Color.BLACK);
        board.setPiece(piece, position);
        board.setPiece(pieceOpp, position2);
        board.setPiece(pieceOpp2, position3);

        List<Position> expected = List.of(
                new Position(5, 4),
                new Position(5, 3),
                new Position(5, 5)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackWithDiagonalOppositeColor() {
        Position position = new Position(4, 4);
        Position position2 = new Position(3, 3);
        Position position3 = new Position(3, 5);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceOpp = new Pawn(Color.WHITE);
        Piece pieceOpp2 = new Pawn(Color.WHITE);
        board.setPiece(piece, position);
        board.setPiece(pieceOpp, position2);
        board.setPiece(pieceOpp2, position3);

        List<Position> expected = List.of(
                new Position(3, 4),
                new Position(3, 3),
                new Position(3, 5)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
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
