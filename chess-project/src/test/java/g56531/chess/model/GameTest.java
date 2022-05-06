/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g56531.chess.model;

import g56531.chess.model.pieces.Pawn;
import g56531.chess.model.pieces.Piece;
import g56531.chess.model.pieces.Queen;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test methode game
 *
 * @author larsi
 */
public class GameTest {

    private Game game;

    @BeforeEach     // Exécutée avant chaque test
    public void setUp() {
        game = new Game();
    }

    // **************** getCurrentPlayer *****************
    @Test
    public void getCurrentPlayer() {
        game.start();
        Player currentplayer = game.getCurrentPlayer();
        Player expected = new Player(Color.WHITE);
        assertEquals(expected, currentplayer);
    }

    // **************** getOppositePlayer *****************
    @Test
    public void getOppositePlayer() {
        game.start();
        Player currentplayer = game.getOppositePlayer();
        Player expected = new Player(Color.BLACK);
        assertEquals(expected, currentplayer);
    }

    // **************** isCurrentPlayerPosition *****************
    @Test
    public void isCurrentPlayerPositionTrue() {
        game.start();
        Position pos = new Position(1, 1);
        assertTrue(game.isCurrentPlayerPosition(pos));
    }

    @Test
    public void isCurrentPlayerPositionFalseIsOppositePlayer() {
        game.start();
        Position pos = new Position(6, 1);
        assertFalse(game.isCurrentPlayerPosition(pos));
    }

    @Test
    public void isCurrentPlayerPositionFalseIsFree() {
        game.start();
        Position pos = new Position(5, 1);
        assertFalse(game.isCurrentPlayerPosition(pos));
    }

