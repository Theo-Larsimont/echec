/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.controller;

import g56531.chess.model.Game;
import g56531.chess.model.Model;
import g56531.chess.model.Position;
import g56531.chess.view.TextView;
import g56531.chess.view.View;

/**
 * course of a game
 *
 * @author larsi
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * course of a chess game
     */
    public void play() {
        boolean gameOver = false;
        boolean error = true;
        view.displayTitle();
        model.start();

        while (!gameOver) {
            view.displayBoard();
            view.displayPlayer();
            System.out.println("Veuillez choisir le pion que "
                    + " vous allez déplacer ");
            Position oldPos = view.askPosition();
            System.out.println("Veuillez choisir la case sur laquelle "
                    + " vous voulez deplacer le pion ");
            Position newPos = view.askPosition();
            while (!model.getPossibleMoves(oldPos).contains(newPos)) {
                view.displayError("Ce mouvement est impossible veuillez choisir"
                        + " une autre position !");
                oldPos = view.askPosition();
            }

            model.movePiecePosition(oldPos, newPos);
            System.out.println(model.isGameOver());
            if (model.isGameOver()) {
                gameOver = true;
            }

        }
        view.displayWinner();
    }
}
