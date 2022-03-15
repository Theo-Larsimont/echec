/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package g56531.chess.model;

/**
 * color of a player, a piece or a square
 * @author larsi
 */
public enum Color {
    WHITE,BLACK;
    
    /**
     * Gives the opposite Color
     * @return Color
     */
    public Color oppsoite(){
        if(Color.this == WHITE){
            return BLACK;
        }else{
            return WHITE;
        }
    }
}
