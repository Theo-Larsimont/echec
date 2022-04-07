/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess;

import g56531.chess.controller.Controller;
import g56531.chess.model.Game;
import g56531.chess.model.Model;
import g56531.chess.view.TextView;

/**
 * Start the game
 *
 * @author larsi
 */
public class main {

    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new TextView(game));
        controller.play();
    }

}
