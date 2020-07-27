package model;

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
    void testGetDate() {
        assertEquals("July 24", gameRecord.getDate());
    }

}
