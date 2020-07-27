package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameRecordListTest {
    GameRecordList list1;
    GameRecord record1;
    GameRecord record2;
    GameRecord record3;

    @BeforeEach
    void runBefore() {
        list1 = new GameRecordList();
        record1 = new GameRecord(4028, "January", "26");
        record2 = new GameRecord(983022, "May", "1");
        record3 = new GameRecord(100, "June", "20");
    }

    @Test
    void testAddOneNewRecord() {
        list1.addNewRecord(record1);
        assertEquals(1, list1.getLength());
    }

    @Test
    void testAddMultipleNewRecord() {
        list1.addNewRecord(record1);
        list1.addNewRecord(record2);
        list1.addNewRecord(record3);
        assertEquals(3, list1.getLength());
    }

    @Test
    void testAddRecordToFullList() {
        list1.addNewRecord(record1);
        list1.addNewRecord(record2);
        list1.addNewRecord(record3);
        list1.addNewRecord(record1);
        list1.addNewRecord(record2);
        list1.addNewRecord(record3);
        list1.addNewRecord(record1);
        list1.addNewRecord(record2);
        list1.addNewRecord(record3);
        list1.addNewRecord(record1);
        list1.addNewRecord(record3);
        assertEquals(record2, list1.getFirstRecord());
    }

    @Test
    void testGetListWithOneRecord() {
        list1.addNewRecord(record1);
        assertEquals(1, list1.getList().size());
    }

    @Test
    void testGetListWithMultipleRecord() {
        list1.addNewRecord(record1);
        list1.addNewRecord(record2);
        list1.addNewRecord(record3);
        assertEquals(3, list1.getList().size());
    }

}
