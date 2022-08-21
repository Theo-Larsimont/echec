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
//import g56531.chess.model.pieces.Sniper;
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
//                    if (piece instanceof Sniper) {
//                        if (piece.getColor() == Color.WHITE) {
//                            System.out.print("|SW");
//                        } else {
//                            System.out.print("|SB");
//                        }
//                    }
                } else {
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
        System.out.println(model.getOppositePlayer());
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
        var pos = "";
        var colInInt = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez enter la lettre de colonne (de a à h) "
                + "puis le numero de la ligne");

        pos = scanner.next();
        while (pos.length() != 2){
             System.out.println("Attention veuillez d'abord "
                     + "enter la lettre de la colone "
                     + "puis la numero de la ligne");
            pos = scanner.next();

        }
        var col = pos.charAt(0);
        var lineChar = pos.charAt(1);
        var linInt = Character.getNumericValue(lineChar);
        

        while (!(linInt < 9 && linInt > 0)) {
            System.out.println("Le coordonées entrée ne sont pas valide !"
                        + " Veuillez essayer avec d'autres");

            pos = scanner.next();
            if(pos.length() != 2){
                System.out.println("Le coordonées entrée ne sont pas valide !"
                        + " Veuillez essayer avec d'autres");
                pos = scanner.next();
            }
            lineChar = pos.charAt(1);
            linInt = Character.getNumericValue(lineChar);
            col = pos.charAt(0);

        }
        lineChar = pos.charAt(1);

        switch (col) {
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

        linInt = linInt - 1;
        return new Position(linInt, colInInt);
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

    /**
     * displays a message to say that its king is in check
     */
    @Override
    public void displayCheck() {
        System.out.println("== Votre Roi est en échec ! ==");
    }

    /**
     * displays a message to say that its king is in checkmate
     */
    @Override
    public void displayMat() {
        System.out.println("====== Echec et mat ! ======");
    }

    /**
     * displays a message to say that the game ended in a tie
     */
    @Override
    public void displayStaleMat() {
        System.out.println("========= Egalité =========");
    }

}
