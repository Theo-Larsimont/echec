/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g56531.chess.model.pieces;

import g56531.chess.model.Board;
import g56531.chess.model.Color;
import g56531.chess.model.Direction;
import g56531.chess.model.Position;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author larsi
 */
public class PawnTest {

    @Test
    public void getCapturePositionWhiteNE() {
        Board board = new Board();
        Pawn p1 = new Pawn(Color.WHITE);
        Pawn p2 = new Pawn(Color.BLACK);
        Position pos = new Position(2, 3);
        Position pos2 = new Position(3, 4);
        board.setPiece(p1, pos);
        board.setPiece(p2, pos2);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.NE));
        List<Position> result = p1.getCapturePossible(pos, board);
        assertEquals(expected, result);
    }

    @Test
    public void getCapturePositionWhiteNW() {
        Board board = new Board();
        Pawn p1 = new Pawn(Color.WHITE);
        Pawn p2 = new Pawn(Color.BLACK);
        Position pos = new Position(2, 3);
        Position pos2 = new Position(3, 2);
        board.setPiece(p1, pos);
        board.setPiece(p2, pos2);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.NW));
        List<Position> result = p1.getCapturePossible(pos, board);
        assertEquals(expected, result);
    }

    @Test
    public void getCapturePositionWhiteNEandNW() {
        Board board = new Board();
        Pawn p1 = new Pawn(Color.WHITE);
        Pawn p2 = new Pawn(Color.BLACK);
        Pawn p3 = new Pawn(Color.BLACK);
        Position pos = new Position(2, 3);
        Position pos2 = new Position(3, 4);
        Position pos3 = new Position(3, 2);
        board.setPiece(p1, pos);
        board.setPiece(p2, pos2);
        board.setPiece(p2, pos3);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.NE));
        expected.add(pos.next(Direction.NW));
        List<Position> result = p1.getCapturePossible(pos, board);
        assertEquals(expected, result);
    }

    @Test
    public void getCapturePositionBlackSE() {
        Board board = new Board();
        Pawn p1 = new Pawn(Color.BLACK);
        Pawn p2 = new Pawn(Color.WHITE);
        Position pos = new Position(3, 3);
        Position pos2 = new Position(2, 4);
        board.setPiece(p1, pos);
        board.setPiece(p2, pos2);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.SE));
        List<Position> result = p1.getCapturePossible(pos, board);
        assertEquals(expected, result);
    }

    @Test
    public void getCapturePositionBlackSW() {
        Board board = new Board();
        Pawn p1 = new Pawn(Color.BLACK);
        Pawn p2 = new Pawn(Color.WHITE);
        Position pos = new Position(3, 3);
        Position pos2 = new Position(2, 2);
        board.setPiece(p1, pos);
        board.setPiece(p2, pos2);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.SW));
        List<Position> result = p1.getCapturePossible(pos, board);
        assertEquals(expected, result);
    }

    @Test
    public void getCapturePositionWhiteSEandSW() {
        Board board = new Board();
        Pawn p1 = new Pawn(Color.BLACK);
        Pawn p2 = new Pawn(Color.WHITE);
        Pawn p3 = new Pawn(Color.WHITE);
        Position pos = new Position(3, 3);
        Position pos2 = new Position(2, 4);
        Position pos3 = new Position(2, 2);
        board.setPiece(p1, pos);
        board.setPiece(p2, pos2);
        board.setPiece(p2, pos3);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.SE));
        expected.add(pos.next(Direction.SW));
        List<Position> result = p1.getCapturePossible(pos, board);
        assertEquals(expected, result);
    }
     @Test
    public void getCapturePositionWhiteNENotInBoard() {
        Board board = new Board();
        Pawn p1 = new Pawn(Color.WHITE);
        Position pos = new Position(2, 0);
        board.setPiece(p1, pos);
        List<Position> expected = new ArrayList<>();
        List<Position> result = p1.getCapturePossible(pos, board);
        assertEquals(expected, result);
    }

}
