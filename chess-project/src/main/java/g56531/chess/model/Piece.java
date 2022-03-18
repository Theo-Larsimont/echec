/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

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

    
   
    
}
