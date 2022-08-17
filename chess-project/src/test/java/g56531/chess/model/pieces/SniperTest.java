///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package g56531.chess.model.pieces;
//
//import g56531.chess.model.Board;
//import g56531.chess.model.Color;
//import g56531.chess.model.Position;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author larsi
// */
//public class SniperTest {
//
//    @Test
//    public void SniperTestWhiteWithNoPiece() {
//        Board board = new Board();
//        Sniper sniper = new Sniper(Color.WHITE);
//        board.setPiece(sniper, new Position(0, 0));
//        List<Position> expected = new ArrayList<>();
//        List<Position> result = sniper.getPossibleMoves(new Position(0, 0), board);
//
//        for (int i = 1; i < 8; i++) {
//            var pos = new Position(i, 0);
//            expected.add(pos);
//        }
//        assertEquals(expected, result);
//    }
//    
//     @Test
//    public void SniperTestBlackWithNoPiece() {
//        Board board = new Board();
//        Sniper sniper = new Sniper(Color.BLACK);
//        board.setPiece(sniper, new Position(0, 0));
//        List<Position> expected = new ArrayList<>();
//        List<Position> result = sniper.getPossibleMoves(new Position(0, 0), board);
//
//        for (int i = 0; i < 8; i++) {
//            var pos = new Position(0, i);
//            expected.add(pos);
//        }
//        assertEquals(expected, result);
//    }
//     @Test
//    public void SniperTestWhiteWithBlackPiece() {
//        Board board = new Board();
//        Sniper sniperWhite = new Sniper(Color.WHITE);
//        Sniper sniperBlack1 = new Sniper(Color.BLACK);
//        Sniper sniperBlack2 = new Sniper(Color.BLACK);
//        board.setPiece(sniperWhite, new Position(0, 0));
//        board.setPiece(sniperBlack1, new Position(0, 7));
//        board.setPiece(sniperBlack1, new Position(0, 4));
//        List<Position> expected = new ArrayList<>();
//        List<Position> result = sniperWhite.getPossibleMoves(new Position(3, 3), board);
//
//        for (int i = 0; i < 8; i++) {
//            var pos = new Position(0, i);
//            expected.add(pos);
//        }
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void SniperTestBlackWithWhitePiece() {
//        Board board = new Board();
//        Sniper sniperWhite = new Sniper(Color.BLACK);
//        Sniper sniperBlack1 = new Sniper(Color.WHITE);
//        Sniper sniperBlack2 = new Sniper(Color.WHITE);
//        board.setPiece(sniperWhite, new Position(0, 0));
//        board.setPiece(sniperBlack1, new Position(0, 7));
//        board.setPiece(sniperBlack1, new Position(0, 4));
//        List<Position> expected = new ArrayList<>();
//        List<Position> result = sniperWhite.getPossibleMoves(new Position(3, 3), board);
//
//        for (int i = 0; i < 8; i++) {
//            var pos = new Position(0, i);
//            expected.add(pos);
//        }
//        assertEquals(expected, result);
//    }
//
//}
