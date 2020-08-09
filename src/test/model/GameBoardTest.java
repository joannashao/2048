package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    void runBefore() {
        gameBoard = new GameBoard(0);
    }

    @Test
    void testConstructor() {
        assertEquals(0, gameBoard.getScore());
    }

    @Test
    void testGetBlock() {
        assertEquals(gameBoard.board[1][2], gameBoard.getBlock(1,2));
    }

    @Test
    void testScoreUpdate() {
        gameBoard.scoreUpdate();
        assertEquals(1, gameBoard.getScore());
    }

    @Test
    void testMoveUp() {
        gameBoard.board[0][0] = new Block(4,0,0);
        gameBoard.board[1][0] = new Block(2,1,0);
        gameBoard.board[2][0] = new Block(16,2,0);
        assertFalse(gameBoard.checkBlockCanMerge(1,0));
        gameBoard.board[0][0].setValue(2);
        assertTrue(gameBoard.checkBlockCanMerge(1,0));
        gameBoard.moveUp();
        assertEquals(4, gameBoard.board[0][0].getValue());
        assertEquals(16, gameBoard.board[1][0].getValue());
    }

    @Test
    void testMoveDown() {
        gameBoard.board[2][3] = new Block(0,2,3);
        gameBoard.board[3][3] = new Block(2,3,3);
        gameBoard.board[1][3] = new Block(8,1,3);
        assertFalse(gameBoard.checkBlockCanMerge(2,3));
        gameBoard.board[2][3].setValue(2);
        assertTrue(gameBoard.checkBlockCanMerge(2,3));
        assertFalse(gameBoard.checkBlockBelow(3,3));
        gameBoard.moveDown();
        assertEquals(4,gameBoard.board[3][3].getValue());
        assertEquals(8,gameBoard.board[2][3].getValue());
    }

    @Test
    void testMoveLeft() {
        gameBoard.board[0][0] = new Block(2,0,0);
        gameBoard.board[0][1] = new Block(4,0,1);
        gameBoard.board[0][2] = new Block(2,0,2);
        assertFalse(gameBoard.checkBlockCanMerge(0,1));
        gameBoard.board[0][0].setValue(4);
        assertTrue(gameBoard.checkBlockCanMerge(0,1));
        gameBoard.moveLeft();
        assertEquals(8,gameBoard.board[0][0].getValue());
        assertEquals(2,gameBoard.board[0][1].getValue());
    }

    @Test
    void testMoveRight() {
        gameBoard.board[0][1] = new Block(8,0,1);
        gameBoard.board[0][2] = new Block(4,0,2);
        gameBoard.board[0][3] = new Block(2,0,3);
        assertFalse(gameBoard.checkBlockCanMerge(0,2));
        gameBoard.board[0][2].setValue(2);
        assertTrue(gameBoard.checkBlockCanMerge(0,2));
        gameBoard.moveRight();
        assertEquals(4,gameBoard.board[0][3].getValue());
        assertEquals(8,gameBoard.board[0][2].getValue());
    }

    @Test
    void testGenerateBlockValue() {
        int num = 0;
        gameBoard.generateBlockValue();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (gameBoard.board[r][c] == null) {
                    num++;
                }
            }
        }
        assertEquals(15, num);



    }

}

