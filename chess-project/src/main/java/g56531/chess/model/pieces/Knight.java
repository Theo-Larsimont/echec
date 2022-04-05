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
public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> possibleMove = new ArrayList<>();
        int column = position.getColumn();
        int row = position.getRow();
        Piece piece = board.getPiece(position);
        
        if(!board.contains(position)){
            throw new IllegalArgumentException("La position n'est pas dans le tableau");
        }
        // movement of the knight 1 square forward and 1 to the left
        Position move1 = new Position(row+1, column-2);
        if(board.contains(move1)
                && (board.containsOppositeColor(move1, this.color)
                || board.isFree(move1))){
            possibleMove.add(move1);
        }
        // movement of the knight 2 square forward and 1 to the left
        Position move2 = new Position(row+2, column-1);
        if(board.contains(move2)
                && (board.containsOppositeColor(move2, this.color)
                || board.isFree(move2))){
            possibleMove.add(move2);
        }
        // movement of the knight 2 square forward and 1 to the right
        Position move3 = new Position(row+2, column+1);
        if(board.contains(move3)
                && (board.containsOppositeColor(move3, this.color)
                || board.isFree(move3))){
            possibleMove.add(move3);
        }
        // movement of the knight 1 square forward and 1 to the right
        Position move4 = new Position(row+1, column+2);
        if(board.contains(move4) 
                && (board.containsOppositeColor(move4, this.color)
                || board.isFree(move4))){
            possibleMove.add(move4);
        }
        // movement of the knight 1 square back and 2 right
        Position move5 = new Position(row-1, column+2);
        if(board.contains(move5)
                && (board.containsOppositeColor(move5, this.color)
                || board.isFree(move5))){
            possibleMove.add(move5);
        }
        // movement of the knight 2 square back and 1 right
        Position move6 = new Position(row-2, column+1);
        if(board.contains(move6) 
                && (board.containsOppositeColor(move6, this.color)
                || board.isFree(move6))){
            possibleMove.add(move6);
        }
        // movement of the knight 2 square back and 1 left
        Position move7 = new Position(row-2, column-1);
        if(board.contains(move7) 
                && (board.containsOppositeColor(move7, this.color)
                || board.isFree(move7))){
            possibleMove.add(move7);
        }
        // movement of the knight 1 square back and 1 left
        Position move8 = new Position(row-1, column-2);
        if(board.contains(move8) 
                && (board.containsOppositeColor(move8, this.color)
                || board.isFree(move8))){
            possibleMove.add(move8);
        }
        
        return possibleMove;
    }
    
    
}
