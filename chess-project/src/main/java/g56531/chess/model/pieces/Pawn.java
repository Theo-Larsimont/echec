/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model.pieces;

import g56531.chess.model.Board;
import g56531.chess.model.Color;
import g56531.chess.model.Direction;
import g56531.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Game pawn.
 *
 * @author larsi
 */
public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> possibleMoves = new ArrayList<>();
        // check if the piece is at the top or bottom edge of the board
        if (position.getRow() == 0 || position.getRow() == 7) {
            return possibleMoves;
        }
        if (board.getPiece(position).getColor() == Color.WHITE) {
            Position nextPos = position.next(Direction.N);
            if (board.isFree(position.next(Direction.N))) {
                possibleMoves.add(nextPos);
                //if the piece is at the starting position check if the second square is free
                if (board.getInitialPawnRow(color) == position.getRow()
                        && board.isFree(nextPos.next(Direction.N))) {
                    possibleMoves.add(nextPos.next(Direction.N));
                }
            }
            //check if there is an opposing piece to the NW
            if (position.getColumn() != 0
                    && board.containsOppositeColor(position.next(Direction.NW), color.WHITE)) {
                possibleMoves.add(position.next(Direction.NW));
            }
            //check if there is an opposing piece to the NE
            if (position.getColumn() != 7
                    && board.containsOppositeColor(position.next(Direction.NE), color.WHITE)) {
                possibleMoves.add(position.next(Direction.NE));
            }
        } else {
            Position nextPos = position.next(Direction.S);
            if (board.isFree(position.next(Direction.S))) {
                possibleMoves.add(nextPos);
                //if the piece is at the starting position check if the second square is free
                if (board.getInitialPawnRow(color) == position.getRow()
                        && board.isFree(nextPos.next(Direction.S))) {
                    possibleMoves.add(nextPos.next(Direction.S));
                }
            }
            //check if there is an opposing piece to the SW
            if (position.getColumn() != 0
                    && board.containsOppositeColor(position.next(Direction.SW), color.BLACK)) {
                possibleMoves.add(position.next(Direction.SW));
            }
            //check if there is an opposing piece to the SE
            if (position.getColumn() != 7
                    && board.containsOppositeColor(position.next(Direction.SE), color.BLACK)) {
                possibleMoves.add(position.next(Direction.SE));
            }
        }
        return possibleMoves;
    }

    /**
     * look at the capturable pawns for a given pawn
     *
     * @param position position of the capturing pawn
     * @param board
     * @return capturePossible all positions of pawns can be captured
     */
    @Override
    public List<Position> getCapturePosition(Position position, Board board) {
        List<Position> capturePossible = new ArrayList<>();

        //if the color is white check next position NE and NW
        if (this.color == Color.WHITE) {
            var nextWhitePosNE = position.next(Direction.NE);
            var nextWhitePosNW = position.next(Direction.NW);
            if (board.contains(nextWhitePosNE)
                    && board.containsOppositeColor(nextWhitePosNE, Color.WHITE)) {
                capturePossible.add(nextWhitePosNE);

            }
            if (board.contains(nextWhitePosNW)
                    && board.containsOppositeColor(nextWhitePosNW, Color.WHITE)) {
                capturePossible.add(nextWhitePosNW);

            }
            //if the color is black check next position SE and SW
        } else {
            var nextBlackPosSE = position.next(Direction.SE);
            var nextBlackPosSW = position.next(Direction.SW);

            if (board.contains(nextBlackPosSE)
                    && board.containsOppositeColor(nextBlackPosSE, Color.BLACK)) {
                capturePossible.add(nextBlackPosSE);

            }
            if (board.contains(nextBlackPosSW)
                    && board.containsOppositeColor(nextBlackPosSW, Color.BLACK)) {
                capturePossible.add(nextBlackPosSW);

            }
        }
        return capturePossible;
    }

    @Override
    public String toString() {
        return "Pawn " + color;
    }
    
}
