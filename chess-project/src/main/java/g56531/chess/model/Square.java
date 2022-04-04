/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import g56531.chess.model.pieces.Piece;

/**
 * Represents one of the 64 squares of the game.
 *
 * @author larsi
 */
public class Square {

    private Piece piece;

    public Square() {
        this.piece = null;
    }

    /**
     * Put a piece in a square.
     *
     * @param piece
     */
    public Square(Piece piece) {
        this.piece = piece;
    }

    /**
     * Give the coin on the square.
     *
     * @return piece.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Change the piece of the square.
     *
     * @param piece.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Indicates if the square is free.
     *
     * @return squareFree true if the square is free.
     */
    public boolean isFree() {
        var squareFree = false;
        if (piece == null) {
            squareFree = true;
        }

        return squareFree;
    }

}
