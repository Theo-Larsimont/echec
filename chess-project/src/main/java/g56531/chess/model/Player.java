/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import java.util.Objects;

/**
 * Player
 * @author larsi
 */
public class Player {
    private Color color;

    /**
     * Player piece color.
     * @param color 
     */
    public Player(Color color) {
        this.color = color;
    }
    /**
    * Give the player piece color.
    * @return color
    */
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "Player{" + "color=" + color + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.color);
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
        final Player other = (Player) obj;
        return this.color == other.color;
    }
    
    
    
}
