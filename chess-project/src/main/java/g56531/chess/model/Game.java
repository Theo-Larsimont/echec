/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model;

import g56531.chess.model.pieces.Bishop;
import g56531.chess.model.pieces.GameState;
import g56531.chess.model.pieces.King;
import g56531.chess.model.pieces.Knight;
import g56531.chess.model.pieces.Pawn;
import g56531.chess.model.pieces.Piece;
import g56531.chess.model.pieces.Queen;
import g56531.chess.model.pieces.Rook;
import g56531.chess.view.TextView;
import java.util.AbstractList;
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
    private King whiteKing;
    private King blackKing;
    private GameState state;

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
        whiteKing = new King(Color.WHITE);
        blackKing = new King(Color.BLACK);
        state = GameState.PLAY;
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
                board.setPiece(whiteKing, new Position(0, colWhite));
                board.setPiece(blackKing, new Position(7, colWhite));
            }

        }
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
        TextView view = new TextView(this);
        boolean currentPlayerCheck = true;
        Position king = new Position(0, 0);
        Position oppositeKing = new Position(0, 0);
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
        if (currentPlayer.getColor() == Color.WHITE) {
            king = board.getPiecePosition(whiteKing);
            oppositeKing = board.getPiecePosition(blackKing);
        } else {
            king = board.getPiecePosition(blackKing);
            oppositeKing = board.getPiecePosition(whiteKing);
        }
        while (currentPlayerCheck) {
            Piece piece = board.getPiece(oldPos);
            board.setPiece(piece, newPos);
            board.dropPiece(oldPos);
            if (getCapturePosition(getOppositePlayer()).contains(king)) {
                board.setPiece(piece, oldPos);
                board.dropPiece(newPos);
                view.displayError("Ce mouvement vous met en échec veuillez "
                        + "choisir un autre mouvement ");
                view.askPosition();
            } else {
                currentPlayerCheck = false;
            }
        }

        List<Position> allPosPiece = new ArrayList<>();
        allPosPiece = board.getPositionsOccupiedBy(getOppositePlayer());
        List<Position> allMovePossible = new ArrayList<>();
        for (int i = 0; i < allPosPiece.size(); ++i) {
            allMovePossible.addAll(getPossibleMoves(allPosPiece.get(i)));
        }
        
        if (getCapturePosition(getCurrentPlayer()).contains(oppositeKing)) {
            List<Position> posAvoidCheck = new ArrayList<>();
            posAvoidCheck.add(oppositeKing.next(Direction.NW));
            posAvoidCheck.add(oppositeKing.next(Direction.N));
            posAvoidCheck.add(oppositeKing.next(Direction.NE));
            posAvoidCheck.add(oppositeKing.next(Direction.W));
            posAvoidCheck.add(oppositeKing.next(Direction.E));
            posAvoidCheck.add(oppositeKing.next(Direction.SW));
            posAvoidCheck.add(oppositeKing.next(Direction.S));
            posAvoidCheck.add(oppositeKing.next(Direction.SE));

            if (posAvoidCheck.containsAll(allMovePossible)) {
                state = GameState.CHECK;
            } else {
                state = GameState.CHECK_MATE;
            }

        } else if (allMovePossible.isEmpty()) {
            state = GameState.STALE_MATE;
        } else {
            state = GameState.PLAY;
        }
        currentPlayer = getOppositePlayer();
    }

    /**
     * Check if the game is over or not
     *
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        var gameOver = false;
        TextView view = new TextView(this);
        if (state == GameState.CHECK) {
            view.displayError("Attention votre roi est en échec");
        } else if (state == GameState.STALE_MATE) {
            view.displayError("Egalité plus de mouvement possible");
            gameOver = true;

        } else if (state == GameState.CHECK_MATE) {
            view.displayError("Echec et mat");
            gameOver = true;
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

    /**
     * Gives the state of the game
     *
     * @param state
     * @return state of game
     */
    @Override
    public GameState getState() {
        return state;
    }

    /**
     * check if the movement is possible
     *
     * @param oldPos initial position
     * @param newPos wanted position
     * @return if the movement is valid
     */
    @Override
    public boolean isValidMove(Position oldPos, Position newPos) {
        boolean validMove = false;

        if (board.getPiece(oldPos).equals(null)
                || !getPossibleMoves(oldPos).contains(newPos)) {
            throw new IllegalArgumentException("Mouvement impossible ou "
                    + "la position initial ne cotient aucune piece");
        }

        if (getPossibleMoves(oldPos).contains(newPos)) {
            validMove = true;
        }
        return validMove;
    }

    /**
     * all the positions where you can capture a piece
     *
     * @param player currentPlayer
     * @return List of position
     */
    public List<Position> getCapturePosition(Player player) {
        List<Position> capturePosition = new ArrayList<>();
        List<Position> allPiecePlayer = board.getPositionsOccupiedBy(player);

        for (int i = 0; i < allPiecePlayer.size(); ++i) {
            for (int x = 0; x < getPossibleMoves(allPiecePlayer.get(i)).size(); ++x) {
                Position position = getPossibleMoves(allPiecePlayer.get(i)).get(x);
                if (board.containsOppositeColor(position, player.getColor())) {
                    capturePosition.add(position);
                }

            }
        }
        return capturePosition;

    }
}
