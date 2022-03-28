/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.view;

import g56531.chess.model.Color;
import g56531.chess.model.Game;
import g56531.chess.model.Model;
import g56531.chess.model.Piece;
import g56531.chess.model.Player;
import g56531.chess.model.Position;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author larsi
 */
public class TextView implements View {

    private Model model;

    public TextView(Model model) {
        this.model = model;
    }

    /**
     * Display a title and welcome message
     */
    @Override
    public void displayTitle() {
        System.out.println("Les Echecs");
        System.out.println("Bienvenu dans le jeu d'échec");
    }

    /**
     * display the board game
     */
    @Override
    public void displayBoard() {
        for (int line = 0, numLine = 8; line < 8; ++line, --numLine) {
            System.out.print(numLine + " ");
            for (int col = 0; col < 8; ++col) {
                Position pos = new Position(line, col);
                if (model.getPiece(pos) == null) {
                    System.out.print("|  ");
                } else if (model.getPiece(pos).getColor() == Color.WHITE) {
                    System.out.print("|PB");
                } else {
                    System.out.print("|PN");
                }
            }
            System.out.println("|");
            System.out.print("  ");
            System.out.println("-------------------------");
        }
        System.out.println("  a  b  c  d  e  f  g  f");
    }

    /**
     * Display the winning player
     */
    @Override
    public void displayWinner() {
        if (model.isGameOver()) {
            System.out.println(model.getOppositePlayer());
        }

    }

    /**
     * display a message who invite the player to play
     */
    @Override
    public void displayPlayer() {
        System.out.println("C'est a vous de jouez : " + model.getCurrentPlayer());
    }

    /**
     * requests a valid position on the game board from the user
     *
     * @return
     */
    @Override
    public Position askPosition() {
        var line = 0;
        var col = "";
        var colInInt = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vous allez choisir une position.");
        System.out.println("Veuillez d'abord enter le numero de ligne");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Ceci n'est pas un entier");
            System.out.println("Veuillez d'abord enter le numero de ligne");
        }
        line = scanner.nextInt();
        System.out.println("Maintenant veuillez enter le numéro de colonne");
        while (scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Ceci n'est pas une lettre");
            System.out.println("Maintenant veuillez enter le numéro de colonne");
        }
        col = scanner.next();
        switch (col.charAt(0)) {
            case 'a':
                colInInt = 0;
                break;
            case 'b':
                colInInt = 1;
                break;
            case 'c':
                colInInt = 2;
                break;
            case 'd':
                colInInt = 3;
                break;
            case 'e':
                colInInt = 4;
                break;
            case 'f':
                colInInt = 5;
                break;
            case 'g':
                colInInt = 6;
                break;
            case 'h':
                colInInt = 7;
                break;
        }

        switch (line) {
            case 1:
                line = 7;
                break;
            case 2:
                line = 6;
                break;
            case 3:
                line = 5;
                break;
            case 4:
                line = 4;
                break;
            case 5:
                line = 3;
                break;
            case 6:
                line = 2;
                break;
            case 7:
                line = 1;
                break;
            case 8:
                line = 0;
                break;
        }
        System.out.println(line + " " + colInInt);
        return new Position(line, colInInt);
    }

    /**
     * displays the error message passed as a parameter
     *
     * @param message
     */
    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Model model = new Game();
        TextView view = new TextView(model);
        view.model.start();
        view.displayTitle();
        view.displayBoard();
        view.displayPlayer();
        view.askPosition();
        view.displayBoard();
    }

}
