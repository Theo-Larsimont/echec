/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model.pieces;

import g56531.chess.model.Board;
import g56531.chess.model.Color;
import g56531.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author larsi
 */
public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> possibleMove = new ArrayList<>();
        int row = position.getRow();
        int column = position.getColumn();
        boolean movePossible = true;
        while (movePossible) {
            Position nextPos = new Position(++row, column);
            if (board.contains(nextPos)) {
                movePossible =false;
                if (board.isFree(nextPos)) {
                    possibleMove.add(nextPos);
                    movePossible =true;
                }
                if (board.containsOppositeColor(nextPos, this.color)) {
                    possibleMove.add(nextPos);
                }
            } else {
                movePossible = false;
            }
        }
        row = position.getRow();
        movePossible = true;
        while (movePossible) {
            Position nextPos = new Position(--row, column);
            if (board.contains(nextPos)) {
                movePossible =false;
                if (board.isFree(nextPos)) {
                    movePossible =true;
                    possibleMove.add(nextPos);
                }
                if (board.containsOppositeColor(nextPos, this.color)) {
                    possibleMove.add(nextPos);
                    row = position.getRow();                }
            } else {               
                movePossible = false;
            }
        }
        row = position.getRow();
        movePossible = true;
        while (movePossible) {
            Position nextPos = new Position(row, ++column);
            if (board.contains(nextPos)) {
                movePossible =false;
                if (board.isFree(nextPos)) {
                    movePossible =true;
                    possibleMove.add(nextPos);
                }
                if (board.containsOppositeColor(nextPos, this.color)) {
                    possibleMove.add(nextPos);
                }
            } else {
                movePossible = false;
            }
        }
        column = position.getColumn();
        movePossible = true;
        while (movePossible) {
            Position nextPos = new Position(row, --column);
            if (board.contains(nextPos)) {
                movePossible =false;
                if (board.isFree(nextPos)) {
                    movePossible =true;
                    possibleMove.add(nextPos);
                }
                if (board.containsOppositeColor(nextPos, this.color)) {
                    possibleMove.add(nextPos);              
                }
            } else {
                movePossible = false;
            }
        }

        return possibleMove;
    }

}
