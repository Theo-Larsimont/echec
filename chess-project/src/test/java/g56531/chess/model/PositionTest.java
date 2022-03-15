/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g56531.chess.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author larsi
 */
public class PositionTest {
    
    @Test
    public void nextDirectionRowWithPositiveValue(){
        Position pos1 = new Position(4,4);
        Position nextPos = pos1.next(Direction.N);
        var nextRow = nextPos.getRow();
        assertEquals(5,nextRow);
    }
    
     @Test
    public void nextDirectionColumnWithPositiveValue(){
        Position pos1 = new Position(4,4);
        Position nextPos = pos1.next(Direction.N);
        var nextCol = nextPos.getColumn();
        assertEquals(4,nextCol);
    }
    
      @Test
    public void nextDirectionRowWithNagativeValue(){
        Position pos1 = new Position(4,4);
        Position nextPos = pos1.next(Direction.SW);
        var nextRow = nextPos.getRow();
        assertEquals(3,nextRow);
    }
    
     @Test
    public void nextDirectionColumnWithNagativeValue(){
        Position pos1 = new Position(4,4);
        Position nextPos = pos1.next(Direction.SW);
        var nextCol = nextPos.getColumn();
        assertEquals(3,nextCol);
    }
}
