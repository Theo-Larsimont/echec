/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

/**
 * Indicates the position on the game board.
 * @author larsi
 */
public class Position {
    private int row;
    private int column;

    /**
    * Position on the chess board.
    * @param row of tab chess.
    * @param column of tab chess.
    */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Give the row. 
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Give the column.
     * @return column
     */
    public int getColumn() {
        return this.column;
    }
    
    /**
     * Returns a new position according to the requested direction.
     * @param dir Direction requested. 
     * @return newPos next Position.
     */
    public Position next(Direction dir){
        var nextRow = this.row + dir.getDeltaRow();
        var nextColumn = this.column + dir.getDeltaColmun();
        
        Position nextPos = new Position(nextRow, nextColumn);
        
        return nextPos;  
        
    }
}
