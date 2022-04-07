/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import g56531.chess.model.pieces.Bishop;
import g56531.chess.model.pieces.King;
import g56531.chess.model.pieces.Knight;
import g56531.chess.model.pieces.Pawn;
import g56531.chess.model.pieces.Piece;
import g56531.chess.model.pieces.Queen;
import g56531.chess.model.pieces.Rook;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author larsi
 */
public class Game implements Model {

    private Board board;
    private Player white;
    private Player black;
    private Player currentPlayer;

    public Game() {
        this.board = new Board();
        this.white = new Player(Color.WHITE);
        this.black = new Player(Color.BLACK);
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Start the game: create the pieces and put them on the board, initialize
     * the current player to 'WHITE'.
     */
    @Override
    public void start() {
        currentPlayer = white;
        for (int colWhite = 0, colBlack = 7; colWhite < 8; ++colWhite, --colBlack) {
            board.setPiece(new Pawn(Color.WHITE), new Position(1, colWhite));
            board.setPiece(new Pawn(Color.BLACK), new Position(6, colBlack));

            if (colWhite == 0 || colWhite == 7) {
                board.setPiece(new Rook(Color.WHITE), new Position(0, colWhite));
                board.setPiece(new Rook(Color.BLACK), new Position(7, colWhite));
            }
            if (colWhite == 1 || colWhite == 6) {
                board.setPiece(new Knight(Color.WHITE), new Position(0, colWhite));
                board.setPiece(new Knight(Color.BLACK), new Position(7, colWhite));
            }
            if (colWhite == 2 || colWhite == 5) {
                board.setPiece(new Bishop(Color.WHITE), new Position(0, colWhite));
                board.setPiece(new Bishop(Color.BLACK), new Position(7, colWhite));
            }
            if (colWhite == 3) {
                board.setPiece(new Queen(Color.WHITE), new Position(0, colWhite));
                board.setPiece(new Queen(Color.BLACK), new Position(7, colWhite));
            }
            if (colWhite == 4) {
                board.setPiece(new King(Color.WHITE), new Position(0, colWhite));
                board.setPiece(new King(Color.BLACK), new Position(7, colWhite));
            }

        }

        board.setPiece(new King(Color.BLACK), new Position(7, 4));
        board.setPiece(new Queen(Color.BLACK), new Position(7, 3));
    }

    /**
     * Get the piece of the board located on a given position
     *
     * @param pos the position
     * @return the piece located at P
     * @throws IllegalArgumentException pos is not located on the board.
     */
    @Override
    public Piece getPiece(Position pos) {
        if (!board.contains(pos)) {
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
        return currentPlayer.getColor() == Color.WHITE ? black : white;
    }

    /**
     * Check if the square at the given position is occupied by a piece of the
     * current player.
     *
     * @param pos the postion
     * @return true if the position is occupied by a piece of the current
     * player, false otherwise.
     * @throws IllegalArgumentException if the position is not located on the
     * board.
     */
    @Override
    public boolean isCurrentPlayerPosition(Position pos) {
        var pieceCurrentPlayer = false;
        if (!board.contains(pos)) {
            throw new IllegalArgumentException("La position n'est pas dans le plateau");
        }
        if (!board.isFree(pos) && board.getPiece(pos).getColor() == currentPlayer.getColor()) {
            pieceCurrentPlayer = true;
        }
        return pieceCurrentPlayer;
    }

    /**
     * Moves a piece from one position of the chess board to another one. If the
     * game is not over, change the current player.
     *
     * @param oldPos the current position
     * @param newPos the new position of the board where to put the piece
     * @throws IllegalArgumentException if 1) oldPos or newPos are not located
     * on the board, or 2) oldPos does not contain a piece, or 3) the piece does
     * not belong to the current player, or 4) the move is not valid for the
     * piece located at position oldPos
     */
    @Override
    public void movePiecePosition(Position oldPos, Position newPos) {
        if (!board.contains(newPos) || !board.contains(oldPos)) {
            throw new IllegalArgumentException(" oldPos or newPos are not located on the board");
        }
        if (board.isFree(oldPos)) {
            throw new IllegalArgumentException("oldPos does not contain a piece");
        }
        if (board.getPiece(oldPos).getColor() != currentPlayer.getColor()) {
            throw new IllegalArgumentException(" othe piece does not belong to the current player");
        }
        if (!board.getPiece(oldPos).getPossibleMoves(oldPos, board).contains(newPos)) {
            throw new IllegalArgumentException(" the move is not valid for the piece located at position oldPos");
        }

        if (!this.isGameOver()) {
            board.setPiece(new Pawn(currentPlayer.getColor()), newPos);
            board.dropPiece(oldPos);
            currentPlayer = getOppositePlayer();
        }
    }

    /**
     * Check if the game is over or not
     *
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        var gameOver = false;

        List<Position> piecedRestante = board.getPositionsOccupiedBy(currentPlayer);
        if (piecedRestante.isEmpty()) {
            gameOver = true;
        }
        for (int i = 0; i < piecedRestante.size(); ++i) {
            Position pos = piecedRestante.get(i);
            Piece piece = board.getPiece(piecedRestante.get(i));
            if (piece.getPossibleMoves(pos, board).isEmpty()) {
                gameOver = true;
            };
        }
        return gameOver;
    }

    /**
     * Get the possible moves for the piece located at the specified position.
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
