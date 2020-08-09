package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    private Block block1;
    private Block block2;
    private GameBoard gameBoard;

    @BeforeEach
    void runBefore() {
        block1 = new Block(0,0,0);
        block2 = new Block(5,0,0);
        gameBoard = new GameBoard(0);
    }

    @Test
    void testConstructor() {
        assertEquals(0, block1.getValue());
    }

    @Test
    void testSetValue() {
        block1.setValue(5);
        assertEquals(5, block1.getValue());
    }

    @Test
    void testEqual() {
        assertEquals(block1, block1);
        assertNotEquals(block2, block1);
        block1.setValue(5);
        assertEquals(block2, block1);
        block1.setValue(6);
        assertNotEquals(block2, block1);
        assertNotEquals(gameBoard, block1);
        block2 = null;
        assertFalse(block1.equals(block2));

    }

    @Test
    void testHashCode() {
        assertEquals(Objects.hash(0), block1.hashCode());
    }
}
