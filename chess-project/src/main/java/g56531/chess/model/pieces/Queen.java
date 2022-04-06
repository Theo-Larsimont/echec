/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g56531.chess.model.pieces;

import g56531.chess.model.Board;
import g56531.chess.model.Color;
import g56531.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author larsi
 */
public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> possibleMove = new ArrayList<>();
        Rook rook = new Rook(color);
        Bishop bishop = new Bishop(color);
        
        possibleMove.addAll(rook.getPossibleMoves(position, board));
        possibleMove.addAll(bishop.getPossibleMoves(position, board));
        
        return possibleMove;
        
    }
    
    
}
