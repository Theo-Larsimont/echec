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
//import g56531.chess.model.pieces.Sniper;
import g56531.chess.view.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * brings together the different stages of the game
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

//        board.setPiece(new Sniper(Color.WHITE), new Position(4, 7));
//        board.setPiece(new Sniper(Color.BLACK), new Position(3, 0));
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
        var currentPlayerCheck = true;
        TextView view = new TextView(this);

        // All error possible 
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

        // prevents movement on failure
        while (currentPlayerCheck) {
            Piece piece = board.getPiece(oldPos);
            board.dropPiece(oldPos);
            board.setPiece(piece, newPos);
            if(piece instanceof King){
                deplaceRoqueMove(oldPos, newPos);
            }
            if (getCapturePositions(getOppositePlayer()).contains(posKing(currentPlayer))) {
                board.setPiece(piece, oldPos);
                board.dropPiece(newPos);
                view.displayError("Ce mouvement vous met en échec veuillez "
                        + "choisir un autre mouvement ");
                oldPos = view.askPosition();
                newPos = view.askPosition();
            } else {
                currentPlayerCheck = false;
            }
        }

        board.getPiece(newPos).move();
        
        if (check(getCurrentPlayer())) {

            if (oneMoreValidMove(getOppositePlayer())) {
                state = GameState.CHECK;
            } else {
                state = GameState.CHECK_MATE;
            }

        } else if (!oneMoreValidMove(getOppositePlayer())) {
            state = GameState.STALE_MATE;
        } else {
            state = GameState.PLAY;
        }
        currentPlayer = getOppositePlayer();
    }

    /**
     * Get the possible moves for the piece located at the specified position.
     *
     * @param position the position of the piece
     * @return the list of admissible positions.
     */
    @Override
    public List<Position> getPossibleMoves(Position position) {
        Piece piece = board.getPiece(position);
        List<Position> possibleMove
                = board.getPiece(position).getPossibleMoves(position, board);
        possibleMove.addAll(board.getPiece(position).getCapturePosition(position, board));

        List<Position> posRook = findTheRook(getCurrentPlayer());
        if (piece instanceof King) {
            for (int i = 0; i < posRook.size(); ++i) {
                if (isCastlingPossible(getCurrentPlayer(), posRook.get(i))) {
                    possibleMove.addAll(roqueMovePositionKing(getCurrentPlayer(), posRook.get(i)));
                }
            }
        }
        if (piece instanceof Rook) {
            for (int i = 0; i < posRook.size(); ++i) {
                if (isCastlingPossible(getCurrentPlayer(), posRook.get(i))) {
                    possibleMove.addAll(roqueMovePositionRook(getCurrentPlayer(), position));
                }
            }
        }
        return possibleMove;

    }

    /**
     * gives the position of the king of a given player
     *
     * @param player
     * @return position of the king
     */
    private Position posKing(Player player) {
        Position king;
        if (player.getColor() == Color.WHITE) {
            king = board.getPiecePosition(whiteKing);
        } else {
            king = board.getPiecePosition(blackKing);
        }
        return king;
    }

    /**
     * Check if there is still a possible movement
     *
     * @param player
     * @return true if there is a movement possible
     */
    private boolean oneMoreValidMove(Player player) {
        boolean oneMoreMove = false;
        currentPlayer = getOppositePlayer();
        List<Position> allPiecePlayer = board.getPositionsOccupiedBy(player);

        for (int i = 0; i < allPiecePlayer.size(); ++i) {
            Position piecePos = allPiecePlayer.get(i);
            List<Position> allMovePosPiece = new ArrayList<>();
            allMovePosPiece.addAll(getPossibleMoves(piecePos));

            for (int x = 0; x < allMovePosPiece.size(); ++x) {
                Position newPos = allMovePosPiece.get(x);

                if (isValidMove(piecePos, newPos)) {
                    oneMoreMove = true;

                }
            }
        }
        currentPlayer = getOppositePlayer();
        return oneMoreMove;
    }

    /**
     * check if there is a possible move
     *
     * @return true if there is stalemate
     */
    private boolean check(Player player) {
        boolean kingCheck = false;
        List<Position> allPiecePos = board.getPositionsOccupiedBy(player);
        List<Position> allCapture = new ArrayList<>();

        for (int i = 0; i < allPiecePos.size(); ++i) {
            var posPiece = allPiecePos.get(i);
            var piece = board.getPiece(posPiece);

            allCapture.addAll(piece.getCapturePosition(posPiece, board));

        }
        if (allCapture.contains(posKing(getOppositePlayer()))) {
            kingCheck = true;
        }

        return kingCheck;
    }

    /**
     * Gives the state of the game
     *
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
        Piece piece = board.getPiece(oldPos);
        Piece pieceOnNewPos = null;
        Player player = getCurrentPlayer();
        Player oppositePlayer = getOppositePlayer();

        if (board.isFree(oldPos)) {
            throw new IllegalArgumentException("Mouvement impossible ou "
                    + "la position initial ne cotient aucune piece");
        }
        if (!board.isFree(newPos)) {
            pieceOnNewPos = board.getPiece(newPos);
        }

        board.dropPiece(oldPos);
        board.setPiece(piece, newPos);
        Position posCurrentKing = posKing(player);

        if (!getCapturePositions(oppositePlayer).contains(posCurrentKing)) {
            validMove = true;
        }
        board.setPiece(piece, oldPos);
        board.dropPiece(newPos);
        board.setPiece(pieceOnNewPos, newPos);
        return validMove;
    }

    /**
     * all the positions where you can capture a piece
     *
     * @param player currentPlayer
     * @return List of position
     */
    public List<Position> getCapturePositions(Player player) {
        List<Position> capturePosition = new ArrayList<>();
        List<Position> allPiecePlayer = board.getPositionsOccupiedBy(player);
        var playerColor = player.getColor();

        for (int i = 0; i < allPiecePlayer.size(); ++i) {
            var posPice = allPiecePlayer.get(i);
            var piece = board.getPiece(posPice);
            var allCapturePiece = piece.getCapturePosition(posPice, board);
            for (int x = 0; x < allCapturePiece.size(); ++x) {
                Position pos = allCapturePiece.get(x);
                var pieceTest = board.getPiece(pos);
                if (!board.isFree(pos)
                        && pieceTest.getColor() != playerColor) {
                    capturePosition.add(pos);
                }
            }
        }
        return capturePosition;

    }

    private boolean isCastlingPossible(Player player, Position rookPosition) {
        boolean castlingIsPossible = false;
        Player oppositePlayer = getOppositePlayer();
        Position king = posKing(getCurrentPlayer());
        boolean kingMove = board.getPiece(king).isHasMoved();
        boolean rookMove = board.getPiece(rookPosition).isHasMoved();

        if (kingMove
                || rookMove
                || getState() == GameState.CHECK) {
            return castlingIsPossible;
        }

        if (rookPosition.getColumn() == 0) {
            for (int i = king.getColumn() -1; 0 < i; --i) {
                Position posIsFree = new Position(king.getRow(), i);
                if (getCapturePositions(oppositePlayer).contains(posIsFree)
                        || !board.isFree(posIsFree)) {
                    return castlingIsPossible;
                } else {
                    castlingIsPossible = true;
                }
            }
        } else {
            for (int i = king.getColumn()+ 1; i < 7; ++i) {
                Position posIsFree = new Position(king.getRow(), i);
                System.out.println(getCapturePositions(oppositePlayer).contains(posIsFree));
                if (getCapturePositions(oppositePlayer).contains(posIsFree)
                        || !board.isFree(posIsFree)) {
                    return castlingIsPossible;
                } else {
                    castlingIsPossible = true;
                }
            }
        }

        return castlingIsPossible;
    }

    private List<Position> findTheRook(Player player) {
        List<Position> allPosRook = new ArrayList<>();
        List<Position> allPiecePos = board.getPositionsOccupiedBy(player);

        for (int i = 0; i < allPiecePos.size(); ++i) {
            Piece piece = board.getPiece(allPiecePos.get(i));
            if (piece instanceof Rook) {
                allPosRook.add(allPiecePos.get(i));
            }
        }
        return allPosRook;
    }

    private List<Position> roqueMovePositionKing(Player player, Position rookPosition) {
        List<Position> roqueMove = new ArrayList<>();
        Position king = posKing(player);
        if (rookPosition.getColumn() == 0) {
            roqueMove.add(new Position(king.getRow(), king.getColumn() - 2));
        } else {
            roqueMove.add(new Position(king.getRow(), king.getColumn() + 2));
        }

        return roqueMove;
    }

    private List<Position> roqueMovePositionRook(Player player, Position rookPosition) {
        List<Position> roqueMove = new ArrayList<>();
        Position king = posKing(player);
        if (rookPosition.getColumn() == 0) {
            roqueMove.add(new Position(king.getRow(), king.getColumn() - 1));
        } else {
            roqueMove.add(new Position(king.getRow(), king.getColumn() + 1));
        }

        return roqueMove;
    }

    private void deplaceRoqueMove(Position oldPos, Position newPos) {
        Color color = getCurrentPlayer().getColor();
        Piece piece = board.getPiece(oldPos);
        Piece rook = new Rook(color);

        if (piece instanceof King) {
            if (newPos == new Position(0, 2)) {
                board.dropPiece(new Position(0, 0));
                board.setPiece(rook, new Position(0,3));
                
            } else if (newPos == new Position(0, 6)) {
                board.dropPiece(new Position(0, 7));
                board.setPiece(rook, new Position(0,5));

            } else if (newPos == new Position(7, 2)) {
                board.dropPiece(new Position(7, 0));
                board.setPiece(rook, new Position(7,3));

            } else if (newPos == new Position(7, 6)) {
                board.dropPiece(new Position(7, 7));
                board.setPiece(rook, new Position(7,5));

            }
        }
    }
}
