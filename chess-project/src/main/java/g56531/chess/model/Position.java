/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

/**
 * Indicates the position on the game board
 * @author larsi
 */
public class Position {
    private int row;
    private int column;

/**
 * position on the chess board
 * @param row of tab chess
 * @param column of tab chess
 */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * give the row 
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * give the column
     * @return column
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Returns a new position according to the requested direction
     * @param dir Direction requested 
     * @return newPos next Position
     */
    public Position next(Direction dir){
        var nextRow = row + dir.getDeltaRow();
        var nextColumn = column + dir.getDeltaColmun();
        
        Position nextPos = new Position(nextRow, nextColumn);
        
        return nextPos;  
        
    }
}
