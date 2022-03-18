/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g56531.chess.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of the nexPosition method.
 * @author larsi
 */
public class PositionTest {
    
    @Test
    public void nextDirectionN(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(5,4);
        Position nextPos = pos1.next(Direction.N);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
    
    @Test
    public void nextDirectionNE(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(5,5);
        Position nextPos = pos1.next(Direction.NE);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
    
     @Test
    public void nextDirectionNW(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(5,3);
        Position nextPos = pos1.next(Direction.NW);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
    
    @Test
    public void nextDirectionW(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(4,3);
        Position nextPos = pos1.next(Direction.W);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
    @Test
    public void nextDirectionE(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(4,5);
        Position nextPos = pos1.next(Direction.E);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
    @Test
    public void nextDirectionS(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(3,4);
        Position nextPos = pos1.next(Direction.S);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
    @Test
    public void nextDirectionSW(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(3,3);
        Position nextPos = pos1.next(Direction.SW);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
    @Test
    public void nextDirectionSE(){
        Position pos1 = new Position(4,4);
        Position expected = new Position(3,5);
        Position nextPos = pos1.next(Direction.SE);
        assertEquals(expected.getRow(),nextPos.getRow());
        assertEquals(expected.getColumn(), nextPos.getColumn());
    }
}
