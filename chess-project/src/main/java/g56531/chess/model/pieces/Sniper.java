///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package g56531.chess.model.pieces;
//
//import g56531.chess.model.Board;
//import g56531.chess.model.Color;
//import g56531.chess.model.Position;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author larsi
// */
//public class Sniper extends Piece {
//
//    public Sniper(Color color) {
//        super(color);
//    }
//
//    @Override
//    public List<Position> getPossibleMoves(Position position, Board board) {
//        List<Position> possibleMove = new ArrayList<>();
//        int col = position.getColumn();
//        for (int i = 0; i < 8; i++) {
//            var pos = new Position(i, col);
//            var piece = board.getPiece(pos);
//            if ( board.isFree(pos)
//                    || !piece.getColor().equals(this.color)) {
//                possibleMove.add(pos);
//            }
//        }
//        return possibleMove;
//    }
//
//    @Override
//    public List<Position> getCapturePosition(Position position, Board board) {
//        List<Position> capturePossible = new ArrayList<>();
//        int col = position.getColumn();
//        for (int i = 0; i < 8; i++) {
//            var pos = new Position(i, col);
//            var piece = board.getPiece(pos);
//            if (!piece.getColor().equals(this.color)
//                    || piece instanceof King
//                    || piece instanceof Queen) {
//                capturePossible.add(pos);
//            }
//        }
//        return capturePossible;
//    }
//
//}
