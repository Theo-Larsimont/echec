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
 *
 * @author larsi
 */
public class King extends Piece{

    public King(Color color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
       List<Position> possibleMove = new ArrayList<>();
       
       if(board.contains(position.next(Direction.W))
               && (board.isFree(position.next(Direction.W))
               || board.containsOppositeColor(position.next(Direction.W), color))){
           possibleMove.add(position.next(Direction.W));
       }
       
       if(board.contains(position.next(Direction.NW))
               && (board.isFree(position.next(Direction.NW))
               || board.containsOppositeColor(position.next(Direction.NW), color))){
           possibleMove.add(position.next(Direction.NW));
       }
       
       if(board.contains(position.next(Direction.N))
               && (board.isFree(position.next(Direction.N))
               || board.containsOppositeColor(position.next(Direction.N), color))){
           possibleMove.add(position.next(Direction.N));
       }
       
       if(board.contains(position.next(Direction.NE))
               && (board.isFree(position.next(Direction.NE))
               || board.containsOppositeColor(position.next(Direction.NE), color))){
           possibleMove.add(position.next(Direction.NE));
       }
       
       if(board.contains(position.next(Direction.E))
               && (board.isFree(position.next(Direction.E))
               || board.containsOppositeColor(position.next(Direction.E), color))){
           possibleMove.add(position.next(Direction.E));
       }
       
       if(board.contains(position.next(Direction.SE))
               && (board.isFree(position.next(Direction.SE))
               || board.containsOppositeColor(position.next(Direction.SE), color))){
           possibleMove.add(position.next(Direction.SE));
       }
       
       if(board.contains(position.next(Direction.S))
               && (board.isFree(position.next(Direction.S))
               || board.containsOppositeColor(position.next(Direction.S), color))){
           possibleMove.add(position.next(Direction.S));
       }
       
       if(board.contains(position.next(Direction.SW))
               && (board.isFree(position.next(Direction.SW))
               || board.containsOppositeColor(position.next(Direction.SW), color))){
           possibleMove.add(position.next(Direction.SW));
       }
       
       return possibleMove;
    }
    
    
}
