package model;

import java.util.ArrayList;

// Represents a list of game records
public class GameRecordList {
    public static final int MAX_SIZE = 10;
    public ArrayList<GameRecord> recordList;

    //EFFECTS: constructs a new empty game record list
    public GameRecordList() {
        recordList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add a new game record to the record list if list is not full yet, otherwise remove the first record and
    //         then add the new record
    public void addNewRecord(GameRecord g) {
        if (recordList.size() == MAX_SIZE) {
            recordList.remove(0);
            recordList.add(g);
        }
        recordList.add(g);
    }

    //EFFECTS: return the number of game records in the list
    public int getLength() {
        return recordList.size();
    }

    //EFFECTS: return the first record in the list (the one entered earliest)
    public GameRecord getFirstRecord() {
        return recordList.get(0);
    }


}
