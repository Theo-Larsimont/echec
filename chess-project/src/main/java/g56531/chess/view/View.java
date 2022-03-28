/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package g56531.chess.view;

import g56531.chess.model.Position;

/**
 *
 * @author larsi
 */
public interface View {
    /**
     * Display a title and welcome message
     */
    public void displayTitle();
    
    /**
     * display the board game
     */
    public void displayBoard();
    
    /**
     * Display the winning player
     */
    public void displayWinner();
    
    /**
     * display a message who invite the player to play 
     */
    public void displayPlayer();
    
    /**
     * requests a valid position on the game board from the user
     * @return 
     */
    public  Position askPosition();
    
    /**
     * displays the error message passed as a parameter
     * @param message 
     */
    public void displayError(String message);
}
    