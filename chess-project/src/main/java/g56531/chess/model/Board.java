/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import java.util.List;
import java.util.ArrayList;

/**
 * chess board
 * @author larsi
 */
public class Board {
    private Square [][] board;

    /**
     * Initialize a new 8 x 8 board without any piece
     * @param squares 
     */
    public Board() {
        this.board = new Square[8][8];
    }

    /**
     * check if the position is in the table
     * @param pos to check
     * @return true if the pos is in board
     */
    public boolean contains(Position pos){
        var inGame = false;
        
        if(pos.getColumn() < board.length
                && pos.getRow() < board.length
                && -1 < pos.getRow()
                && -1 < pos.getColumn()){
            inGame = true;
        }
        
        return inGame;
    }
    
    /**
     * Allows you to know if the pawn is still in its initial place
     * @param color
     * @return int for the color BLACK and 6 for the color WHITE
     */
    public int getInitialPawnRow(Color color){
        var initialPos = 0;
        
        if(color == Color.BLACK){
            initialPos = 6;
        }else{
            initialPos = 1;
        }
        
        return initialPos;
    }
    
    /**
     * Place a piece on the board at a given position
     * if the position is not on the board it throws an exception
     * @param piece that we want to place
     * @param position position in which we want to place it
     */
    public void setPiece(Piece piece, Position position){
        if(!contains(position)){
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
        }
        board[position.getRow()][position.getColumn()] = new Square(piece);
    }
    
    /**
     * Gives the piece to the positioned
     * if the position is not on the board it throws an exception
     * @param position
     * @return piece 
     */
    public Piece getPiece(Position position){
         if(!contains(position)){
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
         }
        return board[position.getRow()][position.getColumn()].getPiece();
    }
    
    /**
     * Delete the piece on the square at the given position
     * if the position is not on the board it throws an exception
     * @param position 
     */
    public void dropPiece(Position position){
        if(!contains(position)){
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
         }
        
        board[position.getRow()][position.getColumn()] = new Square(null);
    }
    
    /**
     * Returns if the box at the given position is free
     * if the position is not on the board it throws an exception
     * @param position
     * @return true if is free
     */
    public boolean isFree (Position position){
        var squareFree = false;
        if(!contains(position)){
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
         }
        if(board[position.getRow()][position.getColumn()] == null){
            squareFree = true;
        }
        return squareFree;
    }
    
    /**
     * Check if the color of the piece at the given position is opposite 
     * to the color given in parameter
     * @param position
     * @param color
     * @return true if the color is opposite
     */
    public boolean containsOppositeColor(Position position, Color color){
        var oppositeColor = false;
         if(!contains(position)){
            throw new IllegalArgumentException("la position donnée n'est pas "
                    + "sur le plateau");
         }

         // piece color
         Color colorPiceInSqure = board[position.getRow()][position.getColumn()]
                 .getPiece().getColor();
         
         if(color == colorPiceInSqure.opposite()){
             oppositeColor = true;
         }
         
         return oppositeColor;
    }
    
    /**
     * returns all positions occupied by the player
     * @param player
     * @return List with all the positions that the player occupies
     */
    public List<Position> getPositionOccupiedBy(Player player){
        var occupied = new ArrayList<Position>();
        
        for (var line = 0; line < board.length; line++) {
            for(var col = 0; col < board[line].length; col++){
                board[line][col].getPiece().getColor();
                if(board[line][col].getPiece().getColor() == player.getColor()){
                    occupied.add(new Position(line, col));
                }
            }
        }
        
        return occupied;
    }
    
}
