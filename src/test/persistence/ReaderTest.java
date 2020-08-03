package persistence;

import model.GameRecord;
import model.GameRecordList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testReader() {
        assertEquals(",", Reader.DELIMITER);
    }

    @Test
    void testParseRecordList1() {
        try {
            List<GameRecord> gameRecords = Reader.readGameRecords(new File("./data/testRecordList1.txt"));
            GameRecord record1 = gameRecords.get(0);
            assertEquals(300, record1.getScore());
            assertEquals("May", record1.getMonth());
            assertEquals("12", record1.getDay());

            GameRecord record2 = gameRecords.get(1);
            assertEquals(125, record2.getScore());
            assertEquals("June", record2.getMonth());
            assertEquals("24", record2.getDay());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readGameRecords(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
