/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.view;

import g56531.chess.model.Color;
import g56531.chess.model.Game;
import g56531.chess.model.Model;
import g56531.chess.model.pieces.Piece;
import g56531.chess.model.Player;
import g56531.chess.model.Position;
import g56531.chess.model.pieces.Bishop;
import g56531.chess.model.pieces.King;
import g56531.chess.model.pieces.Knight;
import g56531.chess.model.pieces.Pawn;
import g56531.chess.model.pieces.Queen;
import g56531.chess.model.pieces.Rook;
import java.util.List;
import java.util.Scanner;

/**
 * display the game
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
        System.out.println("  -------------------------");
        for (int line = 7, numLine = 8; line > -1; --line, --numLine) {
            System.out.print(numLine + " ");
            for (int col = 0; col < 8; ++col) {
                Position pos = new Position(line, col);
                Piece piece = model.getPiece(pos);
                if (piece != null) {
                    if (piece instanceof Pawn) {
                        if (piece.getColor() == Color.WHITE) {
                            System.out.print("|PW");
                        } else {
                            System.out.print("|PB");
                        }
                    }
                    if (piece instanceof Rook) {
                        if (piece.getColor() == Color.WHITE) {
                            System.out.print("|RW");
                        } else {
                            System.out.print("|RB");
                        }

                    }
                    if (piece instanceof Knight) {
                        if (piece.getColor() == Color.WHITE) {
                            System.out.print("|KW");
                        } else {
                            System.out.print("|KB");
                        }

                    }
                    if (piece instanceof Bishop) {
                        if (piece.getColor() == Color.WHITE) {
                            System.out.print("|BW");
                        } else {
                            System.out.print("|BB");
                        }

                    }
                    if (piece instanceof Queen) {
                        if (piece.getColor() == Color.WHITE) {
                            System.out.print("|#W");
                        } else {
                            System.out.print("|#B");
                        }

                    }
                    if (piece instanceof King) {
                        if (piece.getColor() == Color.WHITE) {
                            System.out.print("|*W");
                        } else {
                            System.out.print("|*B");
                        }

                    }
                }else{
                    System.out.print("|  ");
                }
            }
            System.out.println("|");
            System.out.print("  ");
            System.out.println("-------------------------");
        }
        System.out.println("   a  b  c  d  e  f  g  h");
        System.out.println("");
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
        System.out.println("Veuillez d'abord enter le numero de ligne");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Ceci n'est pas un entier");
            System.out.println("Veuillez d'abord enter le numero de ligne");
        }
        line = scanner.nextInt() - 1;
        System.out.println("Maintenant veuillez enter la lettre de colonne");
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

}
