/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

/**
 *player camp
 * @author larsi
 */
public class Player {
    private Color color;

    /**
     * player piece color
     * @param color 
     */
    public Player(Color color) {
        this.color = color;
    }
    /**
    * give the player piece color
    * @return color
    */
    public Color getColor() {
        return color;
    }
    
    
    
}
