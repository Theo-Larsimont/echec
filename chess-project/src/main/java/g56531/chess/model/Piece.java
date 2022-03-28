/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Game piece.
 * @author larsi
 */
public class Piece {
    private Color color;

    /**
     * Contructor of game piece.
     * @param color 
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Give the color of piece.
     * @return color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * look at all the movement of a possible piece
     * @param position of piece
     * @param board
     * @return 
     */
    public List<Position> getPossibleMoves(Position position, Board board){
        List<Position> possibleMoves = new ArrayList <>();
        // check if the piece is at the top or bottom edge of the board
        if(position.getRow() == 0 || position.getRow() == 7){
            return possibleMoves;
        }
        if(board.getPiece(position).getColor() != Color.WHITE){
            Position nextPos = position.next(Direction.N);
            if(board.isFree(position.next(Direction.N))){
                possibleMoves.add(nextPos);
                //if the piece is at the starting position check if the second square is free
                if(board.getInitialPawnRow(color) == position.getRow()
                        && board.isFree(nextPos.next(Direction.N))){
                    possibleMoves.add(nextPos.next(Direction.N));
                }
            }
            //check if there is an opposing piece to the NW
            if(position.getColumn() != 0 && 
                    board.containsOppositeColor(position.next(Direction.NW), color.WHITE)){
                possibleMoves.add(position.next(Direction.NW));
            }
            //check if there is an opposing piece to the NE
            if(position.getColumn() != 7 && 
                    board.containsOppositeColor(position.next(Direction.NE), color.WHITE)){
                possibleMoves.add(position.next(Direction.NE));
            }
        }else{
            Position nextPos = position.next(Direction.S);
            if(board.isFree(position.next(Direction.S))){
                possibleMoves.add(nextPos);
                //if the piece is at the starting position check if the second square is free
                if(board.getInitialPawnRow(color) == position.getRow()
                        && board.isFree(nextPos.next(Direction.S))){
                    possibleMoves.add(nextPos.next(Direction.S));
                }
            }
            //check if there is an opposing piece to the SW
            if(position.getColumn() != 0 &&
                    board.containsOppositeColor(position.next(Direction.SW), color.BLACK)){
                possibleMoves.add(position.next(Direction.SW));
            }
            //check if there is an opposing piece to the SE
            if(position.getColumn() != 7 && 
                    board.containsOppositeColor(position.next(Direction.SE), color.BLACK)){
                possibleMoves.add(position.next(Direction.SE));
            }
            
        }
        return possibleMoves;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        return this.color == other.color;
    }

    @Override
    public String toString() {
        return "Piece{" + "color=" + color + '}';
    }
    
    


    
   
    
}