    @Test
    public void isCurrentPlayerPositionIsNotInBoard() {
        game.start();
        Position pos = new Position(9, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            game.isCurrentPlayerPosition(pos);
        });
    }

    // **************** movePiecePosition *****************
    @Test
    public void movePiecePositionOldPosIsNotInTheBoard() {
        game.start();
        Position oldPos = new Position(8, 0);
        Position newPos = new Position(0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(oldPos, newPos);
        });
    }

    @Test
    public void movePiecePositionNewPosIsNotInTheBoard() {
        game.start();
        Position oldPos = new Position(8, 0);
        Position newPos = new Position(0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(oldPos, newPos);
        });
    }

    @Test
    public void movePiecePositionoldPosIsfree() {
        game.start();
        Position oldPos = new Position(3, 0);
        Position newPos = new Position(0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(oldPos, newPos);
        });
    }

    @Test
    public void movePiecePositionoPieceInOldPosNotBelongCurrentPlayer() {
        game.start();
        Position oldPos = new Position(6, 1);
        Position newPos = new Position(0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(oldPos, newPos);
        });
    }

    @Test
    public void movePiecePositionWhitePiece() {
        game.start();
        Position oldPos = new Position(1, 1);
        Position newPos = oldPos.next(Direction.N);
        game.getBoard().setPiece(new Pawn(Color.WHITE), oldPos);
        game.movePiecePosition(oldPos, newPos);
        Piece expected = new Pawn(Color.WHITE);
        assertEquals(expected, game.getBoard().getPiece(newPos));
        assertEquals(null, game.getBoard().getPiece(oldPos));
    }

    @Test
    public void movePiecePositionPlay() {
        game.start();
        GameState expected = GameState.PLAY;
        game.movePiecePosition(new Position(1, 6), new Position(3, 6));
        game.movePiecePosition(new Position(6, 4), new Position(4, 4));
        game.movePiecePosition(new Position(1, 5), new Position(2, 5));
        GameState result = game.getState();

        assertEquals(expected, result);

    }

    @Test
    public void movePiecePositionCheckMate() {
        game.start();
        GameState expected = GameState.CHECK_MATE;
        game.movePiecePosition(new Position(1, 6), new Position(3, 6));
        game.movePiecePosition(new Position(6, 4), new Position(4, 4));
        game.movePiecePosition(new Position(1, 5), new Position(2, 5));
        game.movePiecePosition(new Position(7, 3), new Position(3, 7));
        GameState result = game.getState();

        assertEquals(expected, result);

    }

    @Test
    public void movePiecePositionCheck() {
        game.start();
        GameState expected = GameState.CHECK;
        game.movePiecePosition(new Position(1, 6), new Position(3, 6));
        game.movePiecePosition(new Position(6, 4), new Position(4, 4));
        game.movePiecePosition(new Position(1, 3), new Position(2, 3));
        game.movePiecePosition(new Position(6, 0), new Position(5, 0));
        game.movePiecePosition(new Position(1, 5), new Position(2, 5));
        game.movePiecePosition(new Position(7, 3), new Position(3, 7));

        GameState result = game.getState();

        assertEquals(expected, result);

    }

    // **************** getPossibleMoves *****************
    @Test
    public void testGetPossibleMovesP() {
        Position position = new Position(1, 1);
        Piece piece = new Pawn(Color.WHITE);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesInitialPositionWhitePieceButSecondSquareIsNotFree() {
        Position position = new Position(1, 1);
        Position position2 = new Position(3, 1);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceOpp = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);
        game.getBoard().setPiece(pieceOpp, position2);

        List<Position> expected = List.of(
                new Position(2, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesInitialPositionBlackPiece() {
        Position position = new Position(6, 1);
        Piece piece = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesInitialPositionBlackPieceButSecondSquareIsNotFree() {
        Position position = new Position(6, 1);
        Position position2 = new Position(4, 1);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceOpp = new Pawn(Color.WHITE);
        game.getBoard().setPiece(piece, position);
        game.getBoard().setPiece(pieceOpp, position2);

        List<Position> expected = List.of(
                new Position(5, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesFreeAroundWhitePiece() {
        Position position = new Position(2, 1);
        Piece piece = new Pawn(Color.WHITE);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesFreeAroundBlackPiece() {
        Position position = new Position(5, 1);
        Piece piece = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(4, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteWithOppositeColorInFront() {
        Position position = new Position(3, 4);
        Position PositionOpp = new Position(4, 4);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceOpp = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);
        game.getBoard().setPiece(pieceOpp, PositionOpp);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackWithOppositeColorInFront() {
        Position position = new Position(5, 1);
        Position PositionOpp = new Position(4, 1);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceOpp = new Pawn(Color.WHITE);
        game.getBoard().setPiece(piece, position);
        game.getBoard().setPiece(pieceOpp, PositionOpp);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteInEndOfBoard() {
        Position position = new Position(7, 4);
        Piece piece = new Pawn(Color.WHITE);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackInEndOfBoard() {
        Position position = new Position(0, 1);
        Piece piece = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = new ArrayList<>();

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteIsWestEdge() {
        Position position = new Position(3, 0);
        Piece piece = new Pawn(Color.WHITE);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(4, 0)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteIsEastEdge() {
        Position position = new Position(3, 7);
        Piece piece = new Pawn(Color.WHITE);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(4, 7)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackIsWeastEdge() {
        Position position = new Position(4, 0);
        Piece piece = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 0)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleBlackIsEastEdge() {
        Position position = new Position(4, 7);
        Piece piece = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 7)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleWhiteWithDiagonalOppositeColor() {
        Position position = new Position(4, 4);
        Position position2 = new Position(5, 3);
        Position position3 = new Position(5, 5);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceOpp = new Pawn(Color.BLACK);
        Piece pieceOpp2 = new Pawn(Color.BLACK);
        game.getBoard().setPiece(piece, position);
        game.getBoard().setPiece(pieceOpp, position2);
        game.getBoard().setPiece(pieceOpp2, position3);

        List<Position> expected = List.of(
                new Position(5, 4),
                new Position(5, 3),
                new Position(5, 5)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

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
        game.getBoard().setPiece(piece, position);
        game.getBoard().setPiece(pieceOpp, position2);
        game.getBoard().setPiece(pieceOpp2, position3);

        List<Position> expected = List.of(
                new Position(3, 4),
                new Position(3, 3),
                new Position(3, 5)
        );

        List<Position> positions = piece.getPossibleMoves(position, game.getBoard());

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


    // **************** isValidMove *****************
    @Test
    public void getCapturePositions() {
        Game game = new Game();
        game.start();
        Position oldPos = new Position(1, 3);
        Position newPos = new Position(3, 3);

        assertTrue(game.isValidMove(oldPos, newPos));
    }

    @Test
    public void getCapturePositionsFalse() {
        Game game = new Game();
        game.start();
        Position oldPos = new Position(1, 3);
        Position newPos = new Position(4, 3);

        assertThrows(IllegalArgumentException.class, () -> {
            game.isValidMove(oldPos, newPos);
        });
    }

    @Test
    public void getCapturePositionsFalseoldPosNotInboard() {
        Game game = new Game();
        game.start();
        Position oldPos = new Position(-1, 3);
        Position newPos = new Position(4, 3);

        assertThrows(IllegalArgumentException.class, () -> {
            game.isValidMove(oldPos, newPos);
        });
    }
}
