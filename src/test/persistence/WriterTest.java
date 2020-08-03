package persistence;

import model.GameRecord;
import model.GameRecordList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testRecords.txt";
    private Writer testWriter;
    private GameRecord record1;
    private GameRecord record2;
    private GameRecordList gameRecordList;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        record1 = new GameRecord(300, "May", "12");
        record2 = new GameRecord(150, "June", "24");
        gameRecordList = new GameRecordList();
        gameRecordList.addNewRecord(record1);
        gameRecordList.addNewRecord(record2);
    }

    @Test
    void testWriteRecordList() {
        testWriter.write(gameRecordList);
        testWriter.close();

        try {
            List<GameRecord> gameRecords = Reader.readGameRecords(new File(TEST_FILE));
            GameRecord record1 = gameRecords.get(0);
            assertEquals(300, record1.getScore());
            assertEquals("May", record1.getMonth());
            assertEquals("12", record1.getDay());

            GameRecord record2 = gameRecords.get(1);
            assertEquals(150, record2.getScore());
            assertEquals("June", record2.getMonth());
            assertEquals("24", record2.getDay());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
