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
import java.util.Objects;

/**
 * Game piece.
 * @author larsi
 */
public abstract class Piece {
    Color color;
    boolean hasMoved;

    /**
     * Contructor of game piece.
     * @param color 
     */
    public Piece(Color color) {
        this.color = color;
        this.hasMoved = false;
    }

    /**
     * Give the color of piece.
     * @return color
     */
    public Color getColor() {
        return color;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }
    
    public void move(){
        this.hasMoved = true;
    }
    
    /**
     * look at all the movement of a possible piece
     * @param position of piece
     * @param board
     * @return 
     */
    public abstract List<Position> getPossibleMoves(Position position, Board board);
    
    public List<Position> getCapturePosition(Position position, Board board){
        return getPossibleMoves(position, board);
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
    public abstract String toString();
    
  
}
