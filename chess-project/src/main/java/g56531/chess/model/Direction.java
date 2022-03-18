/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package g56531.chess.model;

/**
 * Gives a direction taken by the pawn.
 * @author larsi
 */
public enum Direction {
  NW(1,-1), 
  N(1,0), 
  NE(1,1), 
  W(0,-1), 
  E(0,1), 
  SW(-1,-1), 
  S(-1,0), 
  SE(-1,1);

    private int deltaRow;
    private int deltaColmun;

    /**
     * Initialize direction values.
     * @param deltaR
     * @param deltaC 
     */
    private Direction(int deltaR, int deltaC) {
        this.deltaRow = deltaR;
        this.deltaColmun = deltaC;
    }

    /**
     * Give deltaR.
     * @return deltaRow.
     */
    public int getDeltaRow() {
        return deltaRow;
    }
    
    /**
     * Give deltaC.
     * @return deltaColumn.
     */
    public int getDeltaColmun() {
        return deltaColmun;
    }
    
    
    
    
}
