package model;

import exception.ImpossibleScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameRecordTest {
    private GameRecord gameRecord;

    @BeforeEach
    void runBefore() {
        gameRecord = new GameRecord(468, "July", "24");
    }

    @Test
    void testGetScore() {
        assertEquals(468, gameRecord.getScore());
    }

    @Test
    void testGetMonth() {
        assertEquals("July", gameRecord.getMonth());
    }

    @Test
    void testGetDay() {
        assertEquals("24", gameRecord.getDay());
    }

    @Test
    void testUpdate() {
        try {
            gameRecord.update(124, "August", "12");
        } catch (ImpossibleScoreException exception) {
            fail();
        }
        assertEquals(124, gameRecord.getScore());
        assertEquals("August", gameRecord.getMonth());
        assertEquals("12", gameRecord.getDay());
    }

    @Test
    void testCatchException() {
        try {
            gameRecord.update(-12, "August", "12");
            fail();
        } catch (ImpossibleScoreException exception) {
            // expected to catch exception
        }
    }
}
