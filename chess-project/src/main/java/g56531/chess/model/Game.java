/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author larsi
 */
public class Game implements Model{
    private Board board;
    private Player white;
    private Player black;
    private Player currentPlayer;

    public Game(Board board, Player white, Player black) {
        this.board = board;
        this.white = white;
        this.black = black;
    }

    
    /**
     * Start the game: create the pieces and put them on the board, initialize
     * the current player to 'WHITE'.
     */
    @Override
    public void start() {
        currentPlayer = white;
       for(int colWhite = 0, colBlack = 7; colWhite < 8; ++colWhite, --colBlack){
           board.setPiece(new Piece(Color.WHITE), new Position(1, colWhite));
           board.setPiece(new Piece(Color.BLACK), new Position(6, colBlack));
       }
    }

    /**
     * Get the piece of the board located on a given position
     *
     * @param pos the position
     * @return the piece located at P
     * @throws IllegalArgumentException pos  is not located on the board.
     */
    @Override
    public Piece getPiece(Position pos) {
        if(!board.contains(pos)){
            throw new IllegalArgumentException("La position n'est pas dans le plateau");
        }
        Piece piece = board.getPiece(pos);
        return piece;
    }

    /**
     * Getter for the current player.
     *
     * @return the current player.
     */
    @Override
    public Player getCurrentPlayer() {
       return currentPlayer;
    }

    /**
     * Getter for the second player.
     *
     * @return the player that is waiting
     */
    @Override
    public Player getOppositePlayer() {
        if(currentPlayer.getColor() == Color.WHITE){
            return black;
        }else{
            return white;
        }
    }

    /**
     * Check if the square at the given position is occupied
     * by a piece of the current player.
     *
     * @param pos the postion
     * @return true if the position is occupied by a piece
     * of the current player, false otherwise.
     * @throws IllegalArgumentException if the position is not located on the board.
     */
    @Override
    public boolean isCurrentPlayerPosition(Position pos) {
        var pieceCurrentPlayer = false;
        if(!board.contains(pos)){
            throw new IllegalArgumentException("La position n'est pas dans le plateau");
        }
        if(board.getPiece(pos).getColor() == currentPlayer.getColor()){
            pieceCurrentPlayer = true;
        }
        return pieceCurrentPlayer;
    }

     /**
     * Moves a piece from one position of the chess board to
     * another one. If the game is not over, change
     * the current player.
     *
     * @param oldPos the current position
     * @param newPos the new position of the board where to put the piece
     * @throws IllegalArgumentException if
     *                                  1) oldPos or newPos are not located on the board, or
     *                                  2) oldPos does not contain a piece, or
     *                                  3) the piece does not belong to the current player, or
     *                                  4) the move is not valid for the piece located at position oldPos                                 
     */
    @Override
    public void movePiecePosition(Position oldPos, Position newPos) {
        if(!board.contains(newPos) || !board.contains(oldPos)
                || board.isFree(oldPos)
                || board.getPiece(oldPos).getColor() != currentPlayer.getColor()
                || !board.getPiece(oldPos).getPossibleMoves(oldPos, board).contains(newPos)){
            throw new IllegalArgumentException("Il y a une erreur");
        }
        board.setPiece(new Piece(currentPlayer.getColor()), newPos);
        board.dropPiece(oldPos);
            
    }

    /**
     * Check if the game is over or not
     *
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        var gameOver = false;
        List<Position> positions = board.getPositionsOccupiedBy(currentPlayer);
        for(int i = 0; i < positions.size(); ++i){
            Position pos = positions.get(i);
            if(board.getPiece(pos).getPossibleMoves(pos, board) != null){
                return false;
            }else{
                gameOver = true;
            }
        }
        
        return gameOver;
    }

    /**
     * Get the possible moves for the piece located at
     * the specified position.
     *
     * @param position the position of the piece
     * @return the liste of admissible positions.
     */
    @Override
    public List<Position> getPossibleMoves(Position position) {
        List<Position> possibleMove 
                = board.getPiece(position).getPossibleMoves(position, board);
        return possibleMove;
        
    }
    

}
