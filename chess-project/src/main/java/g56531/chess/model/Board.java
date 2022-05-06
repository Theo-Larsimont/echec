/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import g56531.chess.model.pieces.Piece;
import java.util.List;
import java.util.ArrayList;

/**
 * Setting up the game board
 *
 * @author larsi
 */
public class Board {

    private Square[][] squares;

    /**
     * Initialize a new 8 x 8 board without any piece.
     *
     * @param squares
     */
    public Board() {
        this.squares = new Square[8][8];
        for (int line = 0; line < squares.length; ++line) {
            for (int col = 0; col < squares[line].length; ++col) {
                squares[line][col] = new Square(null);
            }
        }
    }

    /**
     * check if the position given is on the board.
     *
     * @param pos to check
     * @return true if the pos is in board
     */
    public boolean contains(Position pos) {
        var inGame = false;

        if (pos.getColumn() < squares.length
                && pos.getRow() < squares.length
                && -1 < pos.getRow()
                && -1 < pos.getColumn()) {
            inGame = true;
        }

        return inGame;
    }

    /**
     * Allows you to know if the pawn is still in its initial place.
     *
     * @param color
     * @return int for the color BLACK and 6 for the color WHITE
     */
    public int getInitialPawnRow(Color color) {
        var initialPos = 0;

        if (color == Color.BLACK) {
            initialPos = 6;
        } else {
            initialPos = 1;
        }

        return initialPos;
    }

    /**
     * Place a piece on the board at a given position if the position is not on
     * the board it throws an exception.
     *
     * @param piece that we want to place
     * @param position position in which we want to place it
     */
    public void setPiece(Piece piece, Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
        }
        squares[position.getRow()][position.getColumn()].setPiece(piece);
    }

    /**
     * Gives the piece to the positioned if the position is not on the board it
     * throws an exception.
     *
     * @param position
     * @return piece
     */
    public Piece getPiece(Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
        }
        return squares[position.getRow()][position.getColumn()].getPiece();
    }

    /**
     * Delete the piece on the square at the given position if the position is
     * not on the board it throws an exception.
     *
     * @param position
     */
    public void dropPiece(Position position) {
        if (!contains(position)) {
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
        }
        squares[position.getRow()][position.getColumn()].setPiece(null);
    }

    /**
     * Returns if the box at the given position is free if the position is not
     * on the board it throws an exception.
     *
     * @param position
     * @return true if is free
     */
    public boolean isFree(Position position) {
        var squareFree = false;
        if (!contains(position)) {
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
        }
        if (squares[position.getRow()][position.getColumn()].isFree()) {
            squareFree = true;
        }
        return squareFree;
    }

    /**
     * Check if the color of the piece at the given position is opposite to the
     * color given in parameter.
     *
     * @param position
     * @param color
     * @return true if the color is opposite
     */
    public boolean containsOppositeColor(Position position, Color color) {
        boolean oppositeColor = false;
        if (!contains(position)) {
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
        }

        // return false if the suqare is null 
        if (isFree(position)) {
            return oppositeColor;
        }

        //piece color 
        Color colorPiceInSquare = squares[position.getRow()][position.getColumn()]
                .getPiece().getColor();

        if (color != colorPiceInSquare) {
            oppositeColor = true;
        }
        return oppositeColor;
    }

    /**
     * Returns all positions occupied by the player.
     *
     * @param player
     * @return List with all the positions that the player occupies
     */
    public List<Position> getPositionsOccupiedBy(Player player) {
        List<Position> occuped = new ArrayList<>();

        for (int line = 0; line < squares.length; ++line) {
            for (int col = 0; col < squares[line].length; ++col) {
                Position pos = new Position(line, col);
                if (!isFree(pos)
                        && squares[line][col].getPiece().getColor() == player.getColor()) {
                    occuped.add(pos);
                }
            }
        }
        return occuped;
    }

    /**
     * hold the position of a piece
     *
     * @param piece
     * @return position of piece
     */
    public Position getPiecePosition(Piece piece) {
        Position position = null;

        for (int line = 0; line < squares.length; ++line) {
            for (int col = 0; col < squares[line].length; ++col) {
                if (piece == squares[line][col].getPiece()) {
                    position = new Position(line, col);
                }
            }
        }

        return position;

    }
}
